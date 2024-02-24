package it.skrahs.niceauth.listener;

import it.skrahs.niceauth.NiceAuth;
import it.skrahs.niceauth.cache.ConfigCache;
import it.skrahs.niceauth.utils.ChatUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinListener implements Listener {

    private final NiceAuth plugin;

    public JoinListener(NiceAuth plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        FileConfiguration config = plugin.getAuthManager().getPasswords();

        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 254, false, false));
        player.setWalkSpeed(0.0f);

        if (!config.contains(playerName)) {
            plugin.getAuthManager().setRegistrationInProgress(player, true);
            player.sendMessage(ChatUtils.color(ConfigCache.WELCOME_REGISTER_MESSAGE));
        } else {
            plugin.getAuthManager().setLoginInProgress(player, true);
            player.sendMessage(ChatUtils.color(ConfigCache.WELCOME_LOGIN_MESSAGE));
        }
    }
}