package com.demondev.infernoRiftsModeration;

import com.demondev.infernoRiftsModeration.commands.ModToolsCommand;
import com.demondev.infernoRiftsModeration.commands.ModToolsTabComplete;
import org.bukkit.plugin.java.JavaPlugin;

public final class InfernoRiftsModeration extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("modtools").setExecutor(new ModToolsCommand());
        this.getCommand("modtools").setTabCompleter(new ModToolsTabComplete());
    }

    @Override
    public void onDisable() {
    }
}
