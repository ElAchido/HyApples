package com.hyplugins.Apples.AnvilProtection;

import com.hyplugins.Apples.Apples;
import com.hyplugins.Apples.Utils.Colors;
import com.hyplugins.Apples.Config.AppleConstructor;
import com.hyplugins.Apples.Config.ListApples;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class AnvilEvent implements Listener {

    private Apples apples;

    public AnvilEvent(Apples apples) {
        this.apples = apples;
    }

    @EventHandler
    public void onChangeName(InventoryClickEvent e) {
        if (e.getInventory().getType().equals(InventoryType.ANVIL) && e.getCurrentItem() != null) {
            ItemStack item = e.getCurrentItem();
            item.setAmount(1);
            for (AppleConstructor apple : ListApples.apples) {
                if (apple.getMadeApple().equals(item)) {
                    e.setCancelled(true);
                    e.getWhoClicked().getOpenInventory().close();
                    e.getWhoClicked().sendMessage(Colors.colorMessageNormal(apples.getConfig().getString("messages.anvil-block").replace("%prefix%", apples.getConfig().getString("messages.prefix"))));
                    return;
                }
            }
        }
    }
}
