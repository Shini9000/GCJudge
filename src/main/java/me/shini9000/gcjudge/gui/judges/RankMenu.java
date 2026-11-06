package me.shini9000.gcjudge.gui.judges;

import me.shini9000.gcjudge.gui.Menu;
import me.shini9000.gcjudge.utils.PlayerMenuUtils;
import me.shini9000.gcjudge.utils.PlotUtils;
import me.shini9000.gcjudge.utils.SQLUtils;
import me.shini9000.gcjudge.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RankMenu extends Menu {

    private final Utils utils = new Utils();
    private final SQLUtils sqlUtils = new SQLUtils();

    public RankMenu(PlayerMenuUtils playerMenuUtils) {
        super(playerMenuUtils);
    }

    @Override
    public String getMenuName() {
        return ChatColor.RED + "Ranks";
    }

    @Override
    public int getSlots() {
        return 18;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        OfflinePlayer ptarget = sqlUtils.getPlayerByID(PlotUtils.getId(p).toString());
        String name = ptarget.getName();

        if (e.getCurrentItem() == null) return;

        switch (e.getCurrentItem().getType()) {

            case ORANGE_BANNER -> {
                p.closeInventory();
                p.performCommand("lp user "+name+" parent add rank1");
                p.sendMessage(ChatColor.GOLD + "Plot Accepted! Rank up to rank1");
                sqlUtils.setPlotStatus(PlotUtils.getId(p).toString(), "ACCEPTED");

            }
            case BLUE_BANNER -> {
                p.closeInventory();
                p.performCommand("lp user "+name+" parent add rank2");
                p.sendMessage(ChatColor.GOLD + "Plot Accepted! Rank up to rank2");
                sqlUtils.setPlotStatus(PlotUtils.getId(p).toString(), "ACCEPTED");
            }
            case GREEN_BANNER -> {
                p.closeInventory();
                p.performCommand("lp user "+name+" parent add rank3");
                p.sendMessage(ChatColor.GOLD + "Plot Accepted! Rank up to rank3");
                sqlUtils.setPlotStatus(PlotUtils.getId(p).toString(), "ACCEPTED");
            }
            case CYAN_BANNER -> {
                p.closeInventory();
                p.performCommand("lp user "+name+" parent add rank4");
                p.sendMessage(ChatColor.GOLD + "Plot Accepted! Rank up to rank4");
                sqlUtils.setPlotStatus(PlotUtils.getId(p).toString(), "ACCEPTED");
            }
            case PURPLE_BANNER -> {
                p.closeInventory();
                p.performCommand("lp user "+name+" parent add rank5");
                p.sendMessage(ChatColor.GOLD + "Plot Accepted! Rank up to rank5");
                sqlUtils.setPlotStatus(PlotUtils.getId(p).toString(), "ACCEPTED");
            }
            case RED_BANNER -> {
                p.closeInventory();
                p.performCommand("lp user "+name+" parent add rank6");
                p.sendMessage(ChatColor.GOLD + "Plot Accepted! Rank up to rank6");
                sqlUtils.setPlotStatus(PlotUtils.getId(p).toString(), "ACCEPTED");
            }
        }

        e.setCancelled(true);
    }

    @Override
    public void setMenuItems() {
        inventory.setItem(2, utils.createGuiItem(Material.ORANGE_BANNER,
                ChatColor.GOLD + "rank1", 1));
        inventory.setItem(4, Utils.createGuiItem(Material.BLUE_BANNER,
                ChatColor.BLUE + "rank2", 1));
        inventory.setItem(6, Utils.createGuiItem(Material.GREEN_BANNER,
                ChatColor.GREEN + "rank3", 1));
        inventory.setItem(11, Utils.createGuiItem(Material.CYAN_BANNER,
                ChatColor.AQUA + "rank4", 1));
        inventory.setItem(13, Utils.createGuiItem(Material.PURPLE_BANNER,
                ChatColor.DARK_PURPLE + "rank5", 1));
        inventory.setItem(15, Utils.createGuiItem(Material.RED_BANNER,
                ChatColor.RED + "rank6", 1));
    }
}