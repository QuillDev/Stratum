package tech.quilldev;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tech.quilldev.Events.onJoin;

public final class Quilldev extends JavaPlugin {


    @Override
    public void onEnable() {
        var pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new onJoin(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
