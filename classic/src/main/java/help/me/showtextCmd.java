package help.me;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class showtextCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length > 0 && args.length < 3) {
                switch (args.length) {
                    case (1):
                        events.title = ChatColor.translateAlternateColorCodes('&', args[0]);
                        p.sendTitle("" + events.title, "", 5, 20*2, 5);
                        break;
                    case (2):
                        events.title = ChatColor.translateAlternateColorCodes('&', args[0]);
                        events.subtitle = ChatColor.translateAlternateColorCodes('&', args[1]);
                        p.sendTitle(events.title, events.subtitle, 5, 20*2, 5);
                        break;
                }
            } else p.sendMessage("Error! Try /showtext <title> <subtitle>");
        }
        return true;
    }
}
