package it.skrahs.niceauth;

import it.skrahs.niceauth.cache.ConfigCache;
import it.skrahs.niceauth.commands.AuthCommand;
import it.skrahs.niceauth.commands.ChangepasswordCommand;
import it.skrahs.niceauth.commands.LoginCommand;
import it.skrahs.niceauth.commands.RegisterCommand;
import it.skrahs.niceauth.listener.CommandListener;
import it.skrahs.niceauth.listener.JoinListener;
import it.skrahs.niceauth.listener.QuitListener;
import it.skrahs.niceauth.managers.AuthManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class NiceAuth extends JavaPlugin {

    private static Plugin instance;
    private AuthManager authManager;

    public static Plugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        loadCache();

        // Inizializzazione dell'AuthManager
        authManager = new AuthManager();

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        getCommand("register").setExecutor(new RegisterCommand(this));
        getCommand("login").setExecutor(new LoginCommand(this));
        getCommand("auth").setExecutor(new AuthCommand());
        getCommand("changepassword").setExecutor(new ChangepasswordCommand(this));

        getLogger().info("  _   _ _                        _   _     \n" +
                "  | \\ | (_)            /\\        | | | |    \n" +
                "  |  \\| |_  ___ ___   /  \\  _   _| |_| |__  \n" +
                "  | . ` | |/ __/ _ \\ / /\\ \\| | | | __| '_ \\ \n" +
                "  | |\\  | | (_|  __// ____ \\ |_| | |_| | | |\n" +
                "  |_| \\_|_|\\___\\___/_/    \\_\\__,_|\\__|_| |_|\n" +
                "                                           \n" +
                "  Plugin was started Succesfully           \n" +
                "  Version: 1.0                             \n" +
                "  Author: Skrahs                           \n" +
                "\n");
    }

    @Override
    public void onDisable() {

        getLogger().info("  _   _ _                        _   _     \n" +
                "  | \\ | (_)            /\\        | | | |    \n" +
                "  |  \\| |_  ___ ___   /  \\  _   _| |_| |__  \n" +
                "  | . ` | |/ __/ _ \\ / /\\ \\| | | | __| '_ \\ \n" +
                "  | |\\  | | (_|  __// ____ \\ |_| | |_| | | |\n" +
                "  |_| \\_|_|\\___\\___/_/    \\_\\__,_|\\__|_| |_|\n" +
                "                                           \n" +
                "  Plugin was disabled Succesfully           \n" +
                "  Version: 1.0                             \n" +
                "  Author: Skrahs                           \n" +
                "\n");

    }

    public void loadCache() {
        ConfigCache.load();
    }

    public AuthManager getAuthManager() {
        return authManager;
    }
}
