package help.me;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getnumCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Me.getInstance().getConfig().get("Numbers." + 0) != null) {
                p.sendMessage((String) Me.getInstance().getConfig().get("Numbers." + 0));
                for (int i = 1; i < Me.getInstance().getConfig().getInt("Numbers.counter") - 1; i++) {
                    int newPlace = i + 1;
                    if (Me.getInstance().getConfig().get("Numbers." + newPlace) != null) {
                        Me.getInstance().getConfig().set("Numbers." + i, Me.getInstance().getConfig().get("Numbers." + newPlace));
                    } else Me.getInstance().getConfig().set("Numbers." + i, null);
                }
                Me.getInstance().getConfig().set("Numbers." + Me.getInstance().getConfig().get("Numbers.counter"), null);
                if (Me.getInstance().getConfig().getInt("Numbers.counter") != 0) {
                    Me.getInstance().getConfig().set("Numbers.counter", Me.getInstance().getConfig().getInt("Numbers.counter") - 1);
                }
            }
        }
        Me.getInstance().saveConfig();
        return true;
    }
}
