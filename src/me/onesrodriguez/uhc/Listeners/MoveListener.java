package me.onesrodriguez.uhc.Listeners;

import java.util.List;
import me.onesrodriguez.uhc.UHCCore;
import me.onesrodriguez.uhc.Util.GameState;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener{
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
     Player player = event.getPlayer();
     Location loc = event.getTo();
     
     if(UHCCore.getState() != GameState.INGAME){
         Location spawn = player.getWorld().getSpawnLocation();
         
         if(spawn.distance(loc) > 20){
             player.teleport(spawn);
             player.sendMessage(UHCCore.TAG+"§4Don't wander too far from spawn! Have a good look round at your surroundings..");
         }
         else if(spawn.distance(loc) > 15){
             List<Block> blocks = UHCCore.blocksBetweenPoints(player.getLocation().add(2,2,2), player.getLocation().subtract(2,2,2));
             for(Block block : blocks){
                 if(block.getType() != Material.AIR) {
                 }
                 if(block.getLocation().distance(spawn) > 15){
                     block.getWorld().strikeLightningEffect(block.getLocation());
                     block.getWorld().playEffect(block.getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
                     block.getWorld().playEffect(block.getLocation(), Effect.SMOKE, 10);
                     block.getWorld().playSound(block.getLocation(), Sound.HORSE_DEATH, 10, 1);
                     block.getWorld().playSound(block.getLocation(), Sound.GHAST_SCREAM, 10, 5); 
                 }
              }
         }
     }
    }

}
