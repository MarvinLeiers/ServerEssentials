package de.marvinleiers.serveressentials;

import de.marvinleiers.mplugin.MPlugin;
import de.marvinleiers.serveressentials.commands.TPACommand;
import de.marvinleiers.serveressentials.commands.VanishCommand;
import de.marvinleiers.serveressentials.listeners.JoinListener;

public final class ServerEssentials extends MPlugin
{
    @Override
    protected void onStart()
    {
        new TPACommand();
        new VanishCommand();

        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }

    @Override
    protected void onShutdown()
    {

    }
}
