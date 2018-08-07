package rush.recursos.bloqueadores;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class BloquearNameTag implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onInteract(PlayerInteractEntityEvent e) {
		if (e.getPlayer().getItemInHand().getType() == Material.NAME_TAG) {
			if (!(e.getPlayer().hasPermission("system.bypass.usarnametag"))) {
				e.setCancelled(true);
			}
		}
	}
}
