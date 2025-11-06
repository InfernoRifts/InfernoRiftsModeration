package com.demondev.infernoRiftsModeration.listeners;

import com.demondev.infernoRiftsModeration.InfernoRiftsModeration;
import com.demondev.infernoRiftsModeration.utils.MenuUtils;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BanInventoryListener implements Listener {

    private final InfernoRiftsModeration plugin;
    public BanInventoryListener(InfernoRiftsModeration plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE + "Player List")){

            if (e.getCurrentItem().getType() == Material.PLAYER_HEAD){

                Player whoToBan = p.getServer().getPlayerExact(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));

                MenuUtils.openConfirmBanMenu(p, whoToBan);

            }
        }else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Are You sure want to ban this player?")){

            if (e.getCurrentItem().getType() == Material.BARRIER){
                plugin.menuUtils.openBanMenu(p);
          }else if(e.getCurrentItem().getType() == Material.LIME_CONCRETE){
                String name = ChatColor.stripColor(e.getClickedInventory().getItem(4).getItemMeta().getDisplayName());
                p.getServer().getBanList(BanList.Type.PROFILE).addBan(name, "Admin decided", null, null);
                p.sendMessage(ChatColor.GREEN + "Banned Player");
            }
        }
        e.setCancelled(true);

    }

}
