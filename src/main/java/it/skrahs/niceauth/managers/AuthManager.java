package it.skrahs.niceauth.managers;

import it.skrahs.niceauth.NiceAuth;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class AuthManager {
    private final List<UUID> registrationInProgressList = new ArrayList<>();
    private final List<UUID> loginInProgressList = new ArrayList<>();
    private final File file;
    private final FileConfiguration passwords;

    public AuthManager() {
        file = new File(NiceAuth.getInstance().getDataFolder(), "passwords.yml");
        passwords = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getPasswords() {
        return passwords;
    }

    public void savePasswords() {
        try {
            passwords.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setRegistrationInProgress(Player player) {
        if (registrationInProgressList.contains(player.getUniqueId())) {
            registrationInProgressList.remove(player.getUniqueId());
        } else {
            registrationInProgressList.add(player.getUniqueId());
        }
    }

    public void setLoginInProgress(Player player) {
        registrationInProgressList.remove(player.getUniqueId());
        if (loginInProgressList.contains(player.getUniqueId())) {
            loginInProgressList.remove(player.getUniqueId());
        } else {
            loginInProgressList.add(player.getUniqueId());
        }
    }

    public void setLoggedIn(Player player) {
        loginInProgressList.remove(player.getUniqueId());
    }

    public boolean isRegistrationInProgress(Player player) {
        return registrationInProgressList.contains(player.getUniqueId());
    }

    public boolean isLoginInProgress(Player player) {
        return loginInProgressList.contains(player.getUniqueId());
    }

    public boolean isLoggedIn(Player player) {
        return !loginInProgressList.contains(player.getUniqueId());
    }
}
