package com.rosa.domination.command;

import org.bukkit.command.CommandSender;

import com.rosa.domination.Domination;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.HelpCommand;

public class ArenaHelp extends BaseCommand {
	Domination domination;

	public ArenaHelp(final Domination domination) {
		this.domination = domination;
	}

	@HelpCommand
	@CommandPermission("domination.arena.help")
	public void help(final CommandSender sender) {
		//todo if needed :D
	}
}
