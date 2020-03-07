package help.me;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class godCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            events.check = !events.check;
            events.sender = (Player) sender;
            if (events.check) {
                sender.sendMessage("Вы бессмертны.");
            } else sender.sendMessage("Теперь вы смертны.");
        }
        return true;
    }

}
