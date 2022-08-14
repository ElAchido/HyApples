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
                            float value = (float) ListApples.apples.size() / 27;
                            if (e.getClick().equals(ClickType.LEFT) && page - 1 > 0) {
                                e.getWhoClicked().getOpenInventory().close();
                                applesShow.showApplesGUI(page - 1, (Player) e.getWhoClicked());
                            } else if (e.getClick().equals(ClickType.RIGHT) && value > page) {
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
                List<AppleConstructor> applesList = ListApples.apples.stream().filter(a -> a.getId().equals(e.getClickedInventory().getItem(4).getItemMeta().getDisplayName())).collect(Collectors.toList());
                AppleConstructor apple = applesList.get(0);
                switch (e.getCurrentItem().getType()) {
                    case NAME_TAG:
                        apple.setUsePerm(!apple.getUsePerm());
                        apples.configClass.saveConfigChanges("apples." + apple.getId() + ".use_perm", apple.getUsePerm());
                        ItemMeta meta1 = e.getClickedInventory().getItem(19).getItemMeta();
                        meta1.setDisplayName(Colors.colorMessageNormal("&fPermission&7: &c" + apple.getUsePerm()));
                        e.getClickedInventory().getItem(19).setItemMeta(meta1);
                        break;
                    case WATER_BUCKET:
                        apple.setThunder(!apple.getThunder());
                        apples.configClass.saveConfigChanges("apples." + apple.getId() + ".thunder", apple.getThunder());
                        ItemMeta meta2 = e.getClickedInventory().getItem(21).getItemMeta();
                        meta2.setDisplayName(Colors.colorMessageNormal("&fThunder&7: &9" + apple.getThunder()));
                        e.getClickedInventory().getItem(21).setItemMeta(meta2);
                        break;
                    case ENCHANTED_BOOK:
                        apple.setDefaultAppleEffects(!apple.getDefaultAppleEffects());
                        apples.configClass.saveConfigChanges("apples." + apple.getId() + ".default_apple_effects", apple.getDefaultAppleEffects());
                        ItemMeta meta3 = e.getClickedInventory().getItem(23).getItemMeta();
                        meta3.setDisplayName(Colors.colorMessageNormal("&fDefault Effects&7: &d" + apple.getDefaultAppleEffects()));
                        e.getClickedInventory().getItem(23).setItemMeta(meta3);
                        break;
                    case PAPER:
                        apple.setIsBroadcastSameWorld(!apple.getIsBroadcastSameWorld());
                        apples.configClass.saveConfigChanges("apples." + apple.getId() + ".only_origin_world_broadcast", apple.getIsBroadcastSameWorld());
                        ItemMeta meta4 = e.getClickedInventory().getItem(25).getItemMeta();
                        meta4.setDisplayName(Colors.colorMessageNormal("&fBroadcast World Origin&7: &e" + apple.getIsBroadcastSameWorld()));
                        e.getClickedInventory().getItem(25).setItemMeta(meta4);
                        break;
                    case IRON_DOOR:
                        e.getWhoClicked().getOpenInventory().close();
                        apples.gui.showApplesGUI(1, ((Player) e.getWhoClicked()).getPlayer());
                }
            }
        }
    }
}
