package com.rosa.domination.command;

import com.rosa.domination.Domination;
import com.rosa.domination.instance.Arena;
import com.rosa.domination.instance.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestingCommand implements CommandExecutor {

    private Domination domination;

    public TestingCommand(Domination domination) { this.domination = domination; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)   {
        if (!(sender instanceof Player))   { return false; }
        Player player = (Player) sender;
        Arena arena = domination.getArenaManager().getArena(player);
        Game game = arena.getGame();

        game.setBluePoints(100);

        return false;
    }
}
