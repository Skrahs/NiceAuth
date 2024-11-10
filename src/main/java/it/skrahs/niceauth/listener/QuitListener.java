package it.skrahs.niceauth.listener;

import it.skrahs.niceauth.NiceAuth;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private final NiceAuth niceAuth;

    public QuitListener(NiceAuth niceAuth) {
        this.niceAuth = niceAuth;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (niceAuth.getAuthManager().isRegistrationInProgress(player)) {
            niceAuth.getAuthManager().setRegistrationInProgress(player);
        } else if (niceAuth.getAuthManager().isLoginInProgress(player)) {
            niceAuth.getAuthManager().setLoginInProgress(player);
        }
    }
}
