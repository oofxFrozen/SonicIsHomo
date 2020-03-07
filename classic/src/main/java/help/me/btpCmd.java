package help.me;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class btpCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length != 1) {
                p.sendMessage("Something went wrong. Try /btp diamond|gold");
            }
            if (args.length == 1) {
                switch (args[0]) {
                    case ("diamond"):
                        if (ConfigManager.locCfg.get("Teleport.diamond.location") == null) p.sendMessage("First you should place your diamond block!");
                        else {
                            Location loc = (Location) ConfigManager.locCfg.get("Teleport.diamond.location");
                            p.teleport(loc.clone().add(0.5, 1, 0.5));
                        }
                        break;
                    case ("gold"):
                        if (ConfigManager.locCfg.get("Teleport.gold.location") == null) p.sendMessage("First you should place your gold block!");
                        else {
                            Location loc = (Location) ConfigManager.locCfg.get("Teleport.gold.location");

                            p.teleport(loc.clone().add(0.5, 1, 0.5));
                        }
                        break;
                    default:
                        p.sendMessage("Something went wrong. Try /btp diamond|gold");
                }
            }
        }
        return true;
    }
}
