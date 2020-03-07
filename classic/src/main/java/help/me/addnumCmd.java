package help.me;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class addnumCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length != 1) {
                p.sendMessage("Error! Try /addnum <number>");
                return true;
            }
            Me.getInstance().getConfig().set("Numbers." + Me.getInstance().getConfig().get("Numbers.counter"), args[0]);
            Me.getInstance().getConfig().set("Numbers.counter", Me.getInstance().getConfig().getInt("Numbers.counter") + 1);
            Me.getInstance().saveConfig();
        }
        return true;
    }
}
