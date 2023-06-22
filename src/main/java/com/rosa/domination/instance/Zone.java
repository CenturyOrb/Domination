package com.rosa.domination.instance;

import com.rosa.domination.Domination;
import com.rosa.domination.enums.ZoneState;
import com.rosa.domination.utils.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class Zone {

    Domination domination;

    private String name;
    private ZoneState zoneState;
    private int blueCaptureProgress;
    private int redCaptureProgress;
    private Cuboid cuboid;

    public Zone(Domination domination, String name, Cuboid cuboid)   {

        this.domination = domination;

        this.name = name;
        this.cuboid = cuboid;

        zoneState = ZoneState.NEUTRAL;
        blueCaptureProgress = 0;
        redCaptureProgress = 0;

//        System.out.println("Loaded zone " + name );
//        System.out.println("Cords are " + cuboid.getLowerX() + " / " + cuboid.getLowerZ() + " and " + cuboid.getUpperX() + " / " + cuboid.getUpperZ());

    }

    public String getName()   { return name; }

    public ZoneState getZoneState()   { return zoneState; }

    public void setZoneState(ZoneState zoneState)   { this.zoneState = zoneState; }

    public int getBlueCaptureProgress()   { return blueCaptureProgress; }

    public int getRedCaptureProgress()   { return redCaptureProgress; }

    public Cuboid getCuboid()   { return cuboid;
    }

    public void incrementRedCaptureProgress()   {
        redCaptureProgress++;
        updateZoneState();
    }

    public void incrementBlueCaptureProgress()   {
        blueCaptureProgress++;
        updateZoneState();
    }

    private void updateZoneState()   {

        if (redCaptureProgress >= 20 && zoneState != ZoneState.RED)   {
            setZoneState(ZoneState.RED);
            redCaptureProgress = 0;
            blueCaptureProgress = 0;
            System.out.println("Red team has captured " + name + "!");
        }else if (blueCaptureProgress >= 20 && zoneState != ZoneState.BLUE)   {
            setZoneState(ZoneState.BLUE);
            redCaptureProgress = 0;
            blueCaptureProgress = 0;
            System.out.println("Blue team has captured " + name + "!");
        }

    }

    public void reset()   {

        zoneState = ZoneState.NEUTRAL;
        blueCaptureProgress = 0;
        redCaptureProgress = 0;

    }
}
