package tech.quilldev;

import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import tech.quilldev.Commands.ChangeServer;
import tech.quilldev.Commands.NewWorld;
import tech.quilldev.Events.onJoin;
import tech.quilldev.Events.onLeave;
import tech.quilldev.Events.onMessage;

import java.io.IOException;
import java.util.Objects;

public final class Stratum extends JavaPlugin {


    @Override
    public void onEnable() {
        System.out.println("WORKING ON STRATUM");

        var pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new onJoin(), this);
        pluginManager.registerEvents(new onMessage(), this);
        pluginManager.registerEvents(new onLeave(), this);

        //Add executable commands
        Objects.requireNonNull(this.getCommand("newworld")).setExecutor(new NewWorld());
        Objects.requireNonNull(this.getCommand("changeserver")).setExecutor(new ChangeServer());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
