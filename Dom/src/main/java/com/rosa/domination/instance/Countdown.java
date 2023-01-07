package com.rosa.domination.instance;

import com.rosa.domination.Domination;
import com.rosa.domination.GameState;
import com.rosa.domination.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private Domination domination;
    private Arena arena;
    private int countdownSeconds;

    public Countdown(Domination domination,  Arena arena)   {
        this.domination = domination;
        this.arena = arena;
        this.countdownSeconds = ConfigManager.getCountdownSeconds();
    }

    // set arena state to BUY
    // start method that starts the buying stage
    //
    public void start()   {
        arena.setState(GameState.BUY);
        runTaskTimer(domination, 0, 20);
    }

    @Override
    public void run() {
        // once buy timer runs out teleport the players into
        // the arena
        if (countdownSeconds == 0)   {
            cancel();
            arena.start();
            return;
        }

        if (countdownSeconds <= 10 ||  countdownSeconds % 15 == 0) {
            arena.sendMessage(ChatColor.GREEN + "Game will start in " + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s" ) + ".");
        }

        arena.sendTitle(ChatColor.GREEN.toString() + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s"), ChatColor.GRAY + " until game starts");

        countdownSeconds--;
    }
}
