package com.rosa.domination.instance;

import com.rosa.domination.Domination;
import com.rosa.domination.enums.GameState;
import com.rosa.domination.enums.Team;
import com.rosa.domination.enums.ZoneState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class Game {

    private Domination domination;

    private Arena arena;
    private int bluePoints;
    private int redPoints;

    BukkitTask zonePlayerDetector;
    BukkitTask scoreCounter;

    public Game(Domination domination, Arena arena)    {

        this.domination = domination;

        this.arena = arena;

        bluePoints = 0;
        redPoints = 0;
    }

    // start the game, send message and set the points to 0
    public void start()   {
        arena.setState(GameState.LIVE);
        startZoneDetector();
        startScoreCounter();
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
           zonePlayerDetector.cancel();
           scoreCounter.cancel();
            for (Zone zone : arena.getZones())   { zone.reset(); }
           return;
        }else if (redPoints >= 100)   {
            arena.sendTitle(ChatColor.RED + "RED HAS WON", "");
            arena.reset(true);
            zonePlayerDetector.cancel();
            scoreCounter.cancel();
            for (Zone zone : arena.getZones())   { zone.reset(); }
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
        if (redPoints >= 100) {
            arena.sendTitle(ChatColor.RED + "RED HAS WON", "");
            arena.reset(true);
        }
    }

    private void startScoreCounter()   {
        scoreCounter = Bukkit.getScheduler().runTaskTimer(domination, () -> {

            List<Zone> zones = arena.getZones();

            // every 3 seconds check zones and add a point for team that owns that zone
            for (Zone zone : zones)   {

                if (zone.getZoneState() == ZoneState.BLUE)   {
                    addPoint(Team.BLUE);
                    System.out.println("Added point for team blue: " + bluePoints);
                }else if (zone.getZoneState() == ZoneState.RED)   {
                    addPoint(Team.RED);
                    System.out.println("Added point for team red: " + redPoints);
                }

            }

        }, 0, 60);

    }

    private void startZoneDetector()   {
        zonePlayerDetector = Bukkit.getScheduler().runTaskTimer(domination, () -> {

            Map<Player, Zone> playersInZones = new HashMap<Player, Zone>();

            List<Player> listOfPlayersInArena = new ArrayList<>();
            for (UUID playerUUID : arena.getPlayers())   {
                listOfPlayersInArena.add(getPlayerByUuid(playerUUID));
            }

            // loop through all players checking if they are in a zone
            for (Player player : listOfPlayersInArena)   {
                for (Zone zone : arena.getZones())   {
                    if (zone.getCuboid().contains(player.getLocation()))   {
                        playersInZones.put(player, zone);
                        break;
                    }
                }
            }

            // for each zone
            for (Zone zone : arena.getZones())   {

                // check the amount of players for each zone
                List<Player> playersInZone = new ArrayList<>();
                for (Player player : playersInZones.keySet())   {
                    if (playersInZones.get(player) == zone)   {
                        playersInZone.add(player);
                    }
                }

                // if the amount is just 1 get the players team
                if (playersInZone.size() == 1)   {
                    // if that player is in the blue team
                    if (arena.getBluePlayers().contains(playersInZone.get(0).getUniqueId()))   {
                        zone.incrementBlueCaptureProgress();
                    }else   {
                        zone.incrementRedCaptureProgress();
                    }

                }else if (playersInZone.size() > 1)   {

                    int numOfBluePlayers = 0;
                    int numOfRedPlayers = 0;

                    // count blue/red players
                    for (Player player : playersInZone)   {
                        // if a player is in blue team
                        if (arena.getBluePlayers().contains(player.getUniqueId()))   {
                            numOfBluePlayers++;
                        }else   {
                            numOfRedPlayers++;
                        }
                    }

                    // if only blue are in a zone
                    if (numOfBluePlayers == 0)   {
                        zone.incrementRedCaptureProgress();
                    }else if (numOfRedPlayers == 0)   {
                        zone.incrementBlueCaptureProgress();
                    }

                }

            }
        }, 0, 5);
    }

    private Player getPlayerByUuid(UUID uuid) {
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(p.getUniqueId().equals(uuid))   {
            return p;
            }
        }
        return null;
    }

}
