package me.onesrodriguez.uhc.Util;

import me.onesrodriguez.uhc.UHCCore;

public class TimeManager implements Runnable{

    @Override
    public void run() {
        
        if(UHCCore.getTicks() > 0){
            UHCCore.setTicks(UHCCore.getTicks() -1);
        }
        
        
        
    }

}
