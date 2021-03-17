package me.pljr.damagevisualizer.listeners;

import lombok.AllArgsConstructor;
import me.pljr.damagevisualizer.DamageVisualizer;
import me.pljr.damagevisualizer.config.Lang;
import me.pljr.damagevisualizer.config.Settings;
import me.pljr.pljrapispigot.objects.PLJRActionBar;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.pljrapispigot.utils.HologramsUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class EntityDamageByEntityListener implements Listener {

    private final JavaPlugin plugin;
    private final Settings settings;

    @EventHandler
    public void onProjectile(EntityDamageByEntityEvent event){
        if (event.isCancelled() || !(event.getDamager() instanceof Projectile) || !(event.getEntity() instanceof LivingEntity)) return;
        LivingEntity victim = (LivingEntity) event.getEntity();
        Player attacker = (Player) ((Projectile) event.getDamager()).getShooter();
        String damage = new DecimalFormat(Lang.FORMAT_DAMAGE.get()).format(event.getDamage());
        String health = new DecimalFormat(Lang.FORMAT_HEALTH.get()).format(victim.getHealth()-event.getDamage());
        String maxHealth = new DecimalFormat(Lang.FORMAT_MAX.get()).format(victim.getMaxHealth());
        String distance = new DecimalFormat(Lang.FORMAT_DISTANCE.get()).format(attacker.getLocation().distance(victim.getLocation()));
        String target = victim.getName();
        ChatUtil.sendMessage(attacker, Lang.PROJECTILE_TEXT.get()
                .replace("{target}", target)
                .replace("{distance}", distance)
                .replace("{maxHealth}", maxHealth)
                .replace("{currentHealth}", health)
                .replace("{damage}", damage));
    }

    @EventHandler
    public void onMelee(EntityDamageByEntityEvent event){
        if (event.isCancelled() || !(event.getDamager() instanceof Player) || !(event.getEntity() instanceof LivingEntity)) return;
        LivingEntity entity = (LivingEntity) event.getEntity();
        Player damager = (Player) event.getDamager();
        double damage = event.getDamage();
        String damageString = new DecimalFormat(Lang.FORMAT_DAMAGE.get()).format(damage);
        Location location = entity.getLocation().add(settings.getHologramXOffset(), settings.getHologramYOffset(), settings.getHologramZOffset());
        List<String> hologramText = Arrays.asList(Lang.HOLOGRAM_TEXT.get().replace("{damage}", damageString).split("\n"));
        HologramsUtil.create(location, hologramText, settings.getHologramTime());
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, ()->{
            double health = entity.getHealth();
            double max = entity.getMaxHealth();
            String healthString = new DecimalFormat(Lang.FORMAT_HEALTH.get()).format(health);
            String maxString = new DecimalFormat(Lang.FORMAT_MAX.get()).format(max);

            new PLJRActionBar(
                    Lang.ACTIONBAR_TEXT.get()
                            .replace("{damage}", damageString)
                            .replace("{currentHealth}", healthString+"")
                            .replace("{maxHealth}", maxString+""),
                    settings.getActionbarTime()).send(damager);
        }, 2);
    }
}
