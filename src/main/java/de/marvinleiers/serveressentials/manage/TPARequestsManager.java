package de.marvinleiers.serveressentials.manage;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TPARequestsManager
{
    private static HashMap<Player, TPARequest> requests = new HashMap<Player, TPARequest>();
    private static TPARequestsManager instance;

    private TPARequestsManager()
    {
    }

    public void addRequest(TPARequest request)
    {
        requests.put(request.getFrom(), request);
    }

    public boolean hasRequest(Player player)
    {
        for (TPARequest request : requests.values())
        {
            if (request.getTo().getUniqueId().toString().equals(player.getUniqueId().toString()))
                return true;
        }

        return false;
    }

    private Player getTPRequestPlayer(Player player)
    {
        if (!hasRequest(player)) return null;

        for (Map.Entry<Player, TPARequest> entry : requests.entrySet())
        {
            if (entry.getValue().getTo().getUniqueId().toString().equals(player.getUniqueId().toString()))
            {
                return entry.getKey();
            }
        }

        return null;
    }

    public boolean deny(Player player)
    {
        if (!hasRequest(player)) return false;

        for (TPARequest request : requests.values())
        {
            if (request.getTo().getUniqueId().toString().equals(player.getUniqueId().toString()))
            {
                requests.remove(request.getFrom());
                return true;
            }
        }

        return false;
    }

    public boolean accept(Player player)
    {
        if (!hasRequest(player))
            return false;

        Player from = getTPRequestPlayer(player);

        from.teleport(player);

        requests.remove(from);
        return true;
    }

    public static TPARequestsManager getInstance()
    {
        if (instance == null)
            instance = new TPARequestsManager();

        return instance;
    }
}
