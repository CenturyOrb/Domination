package com.rosa.domination.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rosa.domination.manager.ConfigManager;

import net.md_5.bungee.api.ChatColor;

public class Text {
	/**
	 * Sends a message to all online players.
	 *
	 * @param message
	 */
	public static void all(final String message) {
		if (message == "" || message == null) {
			return;
		}

		for (final Player player : Bukkit.getOnlinePlayers()) {
			tell(player, message);
		}
	}

	/**
	 * Sends a message to all online staff.
	 *
	 * @param message
	 */
	public static void staff(final String message) {
		if (message == "" || message == null) {
			return;
		}

		for (final Player player : Bukkit.getOnlinePlayers()) {
			if (player.hasPermission("domination.staff")) {
				tell(player, message);
			}
		}
	}

	/**
	 * Send a player a message.
	 *
	 * @param player
	 * @param message
	 */
	public static void tell(final Player player, final String message) {
		if (message == "" || message == null) {
			return;
		}

		player.sendMessage(color(ConfigManager.getPrefix() + message));
	}

	/**
	 * Send a command sender a message.
	 *
	 * @param sender
	 * @param message
	 */
	public static void send(final CommandSender sender, final String message) {
		if (message == "" || message == null) {
			return;
		}

		sender.sendMessage(color(ConfigManager.getPrefix() + message));
	}

	/**
	 * Send console a message.
	 *
	 * @param message
	 */
	public static void console(final String message) {
		//If message is empty
		if (message == "" || message == null) {
			return;
		}

		System.out.println(color(ConfigManager.getPrefix() + message));
	}

	private static final Pattern rgbPattern = Pattern.compile("&#[a-fA-F0-9]{6}");

	/**
	 * Convert color codes into color.
	 *
	 * @param msg
	 * @return coloredMsg
	 */
	public static String color(String msg) {
		if (msg.contains("#")) {
			msg = msg.replaceAll("#white", "&#FFFFFF");
			msg = msg.replaceAll("#black", "&#000000");
			msg = msg.replaceAll("#red", "&#FF0000");
			msg = msg.replaceAll("#orange", "&#FFA500");
			msg = msg.replaceAll("#yellow", "&#FFD700");
			msg = msg.replaceAll("#green", "&#008000");
			msg = msg.replaceAll("#blue", "&#0000FF");
			msg = msg.replaceAll("#purple", "&#4B0082");
			msg = msg.replaceAll("#pink", "&#FF69B4");
			msg = msg.replaceAll("#aqua", "&#00FFFF");
			msg = msg.replaceAll("#lime", "&#00FF00");
			msg = msg.replaceAll("#brown", "&#964B00");
		}

		String version = StringUtils.substringBetween(Bukkit.getVersion(), "(MC: 1.", ")");
		if (version.contains(".")) {
			version = StringUtils.substringBefore(version, ".");
		}

		if (version.matches("^0*(?:[1-9][0-9]{2,}|[2-9][0-9]|1[6-9])$")) {
			Matcher rgbMatch = rgbPattern.matcher(msg);
			while (rgbMatch.find()) {
				final String color = msg.substring(rgbMatch.start(), rgbMatch.end());
				msg = msg.replace(color, ChatColor.of(color.substring(1)) + "");
				rgbMatch = rgbPattern.matcher(msg);
			}
		}
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

}
