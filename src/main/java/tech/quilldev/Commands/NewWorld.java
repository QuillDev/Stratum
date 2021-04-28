package tech.quilldev.Commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tech.quilldev.Helpers.ConfigManager;

public class NewWorld implements CommandExecutor {

    private final ConfigManager configManager;

    public NewWorld(ConfigManager configManager) {
        this.configManager = configManager;

        for (final var world : configManager.getWorlds()) {
            createDefaultWorld(world);
        }
    }

    //TODO: Add permissions to this ftlog
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;
        if (args.length <= 0) return false;

        var player = ((Player) sender).getPlayer();
        if (player == null) return false;

        //If the config manager has that world don't let them create it
        if (configManager.hasWorld(args[0])) {
            player.sendMessage("That world already exists!");
            return false;
        }

        createDefaultWorld(args[0]);

        Bukkit.getServer().sendMessage(Component.text(player.getName() + " created a new world " + args[0]));
        this.configManager.addWorld(args[0]);
        this.configManager.save();
        return true;
    }

    /**
     * Create a default world with the given name
     *
     * @param name of the world
     */
    private void createDefaultWorld(String name) {
        var worldCreator = new WorldCreator(name);
        worldCreator.environment(World.Environment.NORMAL);
        worldCreator.type(WorldType.NORMAL);
        worldCreator.createWorld();
        System.out.println("Created/Loaded world " + name);
    }
}
