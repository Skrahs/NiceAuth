package it.skrahs.niceauth.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AuthCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;

            player.sendMessage(ChatColor.RED + "NiceAuth 1.0 By Skrahs | t.me/minecraftprotocol");
        }
        return true;
    }
}
