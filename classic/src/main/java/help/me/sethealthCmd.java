package help.me;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class sethealthCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length != 1) {
                p.sendMessage("Error! Try /sethealth <amount>");
            } else if (checkString(args[0])) {
                p.sendMessage("The amount of health must be a number!");
            } else if (Integer.parseInt(args[0]) > p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() || Integer.parseInt(args[0]) <= 0) {
                p.sendMessage("The amount of health can not be greater than the maximum or less than 1!");
            } else {
                p.setHealth(Double.parseDouble(args[0]));
            }
        }
        return true;
    }

    public boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }
}
