package com.d4rkr34lm.wgbuild.plotSystem.commands;

import com.d4rkr34lm.wgbuild.WGBuild;
import com.d4rkr34lm.wgbuild.plotSystem.Plot;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.jetbrains.annotations.NotNull;

public class TntCommand implements CommandExecutor, Listener {

    private WGBuild plugin;

    public TntCommand(WGBuild plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
        this.plugin.getCommand("tnt").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            for(Plot plot : plugin.getPlots()){
                if(plot.isInsideArea(player.getLocation())){
                    plot.setTntEnabled(!plot.isTntEnabled());
                    if(plot.isTntEnabled()){
                        plugin.getServer().broadcastMessage("Tnt has been enabled");
                    }
                    else {
                        plugin.getServer().broadcastMessage("Tnt has been disabled");
                    }
                    plugin.getScoreboardManager().updateScoreboard(player);
                }
            }
        }
        return false;
    }

    @EventHandler
    public void onTntExplosion(EntityExplodeEvent event) {
        if(event.getEntityType() == EntityType.PRIMED_TNT){
            for(Plot plot : plugin.getPlots()){
                if(plot.isInsideArea(event.getLocation())){
                    if(!plot.isTntEnabled()){
                        event.blockList().clear();
                    }
                }
            }
        }
    }
}
