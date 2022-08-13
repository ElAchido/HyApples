package com.hyplugins.Apples.Utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Colors {

    public static String colorMessageNormal(String message) { return ChatColor.translateAlternateColorCodes('&', message); }

    public static String applyPlaceholderAPI(String message, Player player) { return PlaceholderAPI.setPlaceholders(player, message); }

    public static void sendMessage(String message, CommandSender sender, String prefix) {
        String messagePrefix = message.replace("%prefix%", ChatColor.translateAlternateColorCodes('&', prefix));
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix));
        } else if (sender instanceof ConsoleCommandSender) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix));
        }
    }
}
