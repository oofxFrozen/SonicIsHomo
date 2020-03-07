package help.me;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.EulerAngle;

import java.util.List;

public class killEffects implements Listener {

    BukkitTask taskID;

    @EventHandler
    public void keChoose(InventoryClickEvent event) {
        if (event.getInventory().getName().equals(ChatColor.AQUA + "Kill Effects")) {
            event.setCancelled(true);
            if (event.getCurrentItem().equals(new ItemStack(Material.BARRIER))) {
                Me.getInstance().getConfig().set("Users." + event.getWhoClicked().getUniqueId() + ".killEffect", "NONE");
                Me.getInstance().getConfig().set("Users." + event.getWhoClicked().getUniqueId() + ".lobbyEffect", "NONE");
                Me.getInstance().saveConfig();
            }
            if (event.getCurrentItem().equals(new ItemStack(Material.SPECKLED_MELON))) {
                Me.getInstance().getConfig().set("Users." + event.getWhoClicked().getUniqueId() + ".killEffect", "RAINBOW");
                Me.getInstance().saveConfig();
            }
            if (event.getCurrentItem().equals(new ItemStack(Material.BONE))) {
                Me.getInstance().getConfig().set("Users." + event.getWhoClicked().getUniqueId() + ".killEffect", "WHITE");
                Me.getInstance().saveConfig();
            }
            if (event.getCurrentItem().equals(customInventory.BONE_MEAL)) {
                Me.getInstance().getConfig().set("Users." + event.getWhoClicked().getUniqueId() + ".killEffect", "WHITE_EXPLOSION");
                Me.getInstance().saveConfig();
            }
            if (event.getCurrentItem().equals(new ItemStack(Material.SLIME_BALL))) {
                Me.getInstance().getConfig().set("Users." + event.getWhoClicked().getUniqueId() + ".killEffect", "SLIME");
                Me.getInstance().saveConfig();
            }
            if (event.getCurrentItem().equals(new ItemStack(Material.BLAZE_ROD))) {
                Me.getInstance().getConfig().set("Users." + event.getWhoClicked().getUniqueId() + ".killEffect", "FIRE");
                Me.getInstance().saveConfig();
            }
            if (event.getCurrentItem().equals(new ItemStack(Material.BLAZE_POWDER))) {
                Me.getInstance().getConfig().set("Users." + event.getWhoClicked().getUniqueId() + ".killEffect", "FIRE_EXPLOSION");
                Me.getInstance().saveConfig();
            }
            if (event.getCurrentItem().equals(new ItemStack(Material.SKULL_ITEM))) {
                Me.getInstance().getConfig().set("Users." + event.getWhoClicked().getUniqueId() + ".killEffect", "FINAL_DEATH");
                Me.getInstance().saveConfig();
            }
            if (event.getCurrentItem().equals(new ItemStack(Material.CACTUS))) {
                Me.getInstance().getConfig().set("Users." + event.getWhoClicked().getUniqueId() + ".killEffect", "REKT");
                Me.getInstance().saveConfig();
            }
            if (event.getCurrentItem().equals(new ItemStack(Material.SNOW_BALL))) {
                Me.getInstance().getConfig().set("Users." + event.getWhoClicked().getUniqueId() + ".killEffect", "SNOW");
                Me.getInstance().saveConfig();
            }
            if (event.getCurrentItem().equals(new ItemStack(Material.REDSTONE))) {
                Me.getInstance().getConfig().set("Users." + event.getWhoClicked().getUniqueId() + ".lobbyEffect", "HEART_TRAIL");
                Me.getInstance().saveConfig();
                this.taskID = Bukkit.getScheduler().runTaskTimerAsynchronously(Me.getInstance(), () -> {
                    Location l = event.getWhoClicked().getLocation().clone().add(0,2,0);
                    l.getWorld().spawnParticle(Particle.HEART, l, 0);
                    if (!Me.getInstance().getConfig().get("Users." + event.getWhoClicked().getUniqueId() + ".lobbyEffect").equals("HEART_TRAIL")) {
                        taskID.cancel();
                    }
                }, 0L, 2L);
            }
        }
    }

