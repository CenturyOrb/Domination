package com.rosa.domination;

import com.rosa.domination.command.ArenaCommand;
import com.rosa.domination.command.TestingCommand;
import com.rosa.domination.manager.ArenaManager;
import com.rosa.domination.manager.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Domination extends JavaPlugin {

    private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        ConfigManager.setUpConfig(this);
        arenaManager = new ArenaManager(this);

        getCommand("arena").setExecutor(new ArenaCommand(this));
        getCommand("bluewins").setExecutor(new TestingCommand(this));
    }

    public ArenaManager getArenaManager()   { return arenaManager; }

}
