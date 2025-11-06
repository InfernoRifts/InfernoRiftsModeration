package com.demondev.infernoRiftsModeration;

import com.demondev.infernoRiftsModeration.commands.BanGUICommand;
import com.demondev.infernoRiftsModeration.listeners.BanInventoryListener;
import com.demondev.infernoRiftsModeration.utils.MenuUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class InfernoRiftsModeration extends JavaPlugin {

    public MenuUtils menuUtils;

    @Override
    public void onEnable() {

        getCommand("bangui").setExecutor(new BanGUICommand(this));
        menuUtils = new MenuUtils(this);

        getServer().getPluginManager().registerEvents(new BanInventoryListener(this), this);

    }

    @Override
    public void onDisable() {
    }
}
