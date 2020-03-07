package help.me;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class flyCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player sndr = (Player) sender;
            if (args.length == 0) {
                Player p = (Player) sender;
                if (!p.getAllowFlight()) {
                    p.setAllowFlight(true);
                } else {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                }
            } else {
                Player p = Bukkit.getServer().getPlayer(args[0]);
                if (p.isOnline()) {
                    if (!p.getAllowFlight()) {
                        p.setAllowFlight(true);
                    } else {
                        p.setAllowFlight(false);
                        p.setFlying(false);
                    }
                } else sndr.sendMessage("Player is offline!");
            }
        }
        return true;
    }
}
