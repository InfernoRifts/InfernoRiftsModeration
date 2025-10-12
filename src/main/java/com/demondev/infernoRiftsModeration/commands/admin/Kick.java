package com.demondev.infernoRiftsModeration.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("modtools.kick")) {
          sender.sendMessage("You do not have permission to use this command!!!!");
          return true;
        }
        if (args.length < 1) {
            sender.sendMessage("Usage: /" + label + " <player> [reason]");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("Player '" + args[0] + "' not found or not online.");
            return true;
        }
        String reason = "Kicked by staff.";
        if (args.length > 1) {
            StringBuilder reasonBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                reasonBuilder.append(args[i]).append(" ");
            }
            reason = reasonBuilder.toString().trim();
        }
        target.kickPlayer(reason);
        sender.sendMessage("Kicked " + target.getName() + " for: " + reason);
        Bukkit.getLogger().info(sender.getName() + " kicked " + target.getName() + " for: " + reason);
        return true;
    }
}
