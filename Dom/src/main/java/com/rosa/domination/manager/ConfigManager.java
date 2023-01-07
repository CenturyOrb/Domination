package com.rosa.domination.manager;

import com.rosa.domination.Domination;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private static FileConfiguration config;

    // sets up the config
    public static void setUpConfig(Domination domination)   {
        domination.saveDefaultConfig();;
        ConfigManager.config = domination.getConfig();
    }

    // get required players for game to start
    public static int getRequiredPlayers()   { return config.getInt("required-players"); }

    // get seconds for the countdown
    public static int getCountdownSeconds()   { return config.getInt("countdown-seconds"); }

    // get location of lobby
    public static Location getLobbySpawn()   {
        return new Location(
                Bukkit.getWorld(config.getString("lobby.world")),
                config.getDouble("lobby.x"),
                config.getDouble("lobby.y"),
                config.getDouble("lobby.z"),
                (float) config.getDouble("lobby.yaw"),
                (float) config.getDouble("lobby.pitch"));
    }

    // get location of hub
    public static Location getHubSpawn()   {
        return new Location(
                Bukkit.getWorld(config.getString("hub.world")),
                config.getDouble("hub.x"),
                config.getDouble("hub.y"),
                config.getDouble("hub.z"),
                (float) config.getDouble("hub.yaw"),
                (float) config.getDouble("hub.pitch"));
    }
    
    //Get plugin prefix
    public static String getPrefix() {
    	return config.getString("prefix");
    }
}
