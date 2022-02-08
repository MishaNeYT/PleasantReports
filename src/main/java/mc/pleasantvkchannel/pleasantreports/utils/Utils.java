package mc.pleasantvkchannel.pleasantreports.utils;

import mc.pleasantvkchannel.pleasantreports.Main;
import org.bukkit.ChatColor;

public class Utils {

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String getString(String msg) {
        return Main.getInstance().getConfig().getString(msg);
    }

    public static Boolean getBoolean(Boolean msg) {
        return Main.getInstance().getConfig().getBoolean(String.valueOf(msg));
    }
}