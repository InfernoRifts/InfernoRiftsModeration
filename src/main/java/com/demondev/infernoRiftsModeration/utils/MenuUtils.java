package com.demondev.infernoRiftsModeration.utils;

import com.demondev.infernoRiftsModeration.InfernoRiftsModeration;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.checkerframework.framework.qual.IgnoreInWholeProgramInference;

import java.util.ArrayList;
import java.util.List;

public class MenuUtils {

    private final InfernoRiftsModeration plugin;
    public MenuUtils(InfernoRiftsModeration plugin){
        this.plugin = plugin;
    }

    public void openBanMenu(Player player) {

        Inventory bangui = Bukkit.createInventory(player, 45, ChatColor.BLUE + "Player List");

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            
            for (Player p : Bukkit.getOnlinePlayers()) {
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) head.getItemMeta();
                if (meta != null) {
                    OfflinePlayer offline = Bukkit.getOfflinePlayer(p.getUniqueId());
                    meta.setOwningPlayer(offline);
                    meta.setDisplayName(ChatColor.YELLOW + p.getName());
                    List<String> lore = List.of(
                            ChatColor.GOLD + "IP: " + ChatColor.RED + p.getAddress().getAddress().getHostAddress()
                    );
                    meta.setLore(lore);
                    head.setItemMeta(meta);
                }

                bangui.addItem(head);
            }

            player.openInventory(bangui);
        }, 2L);
    }

    public static void openConfirmBanMenu(Player p, Player whoToBan){
        Inventory confirmBanMenu = Bukkit.createInventory(p,9,ChatColor.RED + "Are You sure want to ban this player?");


        ItemStack ban = new ItemStack(Material.LIME_CONCRETE, 1);
        ItemMeta ban_meta = ban.getItemMeta();
        ban_meta.setDisplayName(ChatColor.GREEN + "Ban");
        ban.setItemMeta(ban_meta);
        confirmBanMenu.setItem(3, ban);

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        if (meta != null) {
            OfflinePlayer offline = Bukkit.getOfflinePlayer(whoToBan.getPlayer().getUniqueId());
            meta.setOwningPlayer(offline);
            meta.setDisplayName(ChatColor.YELLOW + "" + whoToBan);
            head.setItemMeta(meta);
        }
        confirmBanMenu.setItem(4, head);

        ItemStack cancel = new ItemStack(Material.BARRIER, 1);
        ItemMeta cancel_meta = cancel.getItemMeta();
        cancel_meta.setDisplayName(ChatColor.RED + "Cancel");
        cancel.setItemMeta(cancel_meta);
        confirmBanMenu.setItem(5, cancel);

        p.openInventory(confirmBanMenu);

    }

}
