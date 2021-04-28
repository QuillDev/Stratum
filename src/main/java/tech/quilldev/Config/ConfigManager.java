package tech.quilldev.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import tech.quilldev.Constants;

import java.io.File;
import java.util.Objects;

public class ConfigManager {

    private final static String configPath = "./plugins/Stratum/config.yml";
    private final Plugin plugin;
    private FileConfiguration config;

    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void initConfig() {
        if (!doesConfigExist()) {
            plugin.saveDefaultConfig();
        }

        //Set the world name
        Constants.WORLD_NAME = Objects.requireNonNull(plugin.getConfig().get("serverConfig.serverName")).toString();
        this.config = plugin.getConfig();
    }

    /**
     * Check if the config file exists
     *
     * @return whether it has already been created
     */
    private boolean doesConfigExist() {
        final var configFile = new File(configPath);

        return configFile.exists();
    }

    public FileConfiguration getConfig() {
        if (config == null) {
            System.out.println("Need to initialize configuration before trying to use it.");
            return null;
        }

        return config;
    }
}
