package ru.baronessdev.personal.rightspvp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class RightsPvP extends JavaPlugin {
    public static RightsPvP inst;

    public RightsPvP() {
        inst = this;
    }

    @Override
    public void onEnable() {
        Config.init();
        Bukkit.getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onPvP(EntityDamageByEntityEvent e) {
                Entity damagerEnt = e.getDamager();

                if (damagerEnt instanceof Player && Config.getBoolean("enabled")) {
                    Player damager = (Player) damagerEnt;
                    List<String> rights = Config.getStringList("rights");

                    if (rights.stream().noneMatch(damager::hasPermission)) {
                        e.setCancelled(true);

                        if (Config.getBoolean("messages.enabled")) {
                            damager.sendMessage(Config.getMessage("messages.no-rights"));
                        }
                    }
                }
            }

        }, this);
    }

    @Override
    public void onDisable() {
    }
}