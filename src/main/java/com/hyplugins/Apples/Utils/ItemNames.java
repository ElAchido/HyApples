package com.hyplugins.Apples.Utils;

import com.hyplugins.Apples.Config.AppleConstructor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemNames {

    public static ItemStack setItemNames(AppleConstructor apple) {
        ItemStack item = apple.getMadeApple().clone();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(apple.getId());
        meta.setLore(new ArrayList<>(Arrays.asList(
                "",
                Colors.colorMessageNormal("&8✶ &fUse &c&lPERMISSION&7: &f" + apple.getUsePerm()),
                Colors.colorMessageNormal("&8✶ &fSpawn &9&lTHUNDER&7: &f" + apple.getThunder()),
                Colors.colorMessageNormal("&8✶ &fDefault &6Apple &d&lEFFECTS&7: &f" + apple.getDefaultAppleEffects()),
                Colors.colorMessageNormal("&8✶ &fOrigin world &e&lBROADCAST&7: &f" + apple.getIsBroadcastSameWorld()),
                ""
        )));
        item.setItemMeta(meta);
        return item;
    }
}
