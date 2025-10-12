package com.demondev.infernoRiftsModeration.commands.admin;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ban implements CommandExecutor {

    private static final Pattern DURATION_PATTERN = Pattern.compile("(\\d+)([smhdwy])");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("modtools.ban")) {
            sender.sendMessage("You do not have permission to use this command.");
            return true;
        }

        
        if (args.length < 1) {
            sender.sendMessage("Usage: /" + label + " <player> [duration] [reason]");
            return true;
        }

       
        @SuppressWarnings("deprecation") 
        OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[0]);
        if (!offlineTarget.hasPlayedBefore() && !offlineTarget.isOnline()) {
            sender.sendMessage("Player '" + args[0] + "' has never joined the server.");
            return true;
        }

        Player onlineTarget = offlineTarget.getPlayer(); 

    
        Duration banDuration = null;
        Instant expires = null;
        int reasonStartIndex = 1;
        if (args.length > 1) {
            Matcher matcher = DURATION_PATTERN.matcher(args[1]);
            if (matcher.matches()) {
                long amount = Long.parseLong(matcher.group(1));
                String unit = matcher.group(2);
                banDuration = switch (unit) {
                    case "s" -> Duration.of(amount, ChronoUnit.SECONDS);
                    case "m" -> Duration.of(amount, ChronoUnit.MINUTES);
                    case "h" -> Duration.of(amount, ChronoUnit.HOURS);
                    case "d" -> Duration.of(amount, ChronoUnit.DAYS);
                    case "w" -> Duration.of(amount * 7, ChronoUnit.DAYS);
                    case "y" -> Duration.of(amount * 365, ChronoUnit.DAYS);
                    default -> null;
                };
                if (banDuration != null) {
                    expires = Instant.now().plus(banDuration);
                    reasonStartIndex = 2;
                }
            }
        }
        String reason = "Banned by staff.";
        if (args.length > reasonStartIndex) {
            StringBuilder reasonBuilder = new StringBuilder();
            for (int i = reasonStartIndex; i < args.length; i++) {
                reasonBuilder.append(args[i]).append(" ");
            }
            reason = reasonBuilder.toString().trim();
        }

        String source = sender.getName();

    
        Bukkit.getBanList(BanList.Type.PROFILE).addBan(offlineTarget.getUniqueId().toString(), reason, Date.from(expires), source);



        boolean ipBanned = false;
        String ip = null;
        if (onlineTarget != null) {
            ip = onlineTarget.getAddress().getHostString();
            Bukkit.getBanList(BanList.Type.IP).addBan(ip, reason, Date.from(expires), source);
            ipBanned = true;
        }

        
        if (onlineTarget != null) {
            String kickMessage = "You are banned: " + reason + (banDuration != null ? " for " + formatDuration(banDuration) : " permanently.");
            onlineTarget.kickPlayer(kickMessage);
        }

       
        String message = "Banned " + offlineTarget.getName() + " (UUID: " + offlineTarget.getUniqueId() + ")";
        if (ipBanned) {
            message += " and IP: " + ip;
        }
        message += " for: " + reason + (banDuration != null ? " (Duration: " + formatDuration(banDuration) + ")" : " (Permanent)");
        sender.sendMessage(message);

       
        Bukkit.getLogger().info(source + " banned " + offlineTarget.getName() + " for: " + reason);

        return true;
    }

    private String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        StringBuilder sb = new StringBuilder();
        if (days > 0) sb.append(days).append("d ");
        if (hours > 0) sb.append(hours).append("h ");
        if (minutes > 0) sb.append(minutes).append("m ");
        if (seconds > 0) sb.append(seconds).append("s ");
        return sb.toString().trim();
    }
}
