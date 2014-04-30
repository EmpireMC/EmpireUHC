package me.onesrodriguez.uhc.Teams;

import me.onesrodriguez.uhc.Exceptions.TeamException;
import me.onesrodriguez.uhc.UHCCore;
import me.onesrodriguez.uhc.Util.GameState;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TeamManager {

    public static void joinTeam(Player player, UHCTeam team) throws TeamException{
        if(UHCCore.getState() == GameState.LOBBY || UHCCore.getState() == GameState.WARMUP){
            for(UHCTeam t : UHCCore.getTeams()){
                if(t.getMembers().contains(player.getName())){
                    t.getMembers().remove(player.getName());
                }
            }
            
            team.getMembers().add(player.getName());
            player.sendMessage(UHCCore.TAG + "You have successfully joined: " + team.getColor() + "Team " + team.getName() + "§e!");
            
        }else{
            throw new TeamException(ChatColor.DARK_RED + "Team can't be changed unless the game is in lobby!");
        }
    }
}
