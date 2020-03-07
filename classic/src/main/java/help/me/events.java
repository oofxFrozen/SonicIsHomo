package help.me;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.IOException;
import java.util.Collections;


public class events implements Listener {

    public ItemStack dBlock;
    public ItemStack gBlock;

    private boolean logCheck = true;

    public static String title;
    public static String subtitle;
    public static boolean check = false;
    public static Player sender;

    @EventHandler
    public void JoinEvent (PlayerJoinEvent event) {
        setStepsDelayNull(event.getPlayer());
        if (logCheck) {
            event.getPlayer().sendMessage(ChatColor.BOLD + ChatColor.translateAlternateColorCodes('&', "&k| &cH&6e&el&al&bo&9!  &fДобро пожаловать на тестовый сервер! &k|"));
            logCheck = false;
            Bukkit.getScheduler().runTaskLater(Me.getInstance(), () -> logCheck = true, 20*60);
        }
        ItemStack diamond = new ItemStack(Material.DIAMOND_BLOCK);
        ItemStack gold = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = diamond.getItemMeta();
        meta.setLore(Collections.singletonList(ChatColor.AQUA + "Ставить главной рукой! Иначе связь с телепортом не будет установлена."));
        diamond.setItemMeta(meta);
        meta.setLore(Collections.singletonList(ChatColor.GOLD + "Ставить главной рукой! Иначе связь с телепортом не будет установлена."));
        gold.setItemMeta(meta);
        dBlock = diamond;
        gBlock = gold;
        if (event.getPlayer().getInventory().contains(dBlock)) {
            event.getPlayer().getInventory().removeItem(dBlock);
            event.getPlayer().getInventory().addItem(dBlock);
        } else event.getPlayer().getInventory().addItem(dBlock);
        if (event.getPlayer().getInventory().contains(gBlock)) {
            event.getPlayer().getInventory().removeItem(gBlock);
            event.getPlayer().getInventory().addItem(gBlock);
        } else event.getPlayer().getInventory().removeItem(gBlock);
    }


    @EventHandler
    public void PortalLoc (BlockPlaceEvent event) throws IOException {
        if (event.getPlayer().getInventory().getItemInMainHand().equals(dBlock)) {
            event.getBlock().setMetadata("teleport", new FixedMetadataValue(Me.getInstance(), "teleport!"));
            event.getBlock().getWorld().getBlockAt(ConfigManager.locCfg.getInt("Teleport.diamond.X"), ConfigManager.locCfg.getInt("Teleport.diamond.Y"), ConfigManager.locCfg.getInt("Teleport.diamond.Z")).setType(Material.AIR);
            ConfigManager.locCfg.set("Teleport.diamond.X", event.getBlock().getLocation().getX());
            ConfigManager.locCfg.set("Teleport.diamond.Y", event.getBlock().getLocation().getY());
            ConfigManager.locCfg.set("Teleport.diamond.Z", event.getBlock().getLocation().getZ());
            ConfigManager.locCfg.set("Teleport.diamond.location", event.getBlock().getLocation());
            ConfigManager.locCfg.save(ConfigManager.locs);
            if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                event.getPlayer().getInventory().addItem(dBlock);
            }
        }
        if (event.getPlayer().getInventory().getItemInMainHand().equals(gBlock)) {
            event.getBlock().setMetadata("teleport", new FixedMetadataValue(Me.getInstance(), "teleport!"));
            event.getBlock().getWorld().getBlockAt(ConfigManager.locCfg.getInt("Teleport.gold.X"), ConfigManager.locCfg.getInt("Teleport.gold.Y"), ConfigManager.locCfg.getInt("Teleport.gold.Z")).setType(Material.AIR);
            ConfigManager.locCfg.set("Teleport.gold.X", event.getBlock().getLocation().getX());
            ConfigManager.locCfg.set("Teleport.gold.Y", event.getBlock().getLocation().getY());
            ConfigManager.locCfg.set("Teleport.gold.Z", event.getBlock().getLocation().getZ());
            ConfigManager.locCfg.set("Teleport.gold.location", event.getBlock().getLocation());
            ConfigManager.locCfg.save(ConfigManager.locs);
            if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                event.getPlayer().getInventory().addItem(gBlock);
            }
        }
    }



    @EventHandler
    public void GodEntityDamage (EntityDamageByEntityEvent event) {
        if (event.getEntity() == sender && check) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void GodFireDamage (EntityDamageEvent event) {
        if ((event.getCause() == EntityDamageEvent.DamageCause.FIRE || event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK || event.getCause() == EntityDamageEvent.DamageCause.LAVA) && event.getEntity() == sender && check) {
            event.setCancelled(true);
            event.getEntity().setFireTicks(0);
        }
    }

    public void setStepsDelayNull(Player p) {
        Me.getInstance().getConfig().set("Users." + p.getUniqueId() + ".footStepsDelayCheck", null);
    }
    public void setStepsDelay(Player p) {
        Me.getInstance().getConfig().set("Users." + p.getUniqueId() + ".footStepsDelayCheck", !Me.getInstance().getConfig().getBoolean("Users." + p.getUniqueId() + ".footStepsDelayCheck"));
    }
    public boolean getStepsDelay(Player p) {
        if (Me.getInstance().getConfig().get("Users." + p.getUniqueId() + ".footStepsDelayCheck") == null) {
            Me.getInstance().getConfig().set("Users." + p.getUniqueId() + ".footStepsDelayCheck", true);
        }
        return Me.getInstance().getConfig().getBoolean("Users." + p.getUniqueId() + ".footStepsDelayCheck");
    }

    @EventHandler
    public void FootSteps (PlayerMoveEvent e) {
        if (e.getPlayer().isOnGround()) {
            if (getStepsDelay(e.getPlayer())) {
                Location l = e.getPlayer().getLocation();
                l.getWorld().spawnParticle(Particle.FOOTSTEP, l, 0);
                setStepsDelay(e.getPlayer());
                Bukkit.getScheduler().runTaskLater(Me.getInstance(), () -> {
                    setStepsDelay(e.getPlayer());
                }, 4);
            }
        }
    }

}
