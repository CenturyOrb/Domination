package com.rosa.domination.instance;

import com.rosa.domination.GameState;
import com.rosa.domination.Team;
import org.bukkit.ChatColor;

public class Game {

    private Arena arena;
    private int bluePoints;
    private int redPoints;

    public Game(Arena arena)    {
        this.arena = arena;

        bluePoints = 0;
        redPoints = 0;
    }

    // start the game, send message and set the points to 0
    public void start()   {
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aBattle of &7Halcyon Valley &ahas begun!"));
    }

    // add point to team
    // if team has 100 points they win
    // reset the arena
    public void addPoint(Team team) {
        // add point to the correct team
        if (team == Team.RED) { redPoints++; }
        if (team == Team.BLUE) { bluePoints++; }

        // add code here that updates the scoreboard display

        // check if team has enough points to win
        if (bluePoints >= 100 && redPoints >= 100)   {
            arena.sendTitle(ChatColor.GOLD + "TIE", "");
            arena.reset(true);
            return;
        }else if (bluePoints >= 100) {
           arena.sendTitle(ChatColor.BLUE + "BLUE HAS WON", "");
           arena.reset(true);
           return;
        }else if (redPoints >= 100)   {
            arena.sendTitle(ChatColor.RED + "RED HAS WON", "");
            arena.reset(true);
            return;
        }
    }

    // set blue team's points
    public void setBluePoints(int points)   {
        bluePoints = points;
        if (bluePoints >= 100) {
            arena.sendTitle(ChatColor.BLUE + "BLUE HAS WON", "");
            arena.reset(true);
        }
    }

    // set red team's points
    public void setRedPoints(int points)   {
        redPoints = points;
    }
}
