import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInputEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Particle;
import org.bukkit.Color;

public class S2Brake extends JavaPlugin implements Listener {
    private double speed;
    @Override
    public void onEnable() {
        speed = getConfig().getDouble("brake.speed"); // get brake speed from config
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler
    public void onPlayerInput (PlayerInputEvent event) {
        Player player = event.getPlayer();
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(255, 48, 28), 2.0f);
        // Check if server has brakes on, then if player is in a minecart
        if (getConfig().getBoolean("brake.enabled")) {
        if (player.getVehicle() instanceof Minecart minecart) {
            while (event.getInput().isBackward()) {
                minecart.setVelocity(minecart.getVelocity().multiply(speed));
                if (getConfig().getBoolean("particles.enabled")) {
                    player.spawnParticle(Particle.DUST, player.getLocation(), 1, 0.5, 1, 0.5, 0.01, dustOptions);
                }
            }
        }
    }
}
}
