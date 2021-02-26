package de.marvinleiers.serveressentials.commands;

import de.marvinleiers.mplugin.commands.Command;
import de.marvinleiers.serveressentials.manage.VanishHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand extends Command
{
    public VanishCommand()
    {
        super("vanish", "mplugin.vanish", false);
    }

    @Override
    public void onPlayerExecute(Player player, String[] args)
    {
        VanishHandler vanishHandler = VanishHandler.getInstance();

        if (args.length == 0)
        {
            vanishHandler.togglePlayer(player);
            return;
        }

        Player target;

        if ((target = Bukkit.getPlayer(args[0])) == null)
        {
            player.sendMessage("§e" + args[0] + " §cis not online!");
            return;
        }

        vanishHandler.togglePlayer(target);
        player.sendMessage("§7Toggled vanish for §a" + target.getName());
    }

    @Override
    public void onExecute(CommandSender commandSender, String[] strings)
    {

    }
}
