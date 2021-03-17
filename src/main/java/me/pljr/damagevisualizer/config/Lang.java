package me.pljr.damagevisualizer.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.utils.FormatUtil;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public enum Lang {
    HOLOGRAM_TEXT("&c-{damage}"),
    ACTIONBAR_TEXT("&8>> &a{currentHealth}&7/&a{maxHealth} &8<<"),
    PROJECTILE_TEXT("&8>> &eYou hit &6{target} &efrom &6{distance} blocks &efor &6{damage} damage&e." +
            "\n&eCurrent health is &c{currentHealth}❤"),
    FORMAT_DAMAGE("####.#"),
    FORMAT_HEALTH("####.#"),
    FORMAT_MAX("####.#"),
    FORMAT_DISTANCE("####.#");

    private static HashMap<Lang, String> lang;
    private final String defaultValue;

    Lang(String defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        lang = new HashMap<>();
        FileConfiguration fileConfig = config.getConfig();
        for (Lang lang : Lang.values()){
            if (!fileConfig.isSet(lang.toString())){
                fileConfig.set(lang.toString(), lang.defaultValue);
            }else{
                Lang.lang.put(lang, config.getString(lang.toString()));
            }
        }
        config.save();
    }

    public String get(){
        return lang.getOrDefault(this, FormatUtil.colorString(defaultValue));
    }
}
