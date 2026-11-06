package me.shini9000.gcjudge.utils;

import org.bukkit.entity.Player;

public class PlayerMenuUtils {
    private Player owner;

    public PlayerMenuUtils(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }
}
