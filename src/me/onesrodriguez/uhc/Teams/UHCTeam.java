package me.onesrodriguez.uhc.Teams;

import java.util.List;
import org.bukkit.ChatColor;

public interface UHCTeam {
    
    public String getName();
    
    public List<String> getMembers();
    
    public ChatColor getColor();
}
