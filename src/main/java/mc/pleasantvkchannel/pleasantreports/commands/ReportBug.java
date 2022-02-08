package mc.pleasantvkchannel.pleasantreports.commands;

import mc.pleasantvkchannel.pleasantreports.Main;
import mc.pleasantvkchannel.pleasantreports.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportBug implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.color(Utils.getString("Messages.ConsoleDeny")));
            return false;
        }

        if (!sender.hasPermission(Utils.getString("Settings.PermissionReportBug"))) {
            sender.sendMessage(Utils.color(Utils.getString("Messages.NoPerm")));
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(Utils.color(Utils.getString("Messages.ReportBugCommand.UseCommand")));
            return true;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < args.length; ) {
            builder.append(args[i]).append(" ");
            i++;
        }

        Player player = (Player) sender;

        String message = builder.toString();
        sender.sendMessage(Utils.color(Utils.getString("Messages.ReportBugCommand.ReportBugSend")));

        if (Utils.getBoolean(Boolean.valueOf("Sounds.Enable"))) {
            player.playSound(player.getLocation(), Sound.valueOf(Utils.getString("Sound.SoundSend")), 1, 1);
        }

        for (Player administrator : Bukkit.getOnlinePlayers()) {
            if (administrator.hasPermission(Utils.getString("Settings.AdministratorSend"))) {
                for (String messageAdmin : Main.getInstance().getConfig().getStringList("Messages.ReportBugCommand.BroadcastList")) {
                    messageAdmin = messageAdmin.replace("%message%", message).replace("%reporter%", sender.getName());
                    administrator.sendMessage(Utils.color(messageAdmin));
                }
            }
        }
        return false;
    }
}