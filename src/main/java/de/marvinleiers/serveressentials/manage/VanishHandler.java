package de.marvinleiers.serveressentials.manage;

import de.marvinleiers.serveressentials.ServerEssentials;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class VanishHandler
{
    private static final ArrayList<Player> vanished = new ArrayList<>();
    private static VanishHandler instance;
    private Scoreboard scoreboard;
    private Team vanishedTeam;

    private VanishHandler()
    {
        this.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        vanishedTeam = scoreboard.getTeam("vanished") == null ? scoreboard.registerNewTeam("vanished")
                : scoreboard.getTeam("vanished");

        vanishedTeam.setCanSeeFriendlyInvisibles(true);
    }

    public void addToTeam(Player player)
    {
        vanishedTeam.addEntry(player.getName());
        player.setScoreboard(scoreboard);
    }

    public void togglePlayer(Player player)
    {
        if (vanished.contains(player))
            showPlayer(player);
        else
            hidePlayer(player);

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                if (player.hasPermission("mplugin.vanish"))
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7You are "
                            + (isVanished(player) ? "§a§lVANISHED" : "§c§lVISIBLE")));
            }
        }.runTaskTimer(ServerEssentials.getMPlugin(), 0, 20);
    }

    public static ArrayList<Player> getVanished()
    {
        return vanished;
    }

    public boolean isVanished(Player player)
    {
        return vanished.contains(player);
    }

    private void hidePlayer(Player player)
    {
        if (vanished.contains(player)) return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 10,
                false, false, false));

        vanishedTeam.addEntry(player.getName());
        player.setScoreboard(scoreboard);

        vanished.add(player);
    }

    private void showPlayer(Player player)
    {
        vanished.remove(player);

        player.removePotionEffect(PotionEffectType.INVISIBILITY);
        vanishedTeam.removeEntry(player.getName());

        for (Player all : Bukkit.getOnlinePlayers())
            all.showPlayer(ServerEssentials.getMPlugin(), player);
    }

    public static VanishHandler getInstance()
    {
        if (instance == null)
            instance = new VanishHandler();

        return instance;
    }
}
