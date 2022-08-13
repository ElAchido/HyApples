package com.hyplugins.Apples.Menu;

import com.hyplugins.Apples.Apples;
import com.hyplugins.Apples.Utils.Colors;
import com.hyplugins.Apples.Utils.XMaterial;
import com.hyplugins.Apples.Config.AppleConstructor;
import com.hyplugins.Apples.Config.ListApples;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.stream.Collectors;

public class EventsEditor implements Listener {

    public final Apples apples;
    public OptionsApples optionsApples;
    public ApplesShow applesShow;

    public EventsEditor(Apples apples) {
        this.apples = apples;
        optionsApples = new OptionsApples();
        applesShow = apples.gui;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(Colors.colorMessageNormal("&6&lAPPLES &8| Settings"))) {
            if (e.getCurrentItem() != null) {
                if (!e.getCurrentItem().getType().equals(XMaterial.IRON_DOOR.parseMaterial()) && !e.getCurrentItem().getType().equals(XMaterial.ARROW.parseMaterial())) {
                    e.setCancelled(true);
                    List<AppleConstructor> apples = ListApples.apples.stream().filter(a -> a.getId().equals(e.getCurrentItem().getItemMeta().getDisplayName())).collect(Collectors.toList());
                    if (!apples.isEmpty()) {
                        e.getWhoClicked().getOpenInventory().close();
                        optionsApples.getAppleSettings(((Player)e.getWhoClicked()).getPlayer(), apples.get(0));
                    }
                } else {
                    e.setCancelled(true);
                    switch (e.getCurrentItem().getType()) {
                        case ARROW:
                            ItemMeta metaArrow = e.getCurrentItem().getItemMeta();
                            String[] args = metaArrow.getDisplayName().split(" ");
                            int page = Integer.parseInt(args[2]);
                            if (e.getClick().equals(ClickType.LEFT) && page - 1 > 0) {
                                e.getWhoClicked().getOpenInventory().close();
                                applesShow.showApplesGUI(page - 1, (Player) e.getWhoClicked());
                            } else if (e.getClick().equals(ClickType.RIGHT)) {
                                e.getWhoClicked().getOpenInventory().close();
                                applesShow.showApplesGUI(page + 1, (Player) e.getWhoClicked());
                            }
                            break;
                        case IRON_DOOR:
                            e.getWhoClicked().getOpenInventory().close();
                            break;
                    }
                }
            }
        } else if (e.getView().getTitle().equals(Colors.colorMessageNormal("&6&lAPPLE &8| Editing..."))) {
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);
                List<AppleConstructor> applesList = ListApples.apples.stream().filter(a -> a.getMadeApple().equals(e.getClickedInventory().getItem(4))).collect(Collectors.toList());
                AppleConstructor apple = applesList.get(0);
                switch (e.getCurrentItem().getType()) {
                    case NAME_TAG:
                        ItemMeta meta1 = e.getClickedInventory().getItem(e.getRawSlot()).getItemMeta();
                        meta1.setDisplayName(Colors.colorMessageNormal("&fPermission&7: &c" + !apple.getUsePerm()));
                        e.getClickedInventory().getItem(e.getRawSlot()).setItemMeta(meta1);
                        apple.setUsePerm(!apple.getUsePerm());
                        apples.configClass.saveConfigChanges("apples." + apple.getId() + ".use_perm", apple.getUsePerm());
                        break;
                    case WATER_BUCKET:
                        ItemMeta meta2 = e.getClickedInventory().getItem(e.getRawSlot()).getItemMeta();
                        meta2.setDisplayName(Colors.colorMessageNormal("&fThunder&7: &9" + !apple.getThunder()));
                        e.getClickedInventory().getItem(e.getRawSlot()).setItemMeta(meta2);
                        apple.setThunder(!apple.getThunder());
                        apples.configClass.saveConfigChanges("apples." + apple.getId() + ".thunder", apple.getThunder());
                        break;
                    case ENCHANTED_BOOK:
                        ItemMeta meta3 = e.getClickedInventory().getItem(e.getRawSlot()).getItemMeta();
                        meta3.setDisplayName(Colors.colorMessageNormal("&fDefault Effects&7: &d" + !apple.getDefaultAppleEffects()));
                        e.getClickedInventory().getItem(e.getRawSlot()).setItemMeta(meta3);
                        apple.setDefaultAppleEffects(!apple.getDefaultAppleEffects());
                        apples.configClass.saveConfigChanges("apples." + apple.getId() + ".default_apple_effects", apple.getDefaultAppleEffects());
                        break;
                    case PAPER:
                        ItemMeta meta4 = e.getClickedInventory().getItem(e.getRawSlot()).getItemMeta();
                        meta4.setDisplayName(Colors.colorMessageNormal("&fBroadcast World Origin&7: &e" + !apple.getIsBroadcastSameWorld()));
                        e.getClickedInventory().getItem(e.getRawSlot()).setItemMeta(meta4);
                        apple.setIsBroadcastSameWorld(!apple.getIsBroadcastSameWorld());
                        apples.configClass.saveConfigChanges("apples." + apple.getId() + ".only_origin_world_broadcast", apple.getIsBroadcastSameWorld());
                        break;
                    case IRON_DOOR:
                        e.getWhoClicked().getOpenInventory().close();
                        apples.gui.showApplesGUI(1, ((Player) e.getWhoClicked()).getPlayer());
                }
            }
        }
    }
}
