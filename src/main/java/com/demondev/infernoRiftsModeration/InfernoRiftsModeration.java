package com.demondev.infernoRiftsModeration;

import com.demondev.infernoRiftsModeration.commands.BanGUICommand;
import com.demondev.infernoRiftsModeration.listeners.BanInventoryListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class InfernoRiftsModeration extends JavaPlugin {

    @Override
    public void onEnable() {

        getCommand("bangui").setExecutor(new BanGUICommand());

        getServer().getPluginManager().registerEvents(new BanInventoryListener(), this);

    }

    @Override
    public void onDisable() {
    }
}
