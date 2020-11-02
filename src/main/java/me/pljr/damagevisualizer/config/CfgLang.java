package me.pljr.damagevisualizer.config;

import me.pljr.damagevisualizer.enums.Lang;
import me.pljr.pljrapi.managers.ConfigManager;

import java.util.HashMap;
import java.util.List;

public class CfgLang {
    public static List<String> hologramText;
    public static HashMap<Lang, String> lang;

    public static void load(ConfigManager config){
        CfgLang.hologramText = config.getStringList("damage-hologram");
        CfgLang.lang = new HashMap<>();
        for (Lang lang : Lang.values()){
            CfgLang.lang.put(lang, config.getString("lang."+lang.toString()));
        }
    }
}
