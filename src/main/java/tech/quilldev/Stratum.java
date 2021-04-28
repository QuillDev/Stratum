package tech.quilldev;

import org.bukkit.plugin.java.JavaPlugin;

import tech.quilldev.Config.ConfigManager;
import tech.quilldev.Database.DatabaseManager;
import tech.quilldev.Discord.TsubasaStratum;
import tech.quilldev.Events.onJoin;
import tech.quilldev.Events.onLeave;
import tech.quilldev.Events.onMessage;

public final class Stratum extends JavaPlugin {

    private final ConfigManager configManager = new ConfigManager(this);
    private TsubasaStratum bot;
    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        //Setup the config & database
        configManager.initConfig();
        this.databaseManager = new DatabaseManager();

        var pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new onJoin(), this);
        pluginManager.registerEvents(new onMessage(), this);
        pluginManager.registerEvents(new onLeave(), this);
        pluginManager.registerEvents(databaseManager, this);

        //Run discord stuff on it's own thread
        new Thread(() -> {
            try {
                this.bot = new TsubasaStratum(configManager);
                pluginManager.registerEvents(bot, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void onDisable() {
        bot.close();
        databaseManager.stop();
    }

}
