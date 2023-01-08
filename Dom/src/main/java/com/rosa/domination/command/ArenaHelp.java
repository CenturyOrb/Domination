package com.rosa.domination.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.HelpCommand;
import com.rosa.domination.Domination;
import com.rosa.domination.utils.Text;
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

		Text.send(sender, "#redAvailable arena commands:");
		Text.send(sender, "#yellow- /arena list (Shows list of arenas)");
		Text.send(sender, "#yellow- /arena leave (Leave your current arena)");
		Text.send(sender, "#yellow- /arena join <id> (Join an arena)");
	}
}
