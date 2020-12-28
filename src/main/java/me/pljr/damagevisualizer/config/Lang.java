package me.pljr.damagevisualizer.config;

import me.pljr.pljrapispigot.managers.ConfigManager;

import java.util.HashMap;
import java.util.List;

public enum Lang {
    ACTIONBAR_TEXT,
    FORMAT_DAMAGE,
    FORMAT_HEALTH,
    FORMAT_MAX;
    public static List<String> HOLOGRAM_TEXT;

    private static HashMap<Lang, String> lang;

    public static void load(ConfigManager config){
        HOLOGRAM_TEXT = config.getStringList("damage-hologram");
        lang = new HashMap<>();
        for (Lang lang : Lang.values()){
            Lang.lang.put(lang, config.getString("lang."+lang.toString()));
        }
    }

    public String get(){
        return lang.get(this);
    }
}
