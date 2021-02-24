package de.marvinleiers.serveressentials;

import de.marvinleiers.mplugin.MPlugin;
import de.marvinleiers.serveressentials.commands.TPACommand;

public final class ServerEssentials extends MPlugin
{
    @Override
    protected void onStart()
    {
        new TPACommand();
    }

    @Override
    protected void onShutdown()
    {

    }
}
