package it.skrahs.niceauth.cache;

import it.skrahs.niceauth.NiceAuth;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigCache {

    public static String LOGIN_CORRECT_USE, NOT_REGISTERED,WRONG_PASSWORD,LOGIN_SUCCESFUL, WELCOME_LOGIN_MESSAGE, LOGIN_BIG_ERROR, ONLY_LOGIN_PHASE, AFTER_ACCESS;
    public static String REGISTER_CORRECT_USE, REGISTER_SUCCESFUL, REGISTER_BIG_ERROR, REGISTER_KICK_MESSAGE, WELCOME_REGISTER_MESSAGE, ONLY_REGISTRATION_PHASE;
    public static String SECRET_KEY = "qbBQoizDmR/22z7Q2EsS3Q==";
    public static String CHANGEPASS_CORRECTUSE, CHANGEPASS_SUCCESSFULLY;

    public static void load() {
        FileConfiguration config = NiceAuth.getInstance().getConfig();

        // Login Section

        LOGIN_CORRECT_USE = config.getString("Login-Correct-Use");
        NOT_REGISTERED = config.getString("Not-Registered");
        WRONG_PASSWORD = config.getString("Wrong-Password");
        LOGIN_SUCCESFUL = config.getString("Login-Succesful");
        LOGIN_BIG_ERROR = config.getString("Login-Big-Error");
        WELCOME_LOGIN_MESSAGE = config.getString("Welcome-Login-Message");
        ONLY_LOGIN_PHASE = config.getString("Only-Login-Phase");
        AFTER_ACCESS = config.getString("After-Access");


        // Register Section

        REGISTER_CORRECT_USE = config.getString("Register-Correct-Use");
        REGISTER_SUCCESFUL = config.getString("Register-Succesful");
        REGISTER_BIG_ERROR = config.getString("Register-Big-Error");
        REGISTER_KICK_MESSAGE = config.getString("Register-Kick-Message");
        WELCOME_REGISTER_MESSAGE = config.getString("Welcome-Register-Message");
        ONLY_REGISTRATION_PHASE = config.getString("Only-Register-Phase");

        // Password Section

        CHANGEPASS_CORRECTUSE = config.getString("ChangePassw-Correct-Use");
        CHANGEPASS_SUCCESSFULLY = config.getString("ChangePassw-Success");
    }
}
