package com.rosa.domination.instance;

import com.rosa.domination.Domination;
import com.rosa.domination.GameState;
import com.rosa.domination.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Arena {

    private Domination domination;

    private int id;
    private List<Location> blueSpawns;
    private List<Location> redSpawns;

    private GameState state;
    private List<UUID> players;
    private List<UUID> bluePlayers;
    private List<UUID> redPlayers;
    private Countdown countdown;
    private Game game;

    public Arena(Domination domination, int id, List<Location> blueSpawns, List<Location> redSpawns)   {
        this.domination = domination;

        this.id = id;
        this.blueSpawns = blueSpawns;
        this.redSpawns = redSpawns;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.bluePlayers = new ArrayList<>();
        this.redPlayers = new ArrayList<>();
        this.countdown = new Countdown(domination, this);
        this.game = new Game(this);
    }

    // runs the start command in the Game class
    // teleport the players into the arena
    public void start()   {
        int i = 0;
        for (UUID uuid : bluePlayers)   {
            Bukkit.getPlayer(uuid).teleport(blueSpawns.get(i));
            i++;
        }
        i = 0;

        for (UUID uuid : redPlayers)   {
            Bukkit.getPlayer(uuid).teleport(redSpawns.get(i));
            i++;
        }

        game.start();
    }

    // reset the game
    public void reset(boolean kickPlayers)   {
        // send them back to hub and remove them from all player lists
        if (kickPlayers)   {
            Location location = ConfigManager.getHubSpawn();

            for (UUID uuid : players)   {
                Bukkit.getPlayer(uuid).teleport(location);
            }

            players.clear();
            bluePlayers.clear();
            redPlayers.clear();
        }

        sendTitle("", "");
        state = GameState.RECRUITING;
        countdown.cancel();
        countdown = new Countdown(domination, this);
        game = new Game(this);
    }

    // add player to the lobby, to a random team
    // teleport player to the
    public void addPlayer(Player player)   {
        Random random = new Random();

        // add player to the players list
        players.add(player.getUniqueId());
        // teleport them to the lobby
        player.teleport(ConfigManager.getLobbySpawn());

        // put them into a team, check if one team is full first
        if (bluePlayers.size() == ConfigManager.getRequiredPlayers() / 2)   {
            redPlayers.add(player.getUniqueId());
        }else if (redPlayers.size() == ConfigManager.getRequiredPlayers() / 2)   {
            bluePlayers.add(player.getUniqueId());
        }else {
            if (random.nextBoolean())   {
                bluePlayers.add(player.getUniqueId());
            }else {
                redPlayers.add(player.getUniqueId());
            }
        }

        // check if the lobby has enough players, if so start countdown
        if (state.equals(GameState.RECRUITING) && players.size() >= ConfigManager.getRequiredPlayers())   {
            countdown.start();
        }
    }

    // remove player from players, respective team list
    // send player to hub
    // update the game depending on player action/count
    public void removePlayer(Player player)   {
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getHubSpawn());
        player.sendTitle("", "");

        if (bluePlayers.contains(player.getUniqueId()))   {
            bluePlayers.remove(player.getUniqueId());
        }else if (redPlayers.contains(player.getUniqueId()))   {
            redPlayers.remove(player.getUniqueId());
        }

        // if players are in the buy phase and someone leaves
        // reset the arena back to RECRUITING
        if (state == GameState.BUY && players.size() < ConfigManager.getRequiredPlayers())   {
            sendMessage(ChatColor.RED + "There is not enough players. Countdown stopped");
            reset(false);
            return;
        }

        // if all players on a team left
        if (bluePlayers.size() == 0)   {
            game.setRedPoints(100);
        }
        if (redPlayers.size() == 0)   {
            game.setBluePoints(100);
        }
    }

    // send message to all players in the players list
    public void sendMessage(String message)   {
        for (UUID uuid : players)   {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    // send title to all players in the title list
    public void sendTitle(String title, String subtitle)   {
        for (UUID uuid : players)   {
            Bukkit.getPlayer(uuid).sendTitle(title, subtitle);
        }
    }

    public int getId()   { return id; }
    public List<Location> getBlueSpawns()   { return blueSpawns; }
    public List<Location> getRedSpawns()   { return redSpawns; }

    public GameState getState()   { return state; }
    public List<UUID> getPlayers()   { return players;}
    public List<UUID> getBluePlayers()   { return bluePlayers; }
    public List<UUID> getRedPlayers()   { return redPlayers; }
    public Game getGame()   { return game; }

    // set the arena's state
    public void setState(GameState state)   { this.state = state;}
}
