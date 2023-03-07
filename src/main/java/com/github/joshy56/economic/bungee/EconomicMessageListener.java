package com.github.joshy56.economic.bungee;

import com.github.joshy56.economic.Economic;
import com.github.joshy56.economic.storage.EconomyManager;
import com.github.joshy56.economic.storage.EconomyStorage;
import com.google.common.base.Strings;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 5/3/2023.
 */
public class EconomicMessageListener implements PluginMessageListener {
    private final Economic plugin;
    private final Gson gson;

    public EconomicMessageListener(@NotNull final Economic plugin, @NotNull final Gson gson) {
        this.plugin = plugin;
        this.gson = gson;
    }

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, byte @NotNull [] message) {
        if(Strings.isNullOrEmpty(channel))
            return;

        if(!channel.equalsIgnoreCase(Economic.PLUGIN_CHANNEL))
            return;

        ByteArrayDataInput input = ByteStreams.newDataInput(message);

        String subChannel = input.readUTF();
        switch (subChannel) {
            case "put" -> {
                String economyName = input.readUTF();
                Economy economy = gson.fromJson(input.readUTF(), Economy.class);
                plugin.bungeeManager().flatMap(EconomyManager::repository)
                        .ifPresent(repository -> repository.currencies().put(economyName, economy));
            }
        }
    }
}
