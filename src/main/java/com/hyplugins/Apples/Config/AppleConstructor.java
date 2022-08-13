package com.hyplugins.Apples.Config;

import org.bukkit.FireworkEffect;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class AppleConstructor {

    private String id;
    private String name;
    private Boolean usePerm;
    private List<String> lore;
    private Boolean thunder;
    private Boolean defaultAppleEffects;
    private List<String> broadcast;
    private Boolean isBroadcastSameWorld;
    private List<PotionEffect> effects;
    private List<FireworkEffect> fireworks;
    private List<String> commands;
    private ItemStack madeApple;

    public AppleConstructor
            (String id,
             String name,
             Boolean usePerm,
             List<String> lore,
             Boolean thunder,
             Boolean defaultAppleEffects,
             List<String> broadcast,
             Boolean isBroadcastSameWorld,
             List<PotionEffect> effects,
             List<FireworkEffect> fireworks,
             List<String> commands,
             ItemStack madeApple)
    {
        this.id = id;
        this.name = name;
        this.usePerm = usePerm;
        this.lore = lore;
        this.thunder = thunder;
        this.defaultAppleEffects = defaultAppleEffects;
        this.broadcast = broadcast;
        this.isBroadcastSameWorld = isBroadcastSameWorld;
        this.effects = effects;
        this.fireworks = fireworks;
        this.commands = commands;
        this.madeApple = madeApple;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Boolean getUsePerm() { return usePerm; }
    public void setUsePerm(Boolean value) { usePerm = value; }
    public List<String> getLore() { return lore; }
    public Boolean getThunder() { return thunder; }
    public void setThunder(Boolean value) { thunder = value; }
    public Boolean getDefaultAppleEffects() { return defaultAppleEffects; }
    public void setDefaultAppleEffects(Boolean value) { defaultAppleEffects = value; }
    public List<String> getBroadcast() { return broadcast; }
    public Boolean getIsBroadcastSameWorld() { return isBroadcastSameWorld; }
    public void setIsBroadcastSameWorld(Boolean value) { isBroadcastSameWorld = value; }
    public List<PotionEffect> getEffects() { return effects; }
    public List<FireworkEffect> getFireworks() { return fireworks; }
    public List<String> getCommands() { return commands; }
    public ItemStack getMadeApple() { return madeApple; }
}
