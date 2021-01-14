package me.pljr.damagevisualizer;

import me.pljr.damagevisualizer.config.CfgSettings;
import me.pljr.damagevisualizer.config.Lang;
import me.pljr.damagevisualizer.listeners.EntityDamageByEntityListener;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DamageVisualizer extends JavaPlugin {
    private static DamageVisualizer instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        setupConfig();
        setupListeners();
    }

    private void setupConfig(){
        saveDefaultConfig();
        Lang.load(new ConfigManager(this, "lang.yml"));
        CfgSettings.load(new ConfigManager(this, "config.yml"));
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
