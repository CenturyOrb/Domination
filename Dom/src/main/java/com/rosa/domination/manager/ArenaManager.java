package com.rosa.domination.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.rosa.domination.Domination;
import com.rosa.domination.instance.Arena;

public class ArenaManager {

	private final List<Arena> arenas = new ArrayList<>();

	public ArenaManager(final Domination domination) {
		final FileConfiguration config = domination.getConfig();

		// get the arenas in config
		for (final String str : config.getConfigurationSection("arenas").getKeys(false)) {

			final List<Location> blueSpawns = new ArrayList<>();
			final List<Location> redSpawns = new ArrayList<>();

			// add the locations of blue team spawn points from config
			for (final String spawn : config.getConfigurationSection("arenas." + str + ".spawns.blue").getKeys(false)) {
				System.out.println(str + " " + spawn);
				final Location location = new Location(Bukkit.getWorld(config.getString("arenas." + str + ".spawns.blue." + spawn + ".world")),
						config.getDouble("arenas." + str + ".spawns.blue." + spawn + ".x"), config.getDouble("arenas." + str + ".spawns.blue." + spawn + ".y"),
						config.getDouble("arenas." + str + ".spawns.blue." + spawn + ".z"), (float) config.getDouble("arenas." + str + ".spawns.blue." + spawn + ".yaw"),
						(float) config.getDouble("arenas." + str + ".spawns.blue." + spawn + ".pitch"));

				blueSpawns.add(location);
			}

			// add the locations of red team spawn points from config
			for (final String spawn : config.getConfigurationSection("arenas." + str + ".spawns.red").getKeys(false)) {
				final Location location = new Location(Bukkit.getWorld(config.getString("arenas." + str + ".spawns.red." + spawn + ".world")),
						config.getDouble("arenas." + str + ".spawns.red." + spawn + ".x"), config.getDouble("arenas." + str + ".spawns.red." + spawn + ".y"),
						config.getDouble("arenas." + str + ".spawns.red." + spawn + ".z"), (float) config.getDouble("arenas." + str + ".spawns.red." + spawn + ".yaw"),
						(float) config.getDouble("arenas." + str + ".spawns.red." + spawn + ".pitch"));

				redSpawns.add(location);
			}

			// create the arena
			arenas.add(new Arena(domination, Integer.parseInt(str), blueSpawns, redSpawns));
		}

		//Set command completions
		setArenaCompletions(domination);
	}

	//Sets command completions for the arena ids
	public void setArenaCompletions(final Domination domination) {
		domination.getCommandManager().getCommandCompletions().registerAsyncCompletion("arenas", c -> getArenaIds());
	}

	// return the list of arenas
	public List<Arena> getArenas() {
		return arenas;
	}

	//Return the list of arena ids
	public List<String> getArenaIds() {
		final List<String> ids = new ArrayList<String>();
		for (final Arena arena : arenas) {
			ids.add(String.valueOf(arena.getId()));
		}
		return ids;
	}

	// get the arena a player is in
	// return null if the player is in none of the arenas
	public Arena getArena(final Player player) {
		for (final Arena arena : arenas) {
			if (arena.getPlayers().contains(player.getUniqueId())) {
				return arena;
			}
		}

		return null;
	}

	// get the arena a player is in
	// return null if the player is in none of the arenas
	public Arena getArena(final int id) {
		for (final Arena arena : arenas) {
			if (arena.getId() == id) {
				return arena;
			}
		}

		return null;
	}

}
