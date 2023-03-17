package com.github.joshy56.economic;

import com.github.joshy56.economic.bungee.EconomicMessageListener;
import com.github.joshy56.economic.player.PlayerAccountCredentials;
import com.github.joshy56.economic.storage.BungeeEconomyManager;
import com.github.joshy56.economic.storage.BungeeEconomyRepository;
import com.github.joshy56.economic.storage.BungeeEconomyStorage;
import com.github.joshy56.economic.storage.EconomyManager;
import com.google.gson.Gson;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandAPIConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class Economic extends JavaPlugin {
    private EconomyManager bungeeManager;
    private Gson gson;
    public static final String PLUGIN_CHANNEL = "economic:cross-data";

    @Override
    public void onLoad() {
        CommandAPI.onLoad(
                new CommandAPIConfig()
                        .silentLogs(true)
                        .verboseOutput(true)
        );

        new CommandAPICommand("economic")
                .withAliases("eco", "money")
                .withSubcommands()
                .executesPlayer(
                        (player, args) -> {

                        }
                )
                .register();

        gson = new Gson();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        if(Bukkit.spigot().getSpigotConfig().getBoolean("bungeecord")) {
            bungeeManager = new BungeeEconomyManager();
            bungeeManager.attachStorage(new BungeeEconomyStorage(this, gson));
            bungeeManager.attachRepository(new BungeeEconomyRepository(new ConcurrentHashMap<>()));

            getServer().getMessenger().registerIncomingPluginChannel(
                    this, PLUGIN_CHANNEL, new EconomicMessageListener(this, gson)
            );
            getServer().getMessenger().registerOutgoingPluginChannel(this, PLUGIN_CHANNEL);
        }
        CommandAPI.onEnable(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if(Bukkit.spigot().getSpigotConfig().getBoolean("bungeecord")) {
            bungeeManager.detachStorage();
            bungeeManager.detachRepository();
            bungeeManager = null;

            getServer().getMessenger().unregisterIncomingPluginChannel(this, PLUGIN_CHANNEL);
            getServer().getMessenger().unregisterOutgoingPluginChannel(this, PLUGIN_CHANNEL);
        }
    }

    public @NotNull Optional<EconomyManager> bungeeManager() {
        return Optional.ofNullable(bungeeManager);
    }
}
