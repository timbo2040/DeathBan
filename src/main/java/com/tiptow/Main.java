package com.tiptow;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
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
        Player player = event.getEntity();
        String playerName = player.getName();

        // Drop the player's items
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null) {
                player.getWorld().dropItemNaturally(player.getLocation(), item);
            }
        }
        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (item != null) {
                player.getWorld().dropItemNaturally(player.getLocation(), item);
            }
        }

        // Clear the player's inventory
        player.getInventory().clear();

        // Ban the player with a custom message
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "ban " + playerName + " You have died!");
    }


    // Override default death message
    @EventHandler
    public void onDeathMessage(PlayerDeathEvent event) {
        event.setDeathMessage(ChatColor.DARK_RED + "☠ " + event.getEntity().getName() + " has died!");
    }
}
