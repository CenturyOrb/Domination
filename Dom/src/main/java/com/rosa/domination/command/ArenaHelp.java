package com.rosa.domination.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.HelpCommand;
import com.rosa.domination.Domination;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("arena")
public class ArenaHelp extends BaseCommand {
	Domination domination;

	public ArenaHelp(final Domination domination) {
		this.domination = domination;
	}

	@HelpCommand
	@CommandPermission("domination.arena.help")
	public void help(final CommandSender sender) {
		if (!(sender instanceof Player))   { return; }
		final Player player = (Player) sender;

		player.sendMessage("#redAvailable arena commands:");
		player.sendMessage("#yellow- /arena list (Shows list of arenas)");
		player.sendMessage("#yellow- /arena leave (Leave your current arena)");
		player.sendMessage("#yellow- /arena join <id> (Join an arena)");
	}
}
