package me.shini9000.gcjudge;

import me.shini9000.gcjudge.commands.*;
import me.shini9000.gcjudge.listener.MenuListener;
import me.shini9000.gcjudge.sql.SQLite;
import me.shini9000.gcjudge.sql.SQLCreate;
import me.shini9000.gcjudge.utils.LPUtils;
import me.shini9000.gcjudge.utils.PlayerMenuUtils;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static me.shini9000.gcjudge.utils.PlayerMenuUtils.playerMenuUtilityMap;

public final class GCJudge extends JavaPlugin implements Listener {

    private static GCJudge plugin;
    private ExecutorService dbExecutor;
    public ExecutorService db() { return dbExecutor; }
    public SQLCreate data;
    public SQLite SQL;
    public LuckPerms luckPerms;

    private static final HashMap<Player, PlayerMenuUtils> playerMenuUtilsMap = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new me.shini9000.gcjudge.listener.QuitListener(), this);
        dbExecutor = Executors.newSingleThreadExecutor(r -> new Thread(r, "GCJudge-DB"));

        Bukkit.getConsoleSender().sendMessage("Enabling plugin: "
                + getDescription().getName()
                + " Version: "
                + getDescription().getVersion());
        //noinspection ResultOfMethodCallIgnored
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        new Submit(this);
        new Judge(this);
        new SetPlotTitle(this);
        new SetPlotLore(this);
        new SetPlotComment(this);
        new MenuListener(this);

        // Load an instance of 'LuckPerms' using the services manager.
        this.luckPerms = getServer().getServicesManager().load(LuckPerms.class);

        new LPUtils(this.luckPerms);

        this.SQL = new SQLite(this);
        this.data = new SQLCreate(this);

        try {
            SQL.openConnection();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Database not connected.");
            e.printStackTrace();
        }

        try {
            if (SQL.checkConnection()){
                Bukkit.getLogger().info("Database is connected.");
                data.createTable();
                this.getServer().getPluginManager().registerEvents(this, this);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        dbExecutor.shutdown();
        try { dbExecutor.awaitTermination(5, TimeUnit.SECONDS); } catch (InterruptedException ignored) {}

        Bukkit.getConsoleSender().sendMessage(getDescription().getName());
        try {
            SQL.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static GCJudge getInstance() {
        return (GCJudge) getPlugin(GCJudge.class);
    }

    //Provide a player and return a menu system for that player
    //create one if they don't already have one
    public static PlayerMenuUtils getPlayerMenuUtils(Player p) {
        PlayerMenuUtils playerMenuUtils;
        if (!(playerMenuUtilsMap.containsKey(p))) { //See if the player has a playermenutils "saved" for them

            //This player doesn't. Make one for them and add it to the hashmap
            playerMenuUtils = new PlayerMenuUtils(p);
            playerMenuUtilsMap.put(p, playerMenuUtils);

            return playerMenuUtils;
        } else {
            return playerMenuUtilsMap.get(p); //Return the object by using the provided player
        }
    }
    public static GCJudge getPlugin() {
        return plugin;
    }
    public static void removePlayerMenuUtils(Player p) { playerMenuUtilityMap.remove(p); }

}