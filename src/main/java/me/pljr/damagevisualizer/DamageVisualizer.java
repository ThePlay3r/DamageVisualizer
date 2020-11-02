package me.pljr.damagevisualizer;

import me.pljr.damagevisualizer.config.CfgLang;
import me.pljr.damagevisualizer.config.CfgSettings;
import me.pljr.damagevisualizer.listeners.EntityDamageByEntityListener;
import me.pljr.pljrapi.PLJRApi;
import me.pljr.pljrapi.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DamageVisualizer extends JavaPlugin {
    private static DamageVisualizer instance;
    private static ConfigManager configManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!setupPLJRApi()) return;
        instance = this;
        setupConfig();
        setupListeners();
    }

    private boolean setupPLJRApi(){
        PLJRApi api = (PLJRApi) Bukkit.getServer().getPluginManager().getPlugin("PLJRApi");
        if (api == null){
            Bukkit.getConsoleSender().sendMessage("§cDamageVisualizer: PLJRApi not found, disabling plugin!");
            getServer().getPluginManager().disablePlugin(this);
            return false;
        }else{
            Bukkit.getConsoleSender().sendMessage("§aDamageVisualizer: Hooked into PLJRApi!");
            return true;
        }
    }

    private void setupConfig(){
        saveDefaultConfig();
        configManager = new ConfigManager(getConfig(), "§cDamageVisualizer", "config.yml");
        CfgLang.load(configManager);
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
