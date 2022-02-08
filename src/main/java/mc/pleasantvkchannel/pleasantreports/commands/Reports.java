package mc.pleasantvkchannel.pleasantreports.commands;

import mc.pleasantvkchannel.pleasantreports.Main;
import mc.pleasantvkchannel.pleasantreports.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reports implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.color(Utils.getString("Messages.ConsoleDeny")));
            return false;
        }

        Player player = (Player) sender;

        if (!sender.hasPermission(Utils.getString("Settings.PermissionReport"))) {
            sender.sendMessage(Utils.color(Utils.getString("Messages.NoPerm")));
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(Utils.color(Utils.getString("Messages.ReportCommand.UseCommand")));
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(Utils.color(Utils.getString("Messages.ReportCommand.EnterReason")));
            return true;
        }

        if (sender == Bukkit.getPlayer(args[0])) {
            player.sendMessage(Utils.color(Utils.getString("Messages.ReportCommand.ReportMyself")));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(Utils.color(Utils.getString("Messages.ReportCommand.PlayerNotFound")));
            return true;
        }


        StringBuilder reason = new StringBuilder();
        for (int i = 1; i < args.length; i++)
            reason.append(args[i]).append(" ");

        player.sendMessage(Utils.color(Utils.getString("Messages.ReportCommand.ReportSend")).replace("%player%", target.getName()));

        if (Utils.getBoolean(Boolean.valueOf("Sounds.Enable"))) {
            player.playSound(player.getLocation(), Sound.valueOf(Utils.getString("Sound.SoundSend")), 1, 1);
        }

        if (Utils.getBoolean(Boolean.valueOf("Settings.TargetAnnounce"))) {
            target.sendMessage(Utils.color(Utils.getString("Messages.ReportCommand.TargetAnnounce")).replace("%player%", sender.getName()));
        }

        for (Player moderator : Bukkit.getOnlinePlayers()) {
            if (moderator.hasPermission(Utils.getString("Settings.ModeratorSend"))) {
                for (String message : Main.getInstance().getConfig().getStringList("Messages.ReportCommand.BroadcastList")) {
                    message = message.replace("%reporter%", sender.getName().replace("%player%", target.getName().replace("%reason%", reason)));
                    moderator.sendMessage(Utils.color(message));
                }
            }
        }
        return false;
    }
}