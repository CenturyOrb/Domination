package com.rosa.domination;

import Item.CreateAndStorage.Armor.CustomArmorManager;
import Item.CreateAndStorage.Offense.CustomOffenseManager;
import com.rosa.domination.instance.Zone;
import com.rosa.domination.shops.*;
import com.rosa.domination.utils.PlayerEvents;
import com.rosa.serverplugin.API;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.rosa.domination.command.ArenaHelp;
import com.rosa.domination.command.ArenaJoin;
import com.rosa.domination.command.ArenaLeave;
import com.rosa.domination.command.ArenaList;
import com.rosa.domination.command.TestingCommand;
import com.rosa.domination.manager.ArenaManager;
import com.rosa.domination.manager.ConfigManager;

import co.aikar.commands.PaperCommandManager;

public final class Domination extends JavaPlugin {

	private ArenaManager arenaManager;
	private PaperCommandManager commandManager;

	@Override
	public void onEnable() {
		ConfigManager.setUpConfig(this);
		commandManager = new PaperCommandManager(this);

		arenaManager = new ArenaManager(this);

		getCommand("bluewins").setExecutor(new TestingCommand(this));

		registerCommands();

		Bukkit.getScheduler().runTaskLater(this, () -> {register();}, 5);
	}

	public void register() {
		API api = Bukkit.getServicesManager().load(API.class);
		CustomOffenseManager offenseManager = api.getOffenseManager();
		CustomArmorManager armorManager = api.getArmorManager();
		Bukkit.getPluginManager().registerEvents(new WeapomSmith(offenseManager), this);
		Bukkit.getPluginManager().registerEvents(new ArmorSmith(armorManager), this);
		Bukkit.getPluginManager().registerEvents(new Fisherman(), this);
		Bukkit.getPluginManager().registerEvents(new Farmer(), this);
		Bukkit.getPluginManager().registerEvents(new Butcher(), this);

		Bukkit.getPluginManager().registerEvents(new PlayerEvents(this), this);
	}

	public ArenaManager getArenaManager() {
		return arenaManager;
	}

	public PaperCommandManager getCommandManager() {
		return commandManager;
	}

	public void registerCommands() {
		commandManager.registerCommand(new ArenaHelp(this));
		commandManager.registerCommand(new ArenaJoin(this));
		commandManager.registerCommand(new ArenaLeave(this));
		commandManager.registerCommand(new ArenaList(this));
	}

}
