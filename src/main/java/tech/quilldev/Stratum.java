package tech.quilldev;

import org.bukkit.plugin.java.JavaPlugin;

import tech.quilldev.Config.ConfigManager;
import tech.quilldev.Discord.TsubasaStratum;
import tech.quilldev.Events.onJoin;
import tech.quilldev.Events.onLeave;
import tech.quilldev.Events.onMessage;

public final class Stratum extends JavaPlugin {

    private final ConfigManager configManager = new ConfigManager(this);

    @Override
    public void onEnable() {


        configManager.initConfig();

        var pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new onJoin(), this);
        pluginManager.registerEvents(new onMessage(), this);
        pluginManager.registerEvents(new onLeave(), this);

        //Try to add discord integration
        try {
            final var bot = new TsubasaStratum(configManager);
            pluginManager.registerEvents(bot, this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onDisable() {
    }

}
