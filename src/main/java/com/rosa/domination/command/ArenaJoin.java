package com.rosa.domination.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.rosa.domination.Domination;
import com.rosa.domination.enums.GameState;
import com.rosa.domination.instance.Arena;
import com.rosa.domination.utils.Text;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("arena")
public class ArenaJoin extends BaseCommand {
	Domination domination;

	public ArenaJoin(final Domination domination) {
		this.domination = domination;
	}

	@Subcommand("join")
	@CommandCompletion("@arenas")
	@CommandPermission("domination.arena.join")
	public void join(final CommandSender sender, final String arenaId) {
		if (!(sender instanceof Player)) {
			Text.send(sender, "#redThis command can only be used by players!");
			return;
		}

		if (arenaId == null || !(StringUtils.isNumeric(arenaId))) {
			Text.send(sender, "#redInvalid ID!");
			return;
		}

		final int id = Integer.parseInt(arenaId);

		Arena arena;

		if (id >= 0 && id < domination.getArenaManager().getArenas().size()) {
			arena = domination.getArenaManager().getArena(id);
		} else {
			Text.send(sender, "#redInvalid ID!");
			return;
		}

		final Player player = (Player) sender;

		if (arena.getState() == GameState.RECRUITING) {
			Text.tell(player, "#limeYou are now playing in Arena #yellow" + id + "#lime.");
			arena.addPlayer(player);
		} else {
			Text.tell(player, "#redYou can not join this arena right now!");
		}
	}
}
