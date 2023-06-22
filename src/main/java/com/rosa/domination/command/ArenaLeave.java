package com.rosa.domination.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rosa.domination.Domination;
import com.rosa.domination.instance.Arena;
import com.rosa.domination.utils.Text;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;

@CommandAlias("arena")
public class ArenaLeave extends BaseCommand {
	Domination domination;

	public ArenaLeave(final Domination domination) {
		this.domination = domination;
	}

	@Subcommand("leave")
	@CommandPermission("domination.arena.leave")
	public void leave(final CommandSender sender) {
		if (!(sender instanceof Player)) {
			Text.send(sender, "#redThis command can only be used by players!");
			return;
		}

		final Player player = (Player) sender;

		final Arena arena = domination.getArenaManager().getArena(player);
		if (arena != null) {
			Text.tell(player, "#redYou left the arena. Jass command");
			arena.removePlayer(player);
		} else {
			Text.tell(player, "#redYou are not in an arena.");
		}
	}
}
