package com.rosa.domination.command;

import org.bukkit.command.CommandSender;

import com.rosa.domination.Domination;
import com.rosa.domination.instance.Arena;
import com.rosa.domination.utils.Text;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;

@CommandAlias("arena")
public class ArenaList extends BaseCommand {
	Domination domination;

	public ArenaList(final Domination domination) {
		this.domination = domination;
	}

	@Subcommand("list")
	@CommandPermission("domination.arena.list")
	public void list(final CommandSender sender) {
		Text.send(sender, "&7Available arenas:");
		for (final Arena arena : domination.getArenaManager().getArenas()) {
			Text.send(sender, "&7- #yellow" + arena.getId() + " &7(#lime" + arena.getState().name() + "&7)");
		}
	}
}
