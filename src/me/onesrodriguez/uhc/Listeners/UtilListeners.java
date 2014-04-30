package me.onesrodriguez.uhc.Listeners;

import me.onesrodriguez.uhc.UHCCore;
import me.onesrodriguez.uhc.Util.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class UtilListeners implements Listener{

    @EventHandler
    public void onPing(ServerListPingEvent event){
        GameState state = UHCCore.getState();
        
        if(state == GameState.LOBBY){
            event.setMotd("§a§lJoinable! §aCome and play!");
        }
        else if(state == GameState.INGAME){
            event.setMotd("§4§lNot Joinable! §cGame in progress..");
        }
        else if(state == GameState.STARTING){
            event.setMotd("§4§lNot Joinable! §cBooting up...");
        }
        else if(state == GameState.CLEANUP){
            event.setMotd("§4§lNot Joinable! §cCleaning up and restarting...");
        }
        else if(state == GameState.WARMUP){
            event.setMotd("§4§lNot Joinable! §cSelecting the teams and beginning!");
        }                        
    }
    
    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(UHCCore.getState()!=GameState.INGAME){
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event){
        if(UHCCore.getState()!=GameState.INGAME){
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event){
        if(UHCCore.getState()!=GameState.INGAME){
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event){
        if(UHCCore.getState()!=GameState.INGAME){
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(UHCCore.getState()!=GameState.INGAME){
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if(UHCCore.getState()!=GameState.INGAME){
            event.setCancelled(true);
        }
    }
}
