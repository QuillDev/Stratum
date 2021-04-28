package tech.quilldev.Database;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.UuidRepresentation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tech.quilldev.Roles.Rank;

import java.util.ArrayList;

public class DatabaseManager implements Listener {

    //Store Stratum Users
    public static ArrayList<StratumUser> stratumUsers = new ArrayList<>();

    //
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    //Users collection
    private final MongoCollection<Document> userCollection;

    public DatabaseManager() {
        this.mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .uuidRepresentation(UuidRepresentation.STANDARD)
                        .build());

        this.database = mongoClient.getDatabase("stratum-general");
        this.userCollection = this.database.getCollection("users");

        //Load the player data for each player
        Bukkit.getOnlinePlayers().forEach(this::loadPlayerData);
    }

    /**
     * Create a new user if the player doesn't already have one in the database
     *
     * @param player to add to the database
     * @return the user that was created
     */
    public Document createUser(Player player) {
        final var user = new Document()
                .append("username_display", player.getName())
                .append("username_lower", player.getName().toLowerCase())
                .append("rank", Rank.MEMBER.value)
                .append("uuid", player.getUniqueId());

        final var result = userCollection.insertOne(user);

        //If the result was added to the DB
        if (result.wasAcknowledged()) {
            System.out.println("Added a new user to the DB with the uuid " + player.getUniqueId());
        }

        return user;
    }

    /**
     * Get a stratum user from the list
     *
     * @param player to get
     * @return the stratum user
     */
    public Document getUser(Player player) {
        final var uuid = player.getUniqueId();
        return userCollection.find(Filters.eq("uuid", uuid)).first();
    }

    /**
     * Add a user to the current active stratum user list
     *
     * @param stratumUser to add
     */
    public void addStratumUser(StratumUser stratumUser) {
        final var existingUser = stratumUsers.stream().parallel().filter(usr -> usr.getUuid().equals(stratumUser.getUuid())).findFirst();

        //if there is already an existing user, return
        if (existingUser.isPresent()) {
            return;
        }

        stratumUsers.add(stratumUser);
        System.out.println("Cached user " + stratumUser.getUsername_display());
    }

    /**
     * Load data for the given player from the database
     *
     * @param player to load data for
     */
    public void loadPlayerData(Player player) {
        //Get the player from the DB
        var user = getUser(player);

        //If the user didn't exist, create a new one
        if (user == null) {
            user = createUser(player);
        }

        assert user != null;
        addStratumUser(new StratumUser(user));
    }


    /**
     * Get a stratum user from the user list
     *
     * @param player in the list
     * @return the player
     */
    public static StratumUser getStratumUser(Player player) {
        for (var user : stratumUsers) {
            if (user.getUuid().equals(player.getUniqueId())) {
                return user;
            }
        }
        return null;
    }

    // Add and remove users from the database cache if they join/leave
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        loadPlayerData(event.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        stratumUsers.removeIf(stratumUser -> stratumUser.getUuid().equals(event.getPlayer().getUniqueId()));
    }

    public void stop() {
        mongoClient.close();
    }
}
