package com.demondev.infernoRiftsModeration.commands;

import com.demondev.infernoRiftsModeration.commands.admin.Ban;
import com.demondev.infernoRiftsModeration.commands.admin.Kick;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class ModToolsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /modtools <subcommand> [args]");
            sender.sendMessage("Available subcommands: kick");
            return true;
        }

        String subcommand = args[0].toLowerCase();
        String[] subArgs = Arrays.copyOfRange(args, 1, args.length);

        if (subcommand.equals("kick")) {
            return new Kick().onCommand(sender, command, "kick", subArgs);
        }
        if (subcommand.equals("ban")) {
            return new Ban().onCommand(sender, command, "ban", subArgs);
        }
        else {
            sender.sendMessage("Unknown subcommand: " + subcommand);
            sender.sendMessage("Available subcommands: kick, ban");
            return true;
        }
    }
}