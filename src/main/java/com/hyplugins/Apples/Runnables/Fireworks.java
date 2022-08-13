package com.hyplugins.Apples.Runnables;

import com.hyplugins.Apples.Apples;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Fireworks extends BukkitRunnable implements Listener {

    private final List<FireworkEffect> fireworks;
    private final Player player;
    private int count;

    public Fireworks(List<FireworkEffect> fireworks, Player player, Apples apples) {
        this.fireworks = fireworks;
        this.player = player;
        count = fireworks.size();
        this.runTaskTimer(apples, 0, 10);
    }

    @Override
    public void run() {
        Firework firework = player.getWorld().spawn(player.getLocation(), Firework.class);
        firework.setCustomName("Apple's Firework");
        FireworkMeta meta = firework.getFireworkMeta();
        meta.addEffect(fireworks.get(count - 1));
        firework.setFireworkMeta(meta);
        count--;
        if (count == 0) {
            cancel();
        }
    }
}
