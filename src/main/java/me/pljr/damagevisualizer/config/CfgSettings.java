package me.pljr.damagevisualizer.config;

import me.pljr.pljrapispigot.managers.ConfigManager;

public class CfgSettings {
    public static int HOLOGRAM_TIME;
    public static double HOLOGRAM_X_OFFSET;
    public static double HOLOGRAM_Y_OFFSET;
    public static double HOLOGRAM_Z_OFFSET;
    public static int ACTIONBAR_TIME;

    public static void load(ConfigManager config){
        HOLOGRAM_TIME = config.getInt("settings.hologram-time");
        HOLOGRAM_X_OFFSET = config.getDouble("settings.hologram-x-offset");
        HOLOGRAM_Y_OFFSET = config.getDouble("settings.hologram-y-offset");
        HOLOGRAM_Z_OFFSET = config.getDouble("settings.hologram-z-offset");
        ACTIONBAR_TIME = config.getInt("settings.actionbar-time");
    }
}
