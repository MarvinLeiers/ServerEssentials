package de.marvinleiers.serveressentials.commands;

import de.marvinleiers.mplugin.commands.Command;
import de.marvinleiers.serveressentials.manage.TPARequest;
import de.marvinleiers.serveressentials.manage.TPARequestsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPACommand extends Command
{
    private final TPARequestsManager tpaRequestsManager;

    public TPACommand()
    {
        super("tpa", "mplugin.tpa", false);

        this.tpaRequestsManager = TPARequestsManager.getInstance();
    }

    @Override
    public void onPlayerExecute(Player player, String[] args)
    {
        if (args.length != 1)
        {
            player.sendMessage("§cUsage: /tpa <player|accept|deny>");
            return;
        }

        if (args[0].equalsIgnoreCase("accept"))
        {
            if (!tpaRequestsManager.accept(player))
                player.sendMessage("§cYou don't have any requests! :(");

            tpaRequestsManager.accept(player);
            return;
        }
        else if (args[0].equalsIgnoreCase("deny"))
        {
            if (!tpaRequestsManager.hasRequest(player))
            {
                player.sendMessage("§cYou don't have any requests! :(");
                return;
            }

            if (!tpaRequestsManager.deny(player))
                player.sendMessage("§cYou don't have any requests! :(");
            else
                player.sendMessage("§6Teleportation request denied!");

            return;
        }

        Player target;

        if ((target = Bukkit.getPlayer(args[0])) == null)
        {
            player.sendMessage("§e" + args[0] + " §cis not online!");
            return;
        }

        player.sendMessage("§aRequest has been sent!");
        target.sendMessage("§6You received a teleportation request by " + player.getName() + "\nType /tpa accept to accept");

        tpaRequestsManager.addRequest(new TPARequest(player, target));
    }

    @Override
    public void onExecute(CommandSender commandSender, String[] strings)
    {

    }
}
