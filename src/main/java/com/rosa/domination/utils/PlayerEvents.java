package com.rosa.domination.utils;

import com.rosa.domination.Domination;
import com.rosa.domination.instance.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class PlayerEvents implements Listener {

    Domination domination;

    public PlayerEvents(Domination domination)   {

        this.domination = domination;

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e)   {

        Player player = e.getPlayer();

        Arena playerArena = domination.getArenaManager().getArena(player);

        // if player was not in an arena
        if (playerArena == null)   { return; }

        playerArena.removePlayer(player);

    }

}
