package it.skrahs.niceauth.commands;

import it.skrahs.niceauth.NiceAuth;
import it.skrahs.niceauth.cache.ConfigCache;
import it.skrahs.niceauth.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ChangepasswordCommand implements CommandExecutor {

    private final NiceAuth plugin;

    public ChangepasswordCommand(NiceAuth plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length < 2) {
                player.sendMessage(ChatUtils.color(ConfigCache.CHANGEPASS_CORRECTUSE));
            } else {
                String currentPassword = args[0];
                String newPassword = args[1];
                FileConfiguration config = plugin.getAuthManager().getPasswords();

                String storedPassword = ChatUtils.decryptPassword(config.getString(player.getName() + ".Password"));
                if(!currentPassword.equals(storedPassword)){
                    player.sendMessage(ChatUtils.color(ConfigCache.WRONG_PASSWORD));
                    return true;
                }

                config.set(player.getName() + ".Password", ChatUtils.encryptPassword(newPassword));
                plugin.getAuthManager().savePasswords();
                player.sendMessage(ChatUtils.color(ConfigCache.CHANGEPASS_SUCCESSFULLY));
            }
        }
        return true;
    }
}
