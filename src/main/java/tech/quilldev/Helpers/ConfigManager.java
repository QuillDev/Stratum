package tech.quilldev.Helpers;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    private final FileConfiguration config;
    private final static String configPath = "./plugins/Stratum/stratumConfig.yml";

    private final ArrayList<String> worlds = new ArrayList<>();

    /**
     * Manage the config for the given plugin
     *
     * @param plugin to manage the config for
     */
    public ConfigManager(Plugin plugin) {
        this.config = plugin.getConfig();
        this.loadConfig();

        //Populate the worlds array
        final var worldObjects = (ArrayList<?>) this.config.getList("worlds", new ArrayList<String>());
        assert worldObjects != null;
        for (final var world : worldObjects) {
            this.worlds.add(world.toString());
        }
    }

    /***
     * If the list has the given world
     * @param name of the world
     * @return the world
     */
    public boolean hasWorld(String name) {
        for (final var world : this.getWorlds()) {
            if (world == name) return true;
        }

        return false;
    }

    public ArrayList<String> getWorlds() {
        return worlds;
    }

    public void addWorld(String worldName) {
        this.worlds.add(worldName);
    }

    public void loadConfig() {

        try {
            this.config.load(configPath);
        } catch (IOException | InvalidConfigurationException e) {
            this.saveDefaultConfig();
        }

    }

    public void saveDefaultConfig() {
        this.config.set("worlds", new ArrayList<String>());
        save();
    }

    public void save() {
        try {
            this.config.save(configPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
