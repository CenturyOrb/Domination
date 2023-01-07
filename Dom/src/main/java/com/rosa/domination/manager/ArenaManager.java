package com.rosa.domination.manager;

import com.rosa.domination.Domination;
import com.rosa.domination.instance.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    private List<Arena> arenas = new ArrayList<>();

    public ArenaManager(Domination domination)   {
        FileConfiguration config = domination.getConfig();

        // get the arenas in config
        for (String str : config.getConfigurationSection("arenas").getKeys(false))  {

            List<Location> blueSpawns = new ArrayList<>();
            List<Location> redSpawns = new ArrayList<>();

            // add the locations of blue team spawn points from config
            for (String spawn : config.getConfigurationSection("arenas." + str + ".spawns.blue").getKeys(false))    {
                System.out.println(str + " " + spawn);
                Location location = new Location(
                        Bukkit.getWorld(config.getString("arenas." + str + ".spawns.blue." + spawn + ".world")),
                        config.getDouble("arenas." + str + ".spawns.blue." + spawn + ".x"),
                        config.getDouble("arenas." + str + ".spawns.blue." + spawn + ".y"),
                        config.getDouble("arenas." + str + ".spawns.blue." + spawn + ".z"),
                        (float) config.getDouble("arenas." + str + ".spawns.blue." + spawn + ".yaw"),
                        (float) config.getDouble("arenas." + str + ".spawns.blue." + spawn + ".pitch"));

                blueSpawns.add(location);
            }

            // add the locations of red team spawn points from config
            for (String spawn : config.getConfigurationSection("arenas." + str + ".spawns.red").getKeys(false))    {
                Location location = new Location(
                        Bukkit.getWorld(config.getString("arenas." + str + ".spawns.red." + spawn + ".world")),
                        config.getDouble("arenas." + str + ".spawns.red." + spawn + ".x"),
                        config.getDouble("arenas." + str + ".spawns.red." + spawn + ".y"),
                        config.getDouble("arenas." + str + ".spawns.red." + spawn + ".z"),
                        (float) config.getDouble("arenas." + str + ".spawns.red." + spawn + ".yaw"),
                        (float) config.getDouble("arenas." + str + ".spawns.red." + spawn + ".pitch"));

                redSpawns.add(location);
            }

            // create the arena
            arenas.add(new Arena(domination, Integer.parseInt(str), blueSpawns, redSpawns));
        }

    }

    // return the list of arenas
    public List<Arena> getArenas()   { return arenas; }

    // get the arena a player is in
    // return null if the player is in none of the arenas
    public Arena getArena(Player player)   {
        for (Arena arena : arenas)   {
            if (arena.getPlayers().contains(player.getUniqueId()))   {
                return arena;
            }
        }

        return null;
    }

    // get the arena a player is in
    // return null if the player is in none of the arenas
    public Arena getArena(int id)   {
        for (Arena arena : arenas)   {
            if (arena.getId() == id)   {
                return arena;
            }
        }

        return null;
    }

}
