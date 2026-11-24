package me.shini9000.gcjudge.gui.judges;

import me.shini9000.gcjudge.gui.Menu;
import me.shini9000.gcjudge.utils.PlayerMenuUtils;
import me.shini9000.gcjudge.utils.PlotUtils;
import me.shini9000.gcjudge.utils.SQLUtils;
import me.shini9000.gcjudge.utils.Utils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
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
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        Player p = (Player) e.getWhoClicked();
        OfflinePlayer ptarget = sqlUtils.getPlayerByID(PlotUtils.getId(p).toString());
        LuckPerms lp = LuckPermsProvider.get();
        String name = ptarget.getName();

        if (e.getCurrentItem() == null) return;

        String ranks = "ranks";
        Group group = lp.getGroupManager().getGroup(ranks);
        if (group == null) return;
        //if (lp.getUserManager().getUser(ptarget.getName()).getInheritedGroups(group) {

        //}

//        switch (e.getCurrentItem().getType()) {
//
//            case ORANGE_BANNER -> {
//                p.closeInventory();
//                //p.performCommand("lp user "+name+" parent add boilerkeeper");
//
//
//                p.sendMessage(ChatColor.GOLD + "Plot Accepted! Rank up to boilerkeeper");
//                sqlUtils.setPlotStatus(PlotUtils.getId(p).toString(), "ACCEPTED");
//
//            }
//            case BLUE_BANNER -> {
//                p.closeInventory();
//                p.performCommand("lp user "+name+" promote add blacksmith");
//                p.sendMessage(ChatColor.GOLD + "Plot Accepted! Rank up to blacksmith");
//                sqlUtils.setPlotStatus(PlotUtils.getId(p).toString(), "ACCEPTED");
//            }
//            case GREEN_BANNER -> {
//                p.closeInventory();
//                p.performCommand("lp user "+name+" parent add hatmaker");
//                p.sendMessage(ChatColor.GOLD + "Plot Accepted! Rank up to hatmaker");
//                sqlUtils.setPlotStatus(PlotUtils.getId(p).toString(), "ACCEPTED");
//            }
//            case CYAN_BANNER -> {
//                p.closeInventory();
//                p.performCommand("lp user "+name+" parent add guardian");
//                p.sendMessage(ChatColor.GOLD + "Plot Accepted! Rank up to guardian");
//                sqlUtils.setPlotStatus(PlotUtils.getId(p).toString(), "ACCEPTED");
//            }
//            case PURPLE_BANNER -> {
//                p.closeInventory();
//                p.performCommand("lp user "+name+" parent add shishigami");
//                p.sendMessage(ChatColor.GOLD + "Plot Accepted! Rank up to shishigami");
//                sqlUtils.setPlotStatus(PlotUtils.getId(p).toString(), "ACCEPTED");
//            }
////            case RED_BANNER -> {
////                p.closeInventory();
////                p.performCommand("lp user "+name+" parent add rank6");
////                p.sendMessage(ChatColor.GOLD + "Plot Accepted! Rank up to rank6");
////                sqlUtils.setPlotStatus(PlotUtils.getId(p).toString(), "ACCEPTED");
////            }
//        }

        e.setCancelled(true);
    }

    @Override
    public void setMenuItems() {
        inventory.setItem(2, utils.createGuiItem(Material.ORANGE_BANNER,
                ChatColor.GOLD + "boilerkeeper", 1));
        inventory.setItem(4, Utils.createGuiItem(Material.BLUE_BANNER,
                ChatColor.BLUE + "blacksmith", 1));
        inventory.setItem(6, Utils.createGuiItem(Material.GREEN_BANNER,
                ChatColor.GREEN + "hatmaker", 1));
        inventory.setItem(11, Utils.createGuiItem(Material.CYAN_BANNER,
                ChatColor.AQUA + "guardian", 1));
        inventory.setItem(13, Utils.createGuiItem(Material.PURPLE_BANNER,
                ChatColor.DARK_PURPLE + "shishigami", 1));
        //inventory.setItem(15, Utils.createGuiItem(Material.RED_BANNER,
        //        ChatColor.RED + "rank6", 1));
    }
}