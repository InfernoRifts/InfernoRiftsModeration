package com.demondev.infernoRiftsModeration.commands;

import com.demondev.infernoRiftsModeration.InfernoRiftsModeration;
import com.demondev.infernoRiftsModeration.utils.MenuUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanGUICommand implements CommandExecutor {

    private final InfernoRiftsModeration plugin;
    public BanGUICommand(InfernoRiftsModeration plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player p){

            plugin.menuUtils.openBanMenu(p);

        }

        return true;
    }
}
