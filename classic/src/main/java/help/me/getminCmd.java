package help.me;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getminCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            int min = 0;
            if (args.length == 0) {
                p.sendMessage("Lol gimme some numbers instead of void :/");
                return true;
            }
            for (int i = 0; i < args.length; i++) {
                if (checkString(args[i])) {
                    p.sendMessage("Srsly? Numbers must be numbers.");
                    return true;
                }
                if (Long.parseLong(args[i]) < Long.parseLong(args[min])) {
                    min = i;
                }
            }
            p.sendMessage("The minimal number is: " + args[min]);
        }
        return true;
    }

    public boolean checkString(String string) {
        try {
            Long.parseLong(string);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }
}
