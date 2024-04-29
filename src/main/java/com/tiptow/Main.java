package com.tiptow;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("DeathBan has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("DeathBan has been disabled!");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        String playerName = event.getEntity().getName();
        // Ban the player with a custom message
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "ban " + playerName + " You have died!");
    }

    // Override default death message
    @EventHandler
    public void onDeathMessage(PlayerDeathEvent event) {
        event.setDeathMessage(ChatColor.DARK_RED + "☠ " + event.getEntity().getName() + " has died!");
    }
}

