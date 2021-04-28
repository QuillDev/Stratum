package tech.quilldev.Database;

import org.bson.Document;
import tech.quilldev.Roles.Rank;

import java.util.UUID;

public class StratumUser {

    private final UUID uuid;
    private final String username_lower;
    private final String username_display;
    private final Rank rank;

    public StratumUser(Document document) {
        this.uuid = (UUID) document.get("uuid");
        this.username_lower = document.getString("username_lower");
        this.username_display = document.getString("username_display");
        this.rank = Rank.getFromValue(document.getInteger("rank"));
    }

    public Rank getRank() {
        return rank;
    }

    public String getUsername_display() {
        return username_display;
    }

    public String getUsername_lower() {
        return username_lower;
    }

    public UUID getUuid() {
        return uuid;
    }
}
