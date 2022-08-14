package com.hyplugins.Apples.Config;

import com.hyplugins.Apples.Apples;
import com.hyplugins.Apples.Utils.CheckColor;
import com.hyplugins.Apples.Utils.Colors;
import com.hyplugins.Apples.Utils.XMaterial;
import org.bukkit.FireworkEffect;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class Configuration {

    private final Apples apples;
    public Boolean isProtectionAnvilEnabled;
    public Boolean isFireworkDamageAllowed;

    public Configuration(Apples plugin) {
        this.apples = plugin;
        isProtectionAnvilEnabled = apples.getConfig().getBoolean("config.protection-anvils");
        isFireworkDamageAllowed = apples.getConfig().getBoolean("config.damage-explosion-fireworks");
        setUpConfig();
    }

    public void setUpConfig() {
        FileConfiguration config = apples.getConfig();
        Set<String> apples = config.getConfigurationSection("apples").getKeys(false);
        for (String apple : apples) {
            String name = Colors.colorMessageNormal(config.getString("apples." + apple + ".name"));
            Boolean usePerm = config.getBoolean("apples." + apple + ".use_perm");
            List<String> lore = config.getStringList("apples." + apple + ".lore");
            List<String> colorLore = new ArrayList<>();
            for (String line : lore) {
                colorLore.add(Colors.colorMessageNormal(line));
            }
            lore.clear();
            Boolean thunder = config.getBoolean("apples." + apple + ".thunder");
            Boolean defaultEffects = config.getBoolean("apples." + apple + ".default_apple_effects");
            List<String> broadcast = config.getStringList("apples." + apple + ".broadcast");
            List<String> broadcastColor = new ArrayList<>();
            for (String line : broadcast) {
                broadcastColor.add(Colors.colorMessageNormal(line));
            }
            broadcast.clear();
            Boolean originWorldBroadcast = config.getBoolean("apples." + apple + ".only_origin_world_broadcast");
            List<PotionEffect> effects = new ArrayList<>();
            List<String> effectsIds = config.getStringList("apples." + apple + ".effects");
            for (String effectId : effectsIds) {
                String[] args = effectId.split(":");
                PotionEffect effect = new PotionEffect(PotionEffectType.getByName(args[0]), Integer.parseInt(args[2]) * 20, Integer.parseInt(args[1]));
                effects.add(effect);
            }
            List<FireworkEffect> fireworks = new ArrayList<>();
            List<String> fireworksData = config.getStringList("apples." + apple + ".fireworks");
            for (String fireworkData : fireworksData) {
                String[] args = fireworkData.split(":");
                FireworkEffect effect = FireworkEffect.builder().trail(true).withFlicker().with(FireworkEffect.Type.valueOf(args[0])).withColor(CheckColor.getColorString(args[1])).build();
                fireworks.add(effect);
          }
            fireworksData.clear();
            List<String> commands = config.getStringList("apples." + apple + ".commands");
            ItemStack appleItem;
            Optional<XMaterial> material = XMaterial.matchXMaterial(Objects.requireNonNull(config.getString("apples." + apple + ".material")));
            if (XMaterial.isNewVersion()) {
                appleItem = new ItemStack(material.get().parseItem().getType());
            } else {
                appleItem = new ItemStack(material.get().parseItem().getType(), 1, (short) config.getInt("apples." + apple + ".data"));
            }
            ItemMeta appleMeta = appleItem.getItemMeta();
            appleMeta.setDisplayName(name);
            appleMeta.setLore(colorLore);
            appleItem.setItemMeta(appleMeta);
            AppleConstructor appleConstructor = new AppleConstructor(apple, name, usePerm, colorLore, thunder, defaultEffects, broadcastColor, originWorldBroadcast, effects, fireworks, commands, appleItem);
            ListApples.apples.add(appleConstructor);
        }
    }

    public void saveConfigChanges(String path, Object value) {
        apples.getConfig().set(path, value);
        apples.saveConfig();
    }

    public void reloadConfig() {
        apples.reloadConfig();
        ListApples.apples.clear();
        setUpConfig();
    }
}
