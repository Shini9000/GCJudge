package me.shini9000.gcjudge.sql;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite {

    private final JavaPlugin plugin;
    private Connection connection;

    public SQLite(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public Connection forceConnection() throws SQLException {
        String dbPath = new File(plugin.getDataFolder(), "gcjudge.db").getAbsolutePath();
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        return connection;
    }

    public Connection openConnection() throws SQLException {
        if (checkConnection()) {
            return connection;
        }
        return forceConnection();
    }

    public boolean checkConnection() throws SQLException {
        return (connection != null) && !connection.isClosed();
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean closeConnection() throws SQLException {
        if (connection == null) return false;
        connection.close();
        connection = null;
        return true;
    }
}