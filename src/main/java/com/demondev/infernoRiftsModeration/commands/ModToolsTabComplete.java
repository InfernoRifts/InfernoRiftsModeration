package com.demondev.infernoRiftsModeration.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModToolsTabComplete implements TabCompleter {

    private final List<String> subcommands = Arrays.asList("kick, ban");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            // Suggest subcommands
            String partial = args[0].toLowerCase();
            for (String sub : subcommands) {
                if (sub.startsWith(partial) && sender.hasPermission("modtools." + sub)) {
                    completions.add(sub);
                }
            }
        } else if (args.length >= 2) {
            String subcommand = args[0].toLowerCase();
            if (subcommands.contains(subcommand) && sender.hasPermission("modtools." + subcommand)) {
                if (subcommand.equals("kick")) {
                    if (args.length == 2) {
                        String partial = args[1].toLowerCase();
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.getName().toLowerCase().startsWith(partial)) {
                                completions.add(player.getName());
                            }
                        }
                    }
                }
            } else if (subcommand.equals("ban")) {
                if (args.length == 2) {
                    String partial = args[1].toLowerCase();
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.getName().toLowerCase().startsWith(partial)) {
                            completions.add(player.getName());
                        }
                    }
                }
            }
        }

        return completions;
    }
}
