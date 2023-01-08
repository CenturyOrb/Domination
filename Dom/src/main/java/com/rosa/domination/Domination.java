package com.rosa.domination;

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
