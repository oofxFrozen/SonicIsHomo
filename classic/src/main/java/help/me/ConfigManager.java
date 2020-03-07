package help.me;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    // cfg and file
    public static FileConfiguration locCfg;
    public static File locs;
//    public static FileConfiguration helloCfg;
//    public static File hello;
    //-------------------

    public void setup() {
        locs = new File(Me.getInstance().getDataFolder(), "loc.yml");

        if(!locs.exists()) {
            try {
                boolean tryingToCreate = locs.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create the locs.yml file.");
            }
        }

        locCfg = YamlConfiguration.loadConfiguration(locs);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The loc.yml file has been opened.");


        //---------------------------

//        hello = new File(Me.getInstance().getDataFolder(), "hello.yml");
//
//        if(!hello.exists()) {
//            try {
//                boolean tryingToCreate = hello.createNewFile();
//            } catch (IOException e) {
//                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create the hello.yml file.");
//            }
//        }
//
//        helloCfg = YamlConfiguration.loadConfiguration(hello);
//        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The hello.yml file has been opened.");

    }

}
