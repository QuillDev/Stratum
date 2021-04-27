package tech.quilldev;

import org.bukkit.plugin.java.JavaPlugin;
import tech.quilldev.Events.onJoin;

public final class Stratum extends JavaPlugin {


    @Override
    public void onEnable() {
        System.out.println("WORKING ON STRATUM");

        var pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new onJoin(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
