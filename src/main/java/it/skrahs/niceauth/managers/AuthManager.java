package it.skrahs.niceauth.managers;

import it.skrahs.niceauth.NiceAuth;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthManager {
    private final Map<UUID, Boolean> registrationInProgressMap = new HashMap<>();
    private final Map<UUID, Boolean> loginInProgressMap = new HashMap<>();
    private final File file;
    private final FileConfiguration passwords;
    public AuthManager(){
        file = new File(NiceAuth.getInstance().getDataFolder(), "passwords.yml");
        passwords = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getPasswords() {
        return passwords;
    }

    public void savePasswords(){
        try {
            passwords.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setRegistrationInProgress(Player player, boolean value) {
        registrationInProgressMap.put(player.getUniqueId(), value);
    }

    public void setLoginInProgress(Player player, boolean value) {
        registrationInProgressMap.replace(player.getUniqueId(), false);
        loginInProgressMap.put(player.getUniqueId(), value);
    }

    public void setLoggedIn(Player player, boolean value) {
        loginInProgressMap.put(player.getUniqueId(), !value);
    }

    public boolean isRegistrationInProgress(Player player) {
        return registrationInProgressMap.getOrDefault(player.getUniqueId(), false);
    }

    public boolean isLoginInProgress(Player player) {
        return loginInProgressMap.getOrDefault(player.getUniqueId(), false);
    }

    public boolean isLoggedIn(Player player) {
        return !loginInProgressMap.getOrDefault(player.getUniqueId(), false);
    }
}
