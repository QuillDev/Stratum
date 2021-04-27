package tech.quilldev;

import org.bukkit.plugin.java.JavaPlugin;
import tech.quilldev.Events.onJoin;
import tech.quilldev.Events.onMessage;

public final class Stratum extends JavaPlugin {


    @Override
    public void onEnable() {
        System.out.println("WORKING ON STRATUM");

        var pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new onJoin(), this);
        pluginManager.registerEvents(new onMessage(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
