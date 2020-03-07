package help.me;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class duplicatesCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage("Lol gimme numbers instead of void!");
                return true;
            }
            if (args.length == 1) {
                p.sendMessage("There are no duplicates!");
                return true;
            }
            String[] dublicates = findDublicates(args);
            if (dublicates[0] != null) {
                StringBuilder total = new StringBuilder();
                for (String dublicate : dublicates) {
                    total.append(dublicate).append(" ");
                }
                p.sendMessage("Duplicates: " + total);
            } else p.sendMessage("There are no duplicates!");
        }
        return true;
    }

    private String[] findDublicates(String[] input) {
        Set<String> set = new HashSet<>();
        Set<String> dublicates = new HashSet<>();
        for (String a : input) {
            if (!set.add(a)) {
                dublicates.add(a);
            }
        }
        return dublicates.toArray(new String[0]);
    }
}
