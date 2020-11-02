package me.pljr.damagevisualizer.config;

import me.pljr.pljrapi.managers.ConfigManager;

public class CfgSettings {
    public static int hologramTime;
    public static double hologramXOffset;
    public static double hologramYOffset;
    public static double hologramZOffset;
    public static int actionbarTime;

    public static void load(ConfigManager config){
        hologramTime = config.getInt("settings.hologram-time");
        hologramXOffset = config.getDouble("settings.hologram-x-offset");
        hologramYOffset = config.getDouble("settings.hologram-y-offset");
        hologramZOffset = config.getDouble("settings.hologram-z-offset");
        actionbarTime = config.getInt("settings.actionbar-time");
    }
}
