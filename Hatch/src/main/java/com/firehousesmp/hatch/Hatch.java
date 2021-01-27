package com.firehousesmp.hatch;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public final class Hatch extends JavaPlugin implements Listener {

    //Plugin Startup Logic
    private final Set<String> inWorld = new HashSet<>();
    private double xCoord;
    private double yCoord;
    private double zCoord;
    private float yaw;
    private float pitch;


    @Override
    public void onEnable() {
        //Load the configs
        inWorld.addAll(getConfig().getStringList("inWorld"));
        reloadConfig();
        saveDefaultConfig();
        xCoord = getConfig().getDouble("xCoord");
        yCoord = getConfig().getDouble("yCoord");
        zCoord = getConfig().getDouble("zCoord");
        yaw = getConfig().getInt("yaw");
        pitch = getConfig().getInt("pitch");

        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e){
        Player a = e.getPlayer();

        if(!e.getPlayer().hasPlayedBefore()){
        Location loc = new Location(Bukkit.getWorld("world"), xCoord, yCoord, zCoord, yaw, pitch);
        a.sendMessage(loc.toString());
        a.teleport(loc);


        }else{
            a.sendMessage("You have already been hatched");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
