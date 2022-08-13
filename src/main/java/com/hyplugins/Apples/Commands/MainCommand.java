package com.hyplugins.Apples.Commands;

import com.hyplugins.Apples.Apples;
import com.hyplugins.Apples.Utils.Colors;
import com.hyplugins.Apples.Config.AppleConstructor;
import com.hyplugins.Apples.Config.ListApples;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainCommand implements CommandExecutor, TabCompleter {

    public final Apples apples;
    public YamlConfiguration config;

    public MainCommand(Apples apples) {
        this.apples = apples;
        config = apples.config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender || sender.hasPermission("apples.admin") || sender.isOp()) {
            if (args.length == 4) {
                if (args[0].equals("give")) {
                    for (AppleConstructor apple : ListApples.apples) {
                        if (apple.getId().equals(args[1])) {
                            if (Bukkit.getPlayer(args[3]) != null) {
                                if (Bukkit.getPlayer(args[3]).isOnline()) {
                                    Player target = Bukkit.getPlayer(args[3]);
                                    ItemStack appleCase = apple.getMadeApple().clone();
                                    try {
                                        appleCase.setAmount(Integer.parseInt(args[2]));
                                    } catch (NumberFormatException e) {
                                        Colors.sendMessage(config.getString("messages.invalid-number"), sender, config.getString("messages.prefix"));
                                        return false;
                                    }
                                    target.getInventory().addItem(appleCase);
                                    Colors.sendMessage(config.getString("messages.give-apple").replace("%player_apples%", target.getName()), sender, config.getString("messages.prefix"));
                                    String message = config.getString("messages.receive-apple").replace("%amount%", String.valueOf(appleCase.getAmount()));
                                    message = message.replace("%prefix%", config.getString("messages.prefix"));
                                    target.sendMessage(Colors.colorMessageNormal(message.replace("%apple%", apple.getName())));
                                } else {
                                    Colors.sendMessage(config.getString("messages.player-not-online").replace("%player_apples%", args[3]), sender, config.getString("messages.prefix"));
                                }
                            } else {
                                Colors.sendMessage(config.getString("messages.player-not-found").replace("%player_apples%", args[3]), sender, config.getString("messages.prefix"));
                            }
                            return false;
                        }
                    }
                    Colors.sendMessage(config.getString("messages.apple-not-found").replace("%appleID%", args[1]), sender, config.getString("messages.prefix"));
                } else {
                    Colors.sendMessage(config.getString("messages.invalid-command"), sender, config.getString("messages.prefix"));
                }
            } else if (args.length == 1) {
                if (args[0].equals("reload")) {
                    apples.configClass.reloadConfig();
                    Colors.sendMessage(config.getString("messages.reload-config"), sender, config.getString("messages.prefix"));
                } else if (args[0].equals("gui") && sender instanceof Player) {
                    apples.gui.showApplesGUI(1, ((Player) sender).getPlayer());
                } else {
                    Colors.sendMessage(config.getString("messages.invalid-command"), sender, config.getString("messages.prefix"));
                }
            } else {
                Colors.sendMessage(config.getString("messages.invalid-command"), sender, config.getString("messages.prefix"));
            }
        } else {
            Colors.sendMessage(config.getString("no-command-perms"), sender, config.getString("messages.prefix"));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> arguments = new ArrayList<>();

        if (sender instanceof Player) {
            if (sender.hasPermission("apples.admin")) {
                if (args.length == 1) {
                    arguments.add("reload");
                    arguments.add("give");
                    arguments.add("gui");
                    return arguments.stream().filter(m -> m.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
                } else if (args.length == 2 && args[0].equals("give")) {
                    for (AppleConstructor constructor : ListApples.apples) {
                        arguments.add(constructor.getId());
                    }
                    return arguments.stream().filter(m -> m.startsWith(args[1])).collect(Collectors.toList());
                } else if (args.length == 3 && args[0].equals("give")) {
                    arguments.add("Amount");
                } else if (args.length == 4 && args[0].equals("give")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        arguments.add(player.getName());
                    }
                    return arguments.stream().filter(m -> m.startsWith(args[3])).collect(Collectors.toList());
                }
            }
        }

        return arguments;
    }
}
