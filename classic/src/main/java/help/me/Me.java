package help.me;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Me extends JavaPlugin {

    public static Me instance;
    public static Me getInstance() {
        return instance;
    }

    events events = new events();
    killEffects killEffects = new killEffects();

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(events, this);
        this.getServer().getPluginManager().registerEvents(killEffects, this);
        getLogger().info("PLEASE WORK");
        instance = this;

        getCommand("btp").setExecutor(new btpCmd());
        getCommand("sethealth").setExecutor(new sethealthCmd());
        getCommand("showtext").setExecutor(new showtextCmd());
        getCommand("god").setExecutor(new godCmd());
        getCommand("ke").setExecutor(new keCmd());
        getCommand("fly").setExecutor(new flyCmd());
        getCommand("addeffect").setExecutor(new addeffectCmd());
        getCommand("getmin").setExecutor(new getminCmd());
        getCommand("duplicates").setExecutor(new duplicatesCmd());
        getCommand("addnum").setExecutor(new addnumCmd());
        getCommand("getnum").setExecutor(new getnumCmd());

        loadConfig();
        try {
            loadCustomConfigs();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        getLogger().info("something went wrong :/");
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        if (getConfig().get("Numbers.counter") == null) {
            getConfig().set("Numbers.counter", 1);
        }
        saveConfig();
    }
    public void loadCustomConfigs() throws IOException {
        ConfigManager cfg = new ConfigManager();
        cfg.setup();
        ConfigManager.locCfg.options().copyDefaults(true);
        ConfigManager.locCfg.save(ConfigManager.locs);
//        ConfigManager.helloCfg.options().copyDefaults(true);
//        ConfigManager.helloCfg.save(ConfigManager.hello);
//        if (ConfigManager.helloCfg.get("Hello.") == null) {
//            ConfigManager.helloCfg.set("Hello.", "&k|&cH&6e&el&al&bo&9!&fДобро пожаловать на тестовый сервер!&k|");
//        }
//        ConfigManager.helloCfg.save(ConfigManager.hello);
    }

}
