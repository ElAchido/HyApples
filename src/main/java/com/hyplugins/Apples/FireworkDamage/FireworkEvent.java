package com.hyplugins.Apples.FireworkDamage;

import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class FireworkEvent implements Listener {

    @EventHandler
    public void onDamageFirework(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Firework && e.getEntity() instanceof Player) {
            Firework firework = (Firework) e.getDamager();
            if (firework.getCustomName().equals("Apple's Firework")) {
                e.setCancelled(true);
            }
        }
    }
}