    @EventHandler
    public void KillEffect(EntityDeathEvent event) {
        if (Me.getInstance().getConfig().get("Users." + event.getEntity().getKiller().getUniqueId() + ".killEffect").equals("RAINBOW")) {
            Location loc = event.getEntity().getLocation();

            int R;
            int G;
            int B;

            for (int i = 0; i < 25; i++) {

                double randomX = Math.random() * 1.5 - 0.75;
                double randomZ = Math.random() * 1.5 - 0.75;
                double randomY = Math.random() * 2;

                int randomR = (int) (Math.random() + 1);
                int randomG = (int) (Math.random() + 1);
                int randomB = (int) (Math.random() + 1);

                if (randomR == 1) R = 255;
                else R = 0;
                if (randomG == 1) G = 255;
                else G = 0;
                if (randomB == 1) B = 255;
                else B = 0;

                loc.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + randomX, loc.getY() + randomY, loc.getZ() + randomZ, 0, R, G, B, 1);
                loc.getWorld().spawnParticle(Particle.REDSTONE, loc.getX() + randomX, loc.getY() + randomY, loc.getZ() + randomZ, 0, R, G, B, 1);
            }


        }
        if (Me.getInstance().getConfig().get("Users." + event.getEntity().getKiller().getUniqueId() + ".killEffect").equals("WHITE")) {
            Location l = event.getEntity().getLocation();
            for (int i = 0; i < 25; i++) {
                double X = Math.random() * 1.5 - 0.75;
                double Z = Math.random() * 1.5 - 0.75;
                double Y = Math.random() * 2;
                l.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, l.getX() + X, l.getY() + Y, l.getZ() + Z, 0);
            }
            l.getWorld().playSound(l, Sound.BLOCK_WOOD_HIT, (float) 0.9, (float) 0.8);
        }
        if (Me.getInstance().getConfig().get("Users." + event.getEntity().getKiller().getUniqueId() + ".killEffect").equals("WHITE_EXPLOSION")) {
            Location l = event.getEntity().getLocation();
            for (int i = 0; i < 30; i++) {
                double X = Math.random() * 1.5 - 0.75;
                double Z = Math.random() * 1.5 - 0.75;
                double Y = Math.random() * 2;
                l.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, l.getX() + X, l.getY() + Y, l.getZ() + Z, 1);
            }
            l.getWorld().playSound(l, Sound.ENTITY_GHAST_SHOOT, (float) 0.2, (float) 1);
        }
        if (Me.getInstance().getConfig().get("Users." + event.getEntity().getKiller().getUniqueId() + ".killEffect").equals("SLIME")) {
            Location l = event.getEntity().getLocation();
            l.getWorld().playSound(l, Sound.ENTITY_SLIME_DEATH, (float) 1.5, (float) 0.8);
            for (int i = 0; i < 25; i++) {
                double X = Math.random() * 1.5 - 0.75;
                double Z = Math.random() * 1.5 - 0.75;
                double Y = Math.random() * 2;
                l.getWorld().spawnParticle(Particle.SLIME, l.getX() + X, l.getY() + Y, l.getZ() + Z, 0);
            }
        }
        if (Me.getInstance().getConfig().get("Users." + event.getEntity().getKiller().getUniqueId() + ".killEffect").equals("FIRE")) {
            Location l = event.getEntity().getLocation();
            l.getWorld().playSound(l, Sound.BLOCK_FIRE_AMBIENT, (float) 0.6, (float) 1.5);
            for (int i = 0; i < 25; i++) {
                double X = Math.random() * 1.5 - 0.75;
                double Z = Math.random() * 1.5 - 0.75;
                double Y = Math.random() * 2;
                l.getWorld().spawnParticle(Particle.FLAME, l.getX() + X, l.getY() + Y, l.getZ() + Z, 0);
            }
        }
        if (Me.getInstance().getConfig().get("Users." + event.getEntity().getKiller().getUniqueId() + ".killEffect").equals("FIRE_EXPLOSION")) {
            Location l = event.getEntity().getLocation();
            l.getWorld().playSound(l, Sound.BLOCK_FIRE_AMBIENT, (float) 0.6, (float) 1.5);
            for (int i = 0; i < 25; i++) {
                double X = Math.random() * 1.5 - 0.75;
                double Z = Math.random() * 1.5 - 0.75;
                double Y = Math.random() * 2;
                l.getWorld().spawnParticle(Particle.FLAME, l.getX() + X, l.getY() + Y, l.getZ() + Z, 2);
            }
        }
        if (Me.getInstance().getConfig().get("Users." + event.getEntity().getKiller().getUniqueId() + ".killEffect").equals("REKT")) {
            Location l = event.getEntity().getLocation();
            ArmorStand rekted = (ArmorStand) l.getWorld().spawnEntity(l.clone().add(0, 1, 0), EntityType.ARMOR_STAND);
            rekted.setVisible(false);
            rekted.setMarker(true);
            rekted.setCollidable(false);
            rekted.setGravity(false);
            rekted.setCustomNameVisible(true);
            rekted.setInvulnerable(true);
            l.getWorld().playSound(l, Sound.ENTITY_CAT_AMBIENT, 0.1f, 1f);
            rekted.setCustomName(ChatColor.RED + "#" + ChatColor.GOLD + "R" + ChatColor.YELLOW + "E" + ChatColor.GREEN + "K" + ChatColor.BLUE + "T");
            Bukkit.getScheduler().runTaskLater(Me.getInstance(), () -> {
                rekted.setInvulnerable(false);
                rekted.remove();
            }, 20 * 2);
        }
        if (Me.getInstance().getConfig().get("Users." + event.getEntity().getKiller().getUniqueId() + ".killEffect").equals("SNOW")) {
            Location l = event.getEntity().getLocation();
            l.getWorld().playSound(l,Sound.BLOCK_SNOW_STEP, 1f, 1.3f);
            for (int i = 0; i < 25; i++) {
                double X = Math.random() * 1.5 - 0.75;
                double Z = Math.random() * 1.5 - 0.75;
                double Y = Math.random() * 2;
                l.getWorld().spawnParticle(Particle.SNOWBALL, l.getX() + X, l.getY() + Y, l.getZ() + Z, 0);
            }
        }




        if (Me.getInstance().getConfig().get("Users." + event.getEntity().getKiller().getUniqueId() + ".killEffect").equals("FINAL_DEATH")) {
            Location l = event.getEntity().getLocation();
            String color;
            String team = "";
            if (!event.getEntity().getMetadata("team").isEmpty()) {
                List<MetadataValue> values = event.getEntity().getMetadata("team");
                team = values.get(0).asString();
            }
            ItemStack skull = new ItemStack(Material.SKULL_ITEM);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
            switch (team) {
                case ("RED"):
                    meta.setColor(Color.RED);
                    color = "" + ChatColor.RED;
                    break;
                case ("GREEN"):
                    meta.setColor(Color.GREEN);
                    color = "" + ChatColor.GREEN;
                    break;
                case ("BLUE"):
                    meta.setColor(Color.BLUE);
                    color = "" + ChatColor.BLUE;
                    break;
                case ("YELLOW"):
                    meta.setColor(Color.YELLOW);
                    color = "" + ChatColor.YELLOW;
                    break;
                default:
                    meta.setColor(Color.WHITE);
                    color = "" + ChatColor.WHITE;
                    break;
            }
            chestplate.setItemMeta(meta);
            ArmorStand dead = (ArmorStand) l.getWorld().spawnEntity(l.clone().add(0, -1, 0), EntityType.ARMOR_STAND);
            dead.setChestplate(chestplate);
            dead.setHelmet(skull);
            dead.setInvulnerable(true);
            dead.setVisible(false);
            dead.setMarker(true);
            dead.setCollidable(false);
            dead.setGravity(false);
            dead.setBodyPose(new EulerAngle(Math.toRadians(270), Math.toRadians(0), Math.toRadians(0)));
            dead.setHeadPose(new EulerAngle(Math.toRadians(287), Math.toRadians(7), Math.toRadians(17)));
            ArmorStand deadName = (ArmorStand) l.getWorld().spawnEntity(l.clone().add(0, 1, 0), EntityType.ARMOR_STAND);
            deadName.setInvulnerable(true);
            deadName.setVisible(false);
            deadName.setMarker(true);
            deadName.setCollidable(false);
            deadName.setGravity(false);
            deadName.setCustomNameVisible(true);
            deadName.setCustomName(color + event.getEntity().getName());
        }

    }
}






//    public String print(int n) {
//        if (n > 0 && n % 2 != 0) {
//            StringBuilder builder = new StringBuilder();
//            for (int i = 0; i < n; i++) {
//                for (int j = n-i; j > 1; j--) {
//                    builder.append(" ");
//                }
//                for (int k = i - n) {
//
//                }
//                builder.append("*");
//            }
//        } else return null;
