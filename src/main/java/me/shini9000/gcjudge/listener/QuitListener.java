package me.shini9000.gcjudge.listener;

import me.shini9000.gcjudge.GCJudge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        GCJudge.removePlayerMenuUtils(e.getPlayer());
    }
}