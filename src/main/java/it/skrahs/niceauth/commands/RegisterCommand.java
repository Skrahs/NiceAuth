package it.skrahs.niceauth.commands;

import it.skrahs.niceauth.NiceAuth;
import it.skrahs.niceauth.cache.ConfigCache;
import it.skrahs.niceauth.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;




public class RegisterCommand implements CommandExecutor {

    private final NiceAuth plugin;

    public RegisterCommand(NiceAuth plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "This command can be only executed by a player.");
            return true;
        }

        Player player = (Player) commandSender;

        if (args.length != 2 || !args[0].equals(args[1])) {
            player.sendMessage(ChatUtils.color(ConfigCache.REGISTER_CORRECT_USE)); // Command Correct Use
            return true;
        }

        String password = args[0];
        String registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        FileConfiguration config = plugin.getAuthManager().getPasswords();

        config.set(player.getUniqueId().toString() + ".Password", ChatUtils.encryptPassword(password));
        config.set(player.getUniqueId().toString() + ".Registration Date", registrationDate);

        plugin.getAuthManager().savePasswords();
        player.sendMessage(ChatUtils.color(ConfigCache.REGISTER_SUCCESFUL)); // Register was Succesful
        player.kickPlayer(ChatUtils.color(ConfigCache.REGISTER_KICK_MESSAGE)); // Kick Message Register
        return true;
    }

}


