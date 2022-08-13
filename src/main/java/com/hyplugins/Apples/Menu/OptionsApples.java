package com.hyplugins.Apples.Menu;

import com.hyplugins.Apples.Utils.XMaterial;
import com.hyplugins.Apples.Config.AppleConstructor;
import com.hyplugins.Apples.Utils.Colors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class OptionsApples {

    public void getAppleSettings(Player player, AppleConstructor apple) {
        Inventory gui = Bukkit.createInventory(player, 45, Colors.colorMessageNormal("&6&lAPPLE &8| Editing..."));

        gui.setItem(4, apple.getMadeApple().clone());

        ItemStack perm = new ItemStack(XMaterial.NAME_TAG.parseMaterial());
        ItemMeta metaPerm = perm.getItemMeta();
        metaPerm.setDisplayName(Colors.colorMessageNormal("&fPermission&7: &c" + apple.getUsePerm()));
        metaPerm.setLore(new ArrayList<>(Arrays.asList("", Colors.colorMessageNormal("&fPermission&7: &4hyplugins.apples." + apple.getId()), "", Colors.colorMessageNormal("&fClick to &btoggle &fthis &evalue&f!"))));
        perm.setItemMeta(metaPerm);
        gui.setItem(19, perm);

        ItemStack thunder = new ItemStack(XMaterial.WATER_BUCKET.parseItem());
        ItemMeta metaThunder = thunder.getItemMeta();
        metaThunder.setDisplayName(Colors.colorMessageNormal("&fThunder&7: &9" + apple.getThunder()));
        metaThunder.setLore(new ArrayList<>(Arrays.asList("", Colors.colorMessageNormal("&fClick to &btoggle &fthis &evalue&f!"))));
        thunder.setItemMeta(metaThunder);
        gui.setItem(21, thunder);

        ItemStack defaultEffects = new ItemStack(XMaterial.ENCHANTED_BOOK.parseItem());
        ItemMeta metaDefaultEffects = defaultEffects.getItemMeta();
        metaDefaultEffects.setDisplayName(Colors.colorMessageNormal("&fDefault Effects&7: &d" + apple.getDefaultAppleEffects()));
        metaDefaultEffects.setLore(new ArrayList<>(Arrays.asList("", Colors.colorMessageNormal("&fClick to &btoggle &fthis &evalue&f!"))));
        defaultEffects.setItemMeta(metaDefaultEffects);
        gui.setItem(23, defaultEffects);

        ItemStack broadcastWorldOrigin = new ItemStack(XMaterial.PAPER.parseItem());
        ItemMeta metaBroadcast = broadcastWorldOrigin.getItemMeta();
        metaBroadcast.setDisplayName(Colors.colorMessageNormal("&fBroadcast World Origin&7: &e" + apple.getIsBroadcastSameWorld()));
        metaBroadcast.setLore(new ArrayList<>(Arrays.asList("", Colors.colorMessageNormal("&fClick to &btoggle &fthis &evalue&f!"))));
        broadcastWorldOrigin.setItemMeta(metaBroadcast);
        gui.setItem(25, broadcastWorldOrigin);

        ItemStack exit = new ItemStack(XMaterial.IRON_DOOR.parseMaterial());
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(Colors.colorMessageNormal("&8» &cApple's Menu &8«"));
        exitMeta.setLore(new ArrayList<>(Arrays.asList("", Colors.colorMessageNormal("&fClick to go &cback &fto the &6Apple's &fMenu&f!"))));
        exit.setItemMeta(exitMeta);
        gui.setItem(40, exit);

        ItemStack frame = new ItemStack(XMaterial.BLACK_STAINED_GLASS_PANE.parseItem());
        for (int slot : new int[]{0, 1, 2, 3, 5, 6, 7, 8, 36 ,37 ,38, 39, 41, 42, 43 ,44}) { gui.setItem(slot, frame); }

        player.openInventory(gui);
    }
}
