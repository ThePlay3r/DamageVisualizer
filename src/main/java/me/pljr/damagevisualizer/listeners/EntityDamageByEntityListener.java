package me.pljr.damagevisualizer.listeners;

import me.pljr.damagevisualizer.DamageVisualizer;
import me.pljr.damagevisualizer.config.CfgSettings;
import me.pljr.damagevisualizer.config.Lang;
import me.pljr.pljrapispigot.managers.ActionBarManager;
import me.pljr.pljrapispigot.objects.PLJRActionBar;
import me.pljr.pljrapispigot.utils.HologramsUtil;
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
        String damageString = new DecimalFormat(Lang.FORMAT_DAMAGE.get()).format(damage);
        Location location = entity.getLocation().add(CfgSettings.HOLOGRAM_X_OFFSET, CfgSettings.HOLOGRAM_Y_OFFSET, CfgSettings.HOLOGRAM_Z_OFFSET);
        List<String> hologramText = new ArrayList<>();
        for (String line : Lang.HOLOGRAM_TEXT){
            hologramText.add(line.replace("{damage}", damageString));
        }
        HologramsUtil.create(location, hologramText, CfgSettings.HOLOGRAM_TIME);
        Bukkit.getScheduler().runTaskLaterAsynchronously(DamageVisualizer.getInstance(), ()->{
            double health = entity.getHealth();
            double max = entity.getMaxHealth();
            String healthString = new DecimalFormat(Lang.FORMAT_HEALTH.get()).format(health);
            String maxString = new DecimalFormat(Lang.FORMAT_MAX.get()).format(max);

            ActionBarManager.send(damager, new PLJRActionBar(
                    Lang.ACTIONBAR_TEXT.get()
                            .replace("{damage}", damageString)
                            .replace("{currentHealth}", healthString+"")
                            .replace("{maxHealth}", maxString+""),
                    CfgSettings.ACTIONBAR_TIME));
        }, 2);
    }
}
