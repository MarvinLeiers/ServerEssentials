package de.marvinleiers.serveressentials.listeners;

import de.marvinleiers.serveressentials.ServerEssentials;
import de.marvinleiers.serveressentials.manage.VanishHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        if (!player.hasPermission("mplugin.vanish"))
        {
            for (Player vanished : VanishHandler.getVanished())
            {
                player.hidePlayer(ServerEssentials.getMPlugin(), vanished);
            }
        }
    }
}
