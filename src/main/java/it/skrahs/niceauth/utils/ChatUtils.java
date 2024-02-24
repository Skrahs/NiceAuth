package it.skrahs.niceauth.utils;

import it.skrahs.niceauth.cache.ConfigCache;
import org.bukkit.ChatColor;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ChatUtils {

    public static String color(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String encryptPassword(String password){
        SecretKeySpec secretKeySpec = new SecretKeySpec(ConfigCache.SECRET_KEY.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes;
            encryptedBytes = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (IllegalBlockSizeException | InvalidKeyException | BadPaddingException | NoSuchAlgorithmException |
                 NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decryptPassword(String cryptedPassword){
        try{
            SecretKeySpec secretKeySpec = new SecretKeySpec(ConfigCache.SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(cryptedPassword));
            return new String(decryptedBytes);
        }catch (Exception ignored){}
        return "c";
    }


}