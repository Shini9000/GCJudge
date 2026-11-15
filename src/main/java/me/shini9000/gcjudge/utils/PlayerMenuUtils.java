package me.shini9000.gcjudge.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerMenuUtils {
    private Player owner;

    public static final Map<Player, PlayerMenuUtils> playerMenuUtilityMap = new ConcurrentHashMap<>();

    @EventHandler
    public void onQuit(PlayerQuitEvent e) { playerMenuUtilityMap.remove(e.getPlayer()); }
    public PlayerMenuUtils(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }
}
