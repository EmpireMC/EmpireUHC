package me.onesrodriguez.uhc.Teams;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;

public class Yellow implements UHCTeam{

    private final List<String> members = new ArrayList<String>();
    
    @Override
    public String getName() {
        return "Yellow";
    }

    @Override
    public List<String> getMembers() {
        return members;
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.YELLOW;
    }

}
