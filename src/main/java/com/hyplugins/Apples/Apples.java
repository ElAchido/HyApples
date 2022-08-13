package com.hyplugins.Apples;

import com.hyplugins.Apples.AnvilProtection.AnvilEvent;
import com.hyplugins.Apples.Commands.MainCommand;
import com.hyplugins.Apples.Config.Configuration;
import com.hyplugins.Apples.Config.ListApples;
import com.hyplugins.Apples.Config.SetupConfig;
import com.hyplugins.Apples.EventsApples.Events;
import com.hyplugins.Apples.FireworkDamage.FireworkEvent;
import com.hyplugins.Apples.Menu.ApplesShow;
import com.hyplugins.Apples.Menu.EventsEditor;
import com.hyplugins.Apples.Utils.Colors;
import com.hyplugins.Apples.Utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Apples extends JavaPlugin {

    public Boolean isPlaceholderAPI;
    public Configuration configClass;
    public YamlConfiguration config;
    public File configFile;
    public ApplesShow gui;

    @Override
    public void onEnable() {
        createFolder();
        configFile = createFile();
        config = YamlConfiguration.loadConfiguration(configFile);
        new ListApples();
        configClass = new Configuration(this);
        Bukkit.getPluginManager().registerEvents(new Events(this), this);
        gui = new ApplesShow();
        Bukkit.getPluginManager().registerEvents(new EventsEditor(this), this);
        if (configClass.isProtectionAnvilEnabled) Bukkit.getPluginManager().registerEvents(new AnvilEvent(this), this);
        if (configClass.isFireworkDamageAllowed)  Bukkit.getPluginManager().registerEvents(new FireworkEvent(), this);
        getCommand("apples").setExecutor(new MainCommand(this));
        getCommand("apples").setTabCompleter(new MainCommand(this));
        isPlaceholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
        logOnStart();
    }

    @Override
    public void onDisable() {}

    public void logOnStart() {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(Colors.colorMessageNormal("&8[ &fHy&6&lAPPLES &8] &fPlugin loaded successfully."));
        if (isPlaceholderAPI) sender.sendMessage(Colors.colorMessageNormal("&8[ &fHy&6&lAPPLES &8] &fPlaceHolderAPI detected, their plugin placeholders will be available in broadcast messages..."));
        if (configClass.isProtectionAnvilEnabled) sender.sendMessage(Colors.colorMessageNormal("&8[ &fHy&6&lAPPLES &8] &fAnvil Protection &aenabled&f, players cannot modify apples on anvils."));
        if (configClass.isFireworkDamageAllowed) sender.sendMessage(Colors.colorMessageNormal("&8[ &fHy&6&lAPPLES &8] &fFirework Protection &aenabled&f, players won't receive damage on apple's fireworks."));
        if (XMaterial.isNewVersion()) {
            sender.sendMessage(Colors.colorMessageNormal("&8[ &fHy&6&lAPPLES &8] &fServer version is above 1.13, ignoring Data value on apple's config."));
        } else {
            sender.sendMessage(Colors.colorMessageNormal("&8[ &fHy&6&lAPPLES &8] &fServer version is below 1.13, Data value on apple's config will be used."));
        }
    }

    private File createFile() {
        try {
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                file.createNewFile();
                new SetupConfig(file);
            }
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
        }
        return null;
    }

    private void createFolder() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
    }
}
