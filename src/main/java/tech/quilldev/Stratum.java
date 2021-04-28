package tech.quilldev;

import org.bukkit.plugin.java.JavaPlugin;
import tech.quilldev.Commands.ChangeServer;
import tech.quilldev.Commands.Lobby;
import tech.quilldev.Commands.NewWorld;
import tech.quilldev.Events.onJoin;
import tech.quilldev.Events.onLeave;
import tech.quilldev.Events.onMessage;
import tech.quilldev.Helpers.ConfigManager;

import java.util.Objects;

public final class Stratum extends JavaPlugin {

    private final ConfigManager configManager = new ConfigManager(this);

    @Override
    public void onEnable() {
        System.out.println("WORKING ON STRATUM");

        var pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new onJoin(), this);
        pluginManager.registerEvents(new onMessage(), this);
        pluginManager.registerEvents(new onLeave(), this);

        var changeServer = new ChangeServer(configManager);

        //Add executable commands
        Objects.requireNonNull(this.getCommand("newworld")).setExecutor(new NewWorld(configManager));
        Objects.requireNonNull(this.getCommand("changeserver")).setExecutor(changeServer);
        Objects.requireNonNull(this.getCommand("lobby")).setExecutor(new Lobby(changeServer));
    }

    @Override
    public void onDisable() {
        configManager.save();
    }

}
