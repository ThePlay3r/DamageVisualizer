package me.pljr.damagevisualizer;

import me.pljr.damagevisualizer.config.CfgSettings;
import me.pljr.damagevisualizer.config.Lang;
import me.pljr.damagevisualizer.listeners.EntityDamageByEntityListener;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DamageVisualizer extends JavaPlugin {
    private static DamageVisualizer instance;
    private static ConfigManager configManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        setupConfig();
        setupListeners();
    }

    private void setupConfig(){
        saveDefaultConfig();
        configManager = new ConfigManager(this, "config.yml");
        Lang.load(configManager);
        CfgSettings.load(configManager);
    }

    private void setupListeners(){
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
    }

    public static DamageVisualizer getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
