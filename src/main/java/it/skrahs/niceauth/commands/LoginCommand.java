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
import org.bukkit.potion.PotionEffectType;

public class LoginCommand implements CommandExecutor {

    private final NiceAuth plugin;

    public LoginCommand(NiceAuth plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can be only executed by a player");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ChatUtils.color(ConfigCache.LOGIN_CORRECT_USE)); // Command CORRECT USE
            return true;
        }

        String playerName = player.getName();
        String password = args[0];

        FileConfiguration config = plugin.getAuthManager().getPasswords();

        if (!config.contains(playerName)) {
            player.sendMessage(ChatUtils.color(ConfigCache.NOT_REGISTERED)); // Player NOT REGISTERED
            return true;
        }

        String storedPassword = ChatUtils.decryptPassword(config.getString(playerName + ".Password"));
        if (storedPassword.equals(password)) {
            player.sendMessage(ChatUtils.color(ConfigCache.LOGIN_SUCCESFUL)); // Login Successful
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            player.removePotionEffect(PotionEffectType.JUMP);
            player.setWalkSpeed(0.2f);
            player.setFlySpeed(0.1f);

            plugin.getAuthManager().setLoginInProgress(player, false);
            plugin.getAuthManager().setLoggedIn(player, true);
        } else {
            player.sendMessage(ChatUtils.color(ConfigCache.WRONG_PASSWORD)); // Wrong Password
        }
        return true;
    }
}
