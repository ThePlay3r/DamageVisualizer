package me.pljr.damagevisualizer.config;

import lombok.Getter;
import me.pljr.pljrapispigot.managers.ConfigManager;

@Getter
public class Settings {
    private final int hologramTime;
    private final double hologramXOffset;
    private final double hologramYOffset;
    private final double hologramZOffset;
    private final int actionbarTime;

    public Settings(ConfigManager config){
        hologramTime = config.getInt("settings.hologram-time");
        hologramXOffset = config.getDouble("settings.hologram-x-offset");
        hologramYOffset = config.getDouble("settings.hologram-y-offset");
        hologramZOffset = config.getDouble("settings.hologram-z-offset");
        actionbarTime = config.getInt("settings.actionbar-time");
    }
}
