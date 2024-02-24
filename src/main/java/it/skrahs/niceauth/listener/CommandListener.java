package it.skrahs.niceauth.listener;

import it.skrahs.niceauth.NiceAuth;
import it.skrahs.niceauth.cache.ConfigCache;
import it.skrahs.niceauth.utils.ChatUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;


public class CommandListener implements Listener {

    private final NiceAuth plugin;

    public CommandListener(NiceAuth plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().toLowerCase();
        String command = message.replace("/", "");

        if (plugin.getAuthManager().isRegistrationInProgress(event.getPlayer())) {
            if (!command.startsWith("register")) {
                event.getPlayer().sendMessage(ChatUtils.color(ConfigCache.ONLY_REGISTRATION_PHASE));
                event.setCancelled(true);
            }
        }
        else if (plugin.getAuthManager().isLoginInProgress(event.getPlayer())) {
            if (!command.startsWith("login")) {
                event.getPlayer().sendMessage(ChatUtils.color(ConfigCache.ONLY_LOGIN_PHASE));
                event.setCancelled(true);
            }
        }
        else if (plugin.getAuthManager().isLoggedIn(event.getPlayer())) {
            if (command.startsWith("register") || command.startsWith("login")) {
                event.getPlayer().sendMessage(ChatUtils.color(ConfigCache.AFTER_ACCESS));
                event.setCancelled(true);
            }
        }
    }
}
