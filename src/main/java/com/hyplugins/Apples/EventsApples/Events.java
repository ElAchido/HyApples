package com.hyplugins.Apples.EventsApples;

import com.hyplugins.Apples.Apples;
import com.hyplugins.Apples.Runnables.Fireworks;
import com.hyplugins.Apples.Utils.Colors;
import com.hyplugins.Apples.Config.AppleConstructor;
import com.hyplugins.Apples.Config.ListApples;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class Events implements Listener {

    private final Apples apples;

    public Events(Apples apples) {
        this.apples = apples;
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        for (AppleConstructor apple : ListApples.apples) {
            ItemStack item = e.getItem().clone();
            ItemStack appleMade = apple.getMadeApple().clone();
            if (item.getType().equals(appleMade.getType()) && item.getItemMeta().getDisplayName().equals(appleMade.getItemMeta().getDisplayName()) && item.getItemMeta().getLore().equals(appleMade.getItemMeta().getLore())) {
                Player player = e.getPlayer();
                if (apple.getUsePerm()) {
                    if (!player.hasPermission("hyplugins.apples." + apple.getId()) || !player.isOp()) {
                        player.sendMessage(Colors.colorMessageNormal(apples.getConfig().getString("messages.no-perms").replace("%prefix%", apples.getConfig().getString("messages.prefix"))));
                        e.setCancelled(true);
                        return;
                    }
                }
                if (!apple.getDefaultAppleEffects()) {
                    e.setCancelled(true);
                    item.setAmount(1);
                    int newAmount = e.getItem().getAmount() - 1;
                    item.setAmount(newAmount);
                    player.getInventory().setItemInHand(item);
                }
                if (apple.getThunder()) {
                    World world = player.getWorld();
                    world.strikeLightningEffect(player.getLocation());
                }
                sendBroadcastMessage(apple.getIsBroadcastSameWorld(), player.getWorld().getName(), apple.getBroadcast(), player);
                for (PotionEffect effect : apple.getEffects()) {
                    if (player.hasPotionEffect(effect.getType())) player.removePotionEffect(effect.getType());
                    player.addPotionEffect(effect);
                }
                new Fireworks(apple.getFireworks(), player, apples);
                for (String command : apple.getCommands()) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player_apples%", player.getName()));
                }
                break;
            }
        }
    }

    public void sendBroadcastMessage(Boolean inSameWorld, String world, List<String> broadcast, Player origin) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (inSameWorld) { if (!player.getWorld().getName().equals(world)) continue; }
            for (String line : broadcast) {
                if (apples.isPlaceholderAPI) {
                    line = Colors.applyPlaceholderAPI(line, origin);
                }
                player.sendMessage(line.replace("%player_apples%", origin.getName()));
            }
        }
    }
}
