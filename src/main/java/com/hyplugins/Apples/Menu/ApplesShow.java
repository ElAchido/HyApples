package com.hyplugins.Apples.Menu;

import com.hyplugins.Apples.Utils.Colors;
import com.hyplugins.Apples.Utils.ItemNames;
import com.hyplugins.Apples.Utils.XMaterial;
import com.hyplugins.Apples.Config.ListApples;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ApplesShow {

    public void showApplesGUI(int page, Player player) {
        Inventory gui = Bukkit.createInventory(player, 45, Colors.colorMessageNormal("&6&lAPPLES &8| Settings"));

        int amount = 27 * page;
        int apple = amount - 27;
        for (int slot : new int[]{9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35}) {
            if (ListApples.apples.size() - 1 < apple) break;
            gui.setItem(slot, ItemNames.setItemNames(ListApples.apples.get(apple)));
            apple++;
        }

        ItemStack leaveItem = new ItemStack(XMaterial.IRON_DOOR.parseMaterial());
        ItemMeta metaLeave = leaveItem.getItemMeta();
        metaLeave.setDisplayName(Colors.colorMessageNormal("&cClose Menu"));
        metaLeave.setLore(Arrays.asList("", Colors.colorMessageNormal("&fClick &fto &cclose &fthis menu.")));
        leaveItem.setItemMeta(metaLeave);
        gui.setItem(40, leaveItem);

        ItemStack next = new ItemStack(XMaterial.ARROW.parseMaterial());
        ItemMeta metaNext = next.getItemMeta();
        metaNext.setDisplayName(Colors.colorMessageNormal("&fCurrent Page&7:&a " + page));
        metaNext.setLore(new ArrayList<>(Arrays.asList(
                Colors.colorMessageNormal("&7Right-Click &fto go into the &cnext &fpage."),
                Colors.colorMessageNormal("&7Left-Click &fto go into the &cprevious &fpage.")
        )));
        next.setItemMeta(metaNext);
        gui.setItem(43, next);

        ItemStack frame = new ItemStack(XMaterial.BLACK_STAINED_GLASS_PANE.parseItem());
        for (int slot : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 36 ,37 ,38, 39, 41, 42 ,44}) { gui.setItem(slot, frame); }

        player.openInventory(gui);
    }
}
