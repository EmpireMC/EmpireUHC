package me.onesrodriguez.uhc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import me.onesrodriguez.uhc.Teams.Blue;
import me.onesrodriguez.uhc.Teams.Green;
import me.onesrodriguez.uhc.Teams.Red;
import me.onesrodriguez.uhc.Teams.Spectators;
import me.onesrodriguez.uhc.Teams.UHCTeam;
import me.onesrodriguez.uhc.Teams.Yellow;
import me.onesrodriguez.uhc.Util.GameState;
import me.onesrodriguez.uhc.Util.TimeManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class UHCCore extends JavaPlugin{
    
    private static final List<UHCTeam> teams = new ArrayList<UHCTeam>();
    private static GameState state;
    private static int ticks = 0;
    public static int MIN_PLAYERS = 12;
    
    public static String TAG = "§8[§5Empire§cUHC§8] §e";
    public static String WORLD_NAME = "world";
    
    public static GameState getState(){
        return state;
    }
    
    public static void setState(GameState state){
        UHCCore.state = state;
    }
    
    public static int getTicks(){
        return ticks;
    }
    
    public static void setTicks(int ticks){
        UHCCore.ticks = ticks;
    }
    
    public static UHCTeam getTeam(Player player){
        for(UHCTeam team : getTeams()){
            if(team.getMembers().contains(player.getName())){
                return team;
            }
        }
        return null;
    }
            
    public static UHCTeam getTeam(String name){
        for(UHCTeam team : getTeams()){
            if(team.getName().equalsIgnoreCase(name)){
                return team;
            }
        }
        return null;
    }
    
    public static List<UHCTeam> getTeams(){
        return teams;
    }
    
    @Override
    public void onEnable(){
        state = GameState.STARTING;
               
        if(!(new File(getDataFolder(), "config.yml")).exists()){
          saveDefaultConfig();
        }
        
        teams.add(new Blue());
        teams.add(new Red());
        teams.add(new Green());
        teams.add(new Yellow());
        teams.add(new Spectators());
        
        Bukkit.getServer().getScheduler().runTaskTimer(this, new TimeManager(), 20L, 20L);
        System.out.println(TAG+"UHC enabled!");
    }
    
    @Override
    public void onLoad(){
        deleteWorld(new File(WORLD_NAME));
    }
    
    @Override
    public void onDisable(){
        
    }
    
    public static void deleteWorld(File dir){
        if(dir.isDirectory()){
            String[] children = dir.list();
            for (String children1 : children) {
                deleteWorld(new File(dir, children1));
            }
        }
        dir.delete();
    }
    
    public static List<Block> blocksBetweenPoints(Location l1, Location l2){
        List<Block> blocks = new ArrayList<Block>();
        
        int topBlockX = (l1.getBlockX() < l2.getBlockX() ? l2.getBlockX() : l1.getBlockX());
        int bottomBlockX = (l1.getBlockX() > l2.getBlockX() ? l2.getBlockX() : l1.getBlockX());
        
        int topBlockY = (l1.getBlockY() < l2.getBlockY() ? l2.getBlockY() : l1.getBlockY());
        int bottomBlockY = (l1.getBlockY() > l2.getBlockY() ? l2.getBlockY() : l1.getBlockY());
        
        int topBlockZ = (l1.getBlockZ() < l2.getBlockZ() ? l2.getBlockZ() : l1.getBlockZ());
        int bottomBlockZ = (l1.getBlockZ() > l2.getBlockZ() ? l2.getBlockZ() : l1.getBlockZ());
        
        for(int x = bottomBlockX; x <= topBlockX; x++){
            for(int y = bottomBlockY; y <= topBlockY; y++){
                for(int z = bottomBlockZ; z <= topBlockZ; z++){
                    Block block = l1.getWorld().getBlockAt(x, y, z);
                    blocks.add(block);
                }
            }
        }
        
        return blocks;
        
    }
}