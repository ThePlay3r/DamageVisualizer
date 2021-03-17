package me.pljr.damagevisualizer;

import me.pljr.damagevisualizer.config.Lang;
import me.pljr.damagevisualizer.config.Settings;
import me.pljr.damagevisualizer.listeners.EntityDamageByEntityListener;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DamageVisualizer extends JavaPlugin {

    private Settings settings;

    @Override
    public void onEnable() {
        // Plugin startup logic
        setupConfig();
        setupListeners();
    }

    private void setupConfig(){
        saveDefaultConfig();
        Lang.load(new ConfigManager(this, "lang.yml"));
        settings = new Settings(new ConfigManager(this, "config.yml"));
    }

    private void setupListeners(){
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(this, settings), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
