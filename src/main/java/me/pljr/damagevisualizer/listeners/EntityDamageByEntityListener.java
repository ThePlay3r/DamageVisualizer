package me.pljr.damagevisualizer.listeners;

import me.pljr.damagevisualizer.DamageVisualizer;
import me.pljr.damagevisualizer.config.CfgLang;
import me.pljr.damagevisualizer.config.CfgSettings;
import me.pljr.damagevisualizer.enums.Lang;
import me.pljr.pljrapi.managers.ActionBarManager;
import me.pljr.pljrapi.objects.PLJRActionBar;
import me.pljr.pljrapi.utils.HologramsUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if (event.isCancelled() || !(event.getDamager() instanceof Player) || !(event.getEntity() instanceof LivingEntity)) return;
        LivingEntity entity = (LivingEntity) event.getEntity();
        Player damager = (Player) event.getDamager();
        double damage = event.getDamage();
        String damageString = new DecimalFormat(CfgLang.lang.get(Lang.FORMAT_DAMAGE)).format(damage);
        Location location = entity.getLocation().add(CfgSettings.hologramXOffset, CfgSettings.hologramYOffset, CfgSettings.hologramZOffset);
        List<String> hologramText = new ArrayList<>();
        for (String line : CfgLang.hologramText){
            hologramText.add(line.replace("%damage", damageString));
        }
        HologramsUtil.create(location, hologramText, CfgSettings.hologramTime);
        Bukkit.getScheduler().runTaskLaterAsynchronously(DamageVisualizer.getInstance(), ()->{
            double health = entity.getHealth();
            double max = entity.getMaxHealth();
            String healthString = new DecimalFormat(CfgLang.lang.get(Lang.FORMAT_HEALTH)).format(health);
            String maxString = new DecimalFormat(CfgLang.lang.get(Lang.FORMAT_MAX)).format(max);

            ActionBarManager.send(damager, new PLJRActionBar(
                    CfgLang.lang.get(Lang.ACTIONBAR_TEXT)
                            .replace("%damage", damageString)
                            .replace("%currentHealth", healthString+"")
                            .replace("%maxHealth", maxString+""),
                    CfgSettings.actionbarTime));
        }, 2);
    }
}
