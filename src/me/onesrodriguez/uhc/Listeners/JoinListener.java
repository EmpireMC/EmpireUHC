package me.onesrodriguez.uhc.Listeners;

import me.onesrodriguez.uhc.Exceptions.TeamException;
import me.onesrodriguez.uhc.Teams.TeamManager;
import me.onesrodriguez.uhc.UHCCore;
import me.onesrodriguez.uhc.Util.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinListener implements Listener{

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        
        player.sendMessage(UHCCore.TAG + "Welcome to UHC!");
        int players = Bukkit.getOnlinePlayers().length;
        if(players < UHCCore.MIN_PLAYERS){
            player.sendMessage(UHCCore.TAG + "§cThe game will begin when we get §e§l" + UHCCore.MIN_PLAYERS + " §cplayers.");
        }else{
            player.sendMessage(UHCCore.TAG + "The game will begin shortly..");
        }
        
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        player.setSaturation(8f);
        player.setLevel(0);
        player.setExp(0F);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        
        try {
           TeamManager.joinTeam(player, UHCCore.getTeam("Spectators"));
        } catch(TeamException e){
           System.out.println("Somehow, somewhere, something has gone wrong.");
           e.printStackTrace();
        }

    }
    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        
        GameState state = UHCCore.getState();
        if(state != GameState.LOBBY){
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, UHCCore.TAG + "&4This game is in warmup or has already started!");
        }
    }
}
