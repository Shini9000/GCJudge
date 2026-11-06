package me.shini9000.gcjudge.commands;

import com.plotsquared.bukkit.util.BukkitUtil;
import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.PlotId;
import me.shini9000.gcjudge.GCJudge;
import me.shini9000.gcjudge.gui.PlotOverviewMenu;
import me.shini9000.gcjudge.utils.PlotUtils;
import me.shini9000.gcjudge.utils.SQLUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Submit implements CommandExecutor {

    private final SQLUtils sqlUtils = new SQLUtils();
    private GCJudge plugin;

    public Submit(GCJudge plugin) {
        this.plugin = plugin;
        plugin.getCommand("submit").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if (!(sender instanceof Player)) { this.plugin.getConfig().getString("Console.error"); return true;}

        Player player = (Player) sender;
        if(!player.hasPermission("gcjudge.command.submit")) {
            player.sendMessage(ChatColor.RED + "Lacking permission: gcjudge.command.submit");
            return true;
        }

        PlotId id = PlotUtils.getId(player);

        if(id == null) {
            player.sendMessage(ChatColor.RED + "You must stand on your plot");
            return true;
        }

        PlotPlayer p = BukkitUtil.adapt(player);

        boolean isOwner = Bukkit.getOfflinePlayer(p.getCurrentPlot().getOwner()).getName() == player.getName();

        if (!isOwner) {
            player.sendMessage(ChatColor.RED + "You must stand on your plot");
            return true;
        }

        sqlUtils.setPlotTable(player);
        sqlUtils.addPlotID(id.toString());

        new PlotOverviewMenu(GCJudge.getPlayerMenuUtils(player)).open();
        return true;
    }

}