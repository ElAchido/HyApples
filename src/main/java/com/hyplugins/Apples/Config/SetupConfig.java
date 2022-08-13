package com.hyplugins.Apples.Config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SetupConfig {

    public SetupConfig(File file) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.options().header(
                        "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n" +
                        "-                                                            -\n" +
                        "-      AA      PPPPPP   PPPPPP   L        EEEEEE    SSSSSS   -\n" +
                        "-     A  A     P     P  P     P  L        E        SS        -\n" +
                        "-    AAAAAA    PPPPPP   PPPPPP   L        EEEEEE    SSSSSS   -\n" +
                        "-   A      A   P        P        L        E              SS  -\n" +
                        "-  A        A  P        P        LLLLLLL  EEEEEE    SSSSSS   -\n" +
                        "-                                                            -\n" +
                        "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n\n" +
                        "Welcome to the apple's plugin config. Here you can customize all\n" +
                        "the settings for those, you can add the amount of apples you want,\n" +
                        "you just need to copy the format of an apple and paste it bellow.\n\n" +
                        "APPLE's CONFIG\n\n" +
                        "protection-anvils: true // If players modify apple's aspects (For example, the name), plugin will not recognize them,\n" +
                        "making that those apples will not give the mechanics. To avoid this, enable this option.\n\n" +
                        "damage-explosion-fireworks // This will disable apple's fireworks damage if this value is set to true.\n\n" +
                        "You can view our documentation in the spigot page plugin.\n" +
                        "If you need support, join our discord server: https://discord.gg/e55JEUkjUq\n"

        );

        config.set("config.protection-anvils", true);
        config.set("config.damage-explosion-fireworks", true);

        config.set("messages.prefix", "&6&lAPPLES &8»");
        config.set("messages.reload-config", "%prefix% &aConfig reloaded.");
        config.set("messages.invalid-command", "%prefix% &cInvalid command, use /apples give (AppleID) (Amount) (Player), /apples gui or /apples reload");
        config.set("messages.apple-not-found", "%prefix% &cApple wasn't found, check config apple's ID.");
        config.set("messages.no-perms", "%prefix% &cYou don't have permission to use this apple.");
        config.set("messages.no-command-perms", "%prefix% &fYou don't have permission to execute this command.");
        config.set("messages.anvil-block", "%prefix% &cYou cannot use apples on anvils.");
        config.set("messages.player-not-found", "%prefix% &cPlayer %player_apples% wasn't found.");
        config.set("messages.player-not-online", "%prefix% &cPlayer %player_apples% isn't online.");
        config.set("messages.give-apple", "%prefix% &aApple was given to player %player_apples%.");
        config.set("messages.receive-apple", "%prefix% &aYou've received x%amount% %apple%.");
        config.set("messages.invalid-number", "%prefix% &cThe amount isn't valid.");

        config.set("apples.AppleNumber1.name", "&fApple &e#1");
        config.set("apples.AppleNumber1.material", "GOLDEN_APPLE");
        config.set("apples.AppleNumber1.data", 1);
        config.set("apples.AppleNumber1.thunder", true);
        config.set("apples.AppleNumber1.use_perm", false);
        config.set("apples.AppleNumber1.lore", new ArrayList<>(Arrays.asList("&fThis is a &cpowerfull", "&6apple&f, be careful!")));
        config.set("apples.AppleNumber1.default_apple_effects", false);
        config.set("apples.AppleNumber1.broadcast", new ArrayList<>(Arrays.asList("", "&fPlayer &6%player_apples% &fhas &aused &fan apple!", "&4¡He is in this world!", "")));
        config.set("apples.AppleNumber1.only_origin_world_broadcast", true);
        config.set("apples.AppleNumber1.effects", new ArrayList<>(Arrays.asList("INCREASE_DAMAGE:1:15", "FIRE_RESISTANCE:1:15")));
        config.set("apples.AppleNumber1.fireworks", new ArrayList<>(Arrays.asList("CREEPER:WHITE", "BALL_LARGE:NAVY")));
        config.set("apples.AppleNumber1.commands", new ArrayList<>(Arrays.asList("eco give %player_apples% 1000", "say hello %player_apples%!")));

        config.set("apples.AppleNumber2.name", "&fApple &e#2");
        config.set("apples.AppleNumber2.material", "GOLDEN_APPLE");
        config.set("apples.AppleNumber2.data", 1);
        config.set("apples.AppleNumber2.thunder", true);
        config.set("apples.AppleNumber2.use_perm", false);
        config.set("apples.AppleNumber2.lore", new ArrayList<>(Arrays.asList("&fThis is a &cpowerfull", "&6apple&f, be careful!")));
        config.set("apples.AppleNumber2.default_apple_effects", false);
        config.set("apples.AppleNumber2.broadcast", new ArrayList<>(Arrays.asList("", "&fPlayer &6%luckperms_prefix% %player_apples% &fhas &aused &fan apple!", "")));
        config.set("apples.AppleNumber2.only_origin_world_broadcast", true);
        config.set("apples.AppleNumber2.effects", new ArrayList<>(Arrays.asList("INCREASE_DAMAGE:1:30", "FIRE_RESISTANCE:1:30")));
        config.set("apples.AppleNumber2.fireworks", new ArrayList<>(Arrays.asList("CREEPER:RED", "BALL_LARGE:GREEN")));
        config.set("apples.AppleNumber2.commands", new ArrayList<>(Arrays.asList("give %player_apples% diamond 1", "say hello %player_apples%!")));

        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
