package com.demondev.infernoRiftsModeration.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BanMenuUtils {

    public static void openBanMenu(Player p) {
        ArrayList<Player> list = new ArrayList<>(p.getServer().getOnlinePlayers());

        Inventory bangui = Bukkit.createInventory(p, 45, ChatColor.BLUE + "Player List");

        for (Player player : list) {

            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            ItemMeta meta = playerHead.getItemMeta();

            meta.setDisplayName(player.getDisplayName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GOLD + "IP: " + ChatColor.RED + player.getAddress());
            meta.setLore(lore);
            playerHead.setItemMeta(meta);

            bangui.addItem(playerHead);
        }
        p.openInventory(bangui);
    }

    public static void openConfirmBanMenu(Player p, Player whoToBan){
        Inventory confirmBanMenu = Bukkit.createInventory(p,9,ChatColor.RED + "Are You sure want to ban this player?");


        ItemStack ban = new ItemStack(Material.LIME_CONCRETE, 1);
        ItemMeta ban_meta = ban.getItemMeta();
        ban_meta.setDisplayName(ChatColor.GREEN + "Ban");
        ban.setItemMeta(ban_meta);
        confirmBanMenu.setItem(3, ban);

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta player_meta = playerHead.getItemMeta();
        player_meta.setDisplayName(whoToBan.getDisplayName());
        playerHead.setItemMeta(player_meta);
        confirmBanMenu.setItem(4, playerHead);

        ItemStack cancel = new ItemStack(Material.BARRIER, 1);
        ItemMeta cancel_meta = cancel.getItemMeta();
        cancel_meta.setDisplayName(ChatColor.RED + "Cancel");
        cancel.setItemMeta(cancel_meta);
        confirmBanMenu.setItem(5, cancel);

        p.openInventory(confirmBanMenu);

    }

}
