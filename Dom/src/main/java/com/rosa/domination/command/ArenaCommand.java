package com.rosa.domination.command;

import com.rosa.domination.Domination;
import com.rosa.domination.GameState;
import com.rosa.domination.instance.Arena;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ArenaCommand implements CommandExecutor {

    Domination domination;

    public ArenaCommand(Domination domination)   { this.domination = domination; }

    // the /arena commands
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player))   { return false; }
        Player player = (Player) sender;

        // (1) command : /arena list
        // (2) command : /arena leave
        // (3) command : /arena join <id>
        // (4) command specifications were wrong
        if (args.length == 1 & args[0].equalsIgnoreCase("list"))   {
            player.sendMessage(ChatColor.GREEN + "These are the available arenas:");
            for (Arena arena : domination.getArenaManager().getArenas())   {
                player.sendMessage(ChatColor.GREEN + "- " + arena.getId() + " (" + arena.getState().name() + ")");
            }
        }else if (args.length == 1 & args[0].equalsIgnoreCase("leave"))   {
            Arena arena = domination.getArenaManager().getArena(player);
            if (arena != null)   {
                player.sendMessage(ChatColor.RED + "You left the arena.");
                arena.removePlayer(player);
            }else {
                player.sendMessage(ChatColor.RED + "You are not in an arena.");
            }
        }else if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
            if (domination.getArenaManager().getArena(player) != null)   {
                player.sendMessage(ChatColor.RED + "You are already playing in an arena!");
                return false;
            }

            // check if a player /arena join (int)
            int id;
            try {
                id = Integer.parseInt(args[1]);
            } catch (NumberFormatException e)   {
                player.sendMessage(ChatColor.RED + "You specified an invalid arena ID.");
                return false;
            }

            // (1) player did correct /arena join <id>
            // (2) player did incorrect /arena join <id>
            if (id >= 0 && id < domination.getArenaManager().getArenas().size())   {
                Arena arena = domination.getArenaManager().getArena(id);

                // (1) arena is in RECRUITING, add player to that arena
                // (2) arena is in BUY or LIVE, send message
                if (arena.getState() == GameState.RECRUITING)   {
                    player.sendMessage(ChatColor.GREEN + "You are now playing in Arena " + id + ".");
                    arena.addPlayer(player);
                }else   {
                    player.sendMessage(ChatColor.RED + "You can not join this arena right now!");
                }
            }else {
                player.sendMessage(ChatColor.RED + "You specific an invalid arena ID");
            }
        }else {
            player.sendMessage(ChatColor.RED + "Invalid usage! These are the options:");
            player.sendMessage(ChatColor.RED + "- /arena list");
            player.sendMessage(ChatColor.RED + "- /arena leave");
            player.sendMessage(ChatColor.RED + "- /arena join <id>");
        }

        return false;

    }

}
