package mc.pleasantvkchannel.pleasantreports;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        saveDefaultConfig();
        instance = this;

        onLoadCommands();

        System.out.println(ChatColor.GREEN + "-----------------------------------");
        System.out.println(ChatColor.GREEN + "PleasantReports - успешно включён");
        System.out.println(ChatColor.GREEN + "Вконтакте vk.com/pleasantvkchannel");
        System.out.println(ChatColor.GREEN + "-----------------------------------");

        saveConfig();
    }

    private void onLoadCommands() {
        getCommand("report").setExecutor(new );
        getCommand("reportbug").setExecutor(new ReportBug());
    }
}