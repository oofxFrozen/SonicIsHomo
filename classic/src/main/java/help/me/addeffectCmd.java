package help.me;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class addeffectCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        int modifier = -1;
            if (args.length == 3) {
                if (!checkString(args[2])) {
                    modifier = Integer.parseInt(args[2]);
                    if (modifier < 0) modifier = 0;
                }
            }
        Player p = (Player) sender;
        if (args.length < 2 || args.length > 3) {
            p.sendMessage("Something went wrong. Try /addeffect <type> <duration> <modifier>");
            return true;
        }
        if (checkString(args[1])) {
            p.sendMessage("Duration must be a number!");
            return true;
        }
        if (args.length == 3) {
            if (checkString(args[2])) {
                p.sendMessage("Modifier must be a number!");
                return true;
            }
        }
        String potion;
        int duration = Integer.parseInt(args[1]) * 20;
        if (duration < 0) duration = 0;
        if (modifier < 0) modifier = 0;
        switch (args[0].toUpperCase()) {
            case ("STRENGTH"):
                potion = "INCREASE_DAMAGE";
                break;
            case ("NAUSEA"):
                potion = "CONFUSION";
                break;
            case ("JUMP_BOOST"):
                potion = "JUMP";
                break;
            default:
                potion = args[0].toUpperCase();
                break;
        }
        if (PotionEffectType.getByName(potion) == null) {
            p.sendMessage("Wrong potion effect type!");
            return true;
        }
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            for (PotionEffect pot : player.getActivePotionEffects())
            player.removePotionEffect(pot.getType());
        }
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(potion.toUpperCase()), duration, modifier));
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

// Objects.requireNonNull(Arrays.stream(PotionEffectType.values()).filter(et -> et.getName().equalsIgnoreCase(potion)).findAny().orElse(PotionEffectType.LUCK)))