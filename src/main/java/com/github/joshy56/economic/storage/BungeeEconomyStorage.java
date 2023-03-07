package com.github.joshy56.economic.storage;

import com.github.joshy56.economic.Economic;
import com.github.joshy56.economic.Response;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 6/3/2023.
 */
public class BungeeEconomyStorage implements EconomyStorage {
    private final Plugin plugin;
    private final Gson gson;
    private EconomyManager manager;

    public BungeeEconomyStorage(@NotNull final Plugin plugin, @NotNull final Gson gson) {
        this.plugin = plugin;
        this.gson = gson;
    }

    @Override
    public @NotNull Response save(@NotNull Economy economy) {
        if(manager().isEmpty())
            return Response.FAILURE;

        if(plugin.getServer().getOnlinePlayers().isEmpty())
            return Response.FAILURE;

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("put");
        output.writeUTF(gson.toJson(economy));

        Iterators.find(plugin.getServer().getOnlinePlayers().iterator(), p -> true)
                .sendPluginMessage(plugin, Economic.PLUGIN_CHANNEL, output.toByteArray());
        return Response.SUCCESS;
    }

    @Override
    public @NotNull Response saveAll() {
        if(manager().isEmpty())
            return Response.FAILURE;

        Collection<Economy> currencies = manager().flatMap(EconomyManager::repository)
                .map(EconomyRepository::currencies)
                .map(Map::values)
                .orElse(Collections.emptyList());

        boolean failed = false;
        for (Economy currency : currencies) {
            if(save(currency) != Response.SUCCESS)
                failed = true;
        }

        return (failed) ? Response.FAILURE : Response.SUCCESS;
    }

    @Override
    public @NotNull Response load(@NotNull String currencyName) {
        if(manager().isEmpty())
            return Response.FAILURE;

        if(plugin.getServer().getOnlinePlayers().isEmpty())
            return Response.FAILURE;

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("get");
        output.writeUTF(currencyName);

        Iterators.find(plugin.getServer().getOnlinePlayers().iterator(), p -> true)
                .sendPluginMessage(plugin, Economic.PLUGIN_CHANNEL, output.toByteArray());
        return Response.SUCCESS;
    }

    @Override
    public @NotNull Response loadAll() {
        if(manager().isEmpty())
            return Response.FAILURE;

        Collection<String> currencies = manager().flatMap(EconomyManager::repository)
                .map(EconomyRepository::currencies)
                .map(Map::keySet)
                .orElse(Collections.emptySet());

        boolean failed = false;
        for (String currencyName : currencies) {
            if(load(currencyName) != Response.SUCCESS)
                failed = true;
        }

        return (failed) ? Response.FAILURE : Response.SUCCESS;
    }

    @Override
    public @NotNull Optional<EconomyManager> manager() {
        if(manager == null)
            return Optional.empty();

        if(manager.storage().filter(storage -> !storage.equals(this)).isPresent())
            manager = null;

        return Optional.ofNullable(manager);
    }

    @Override
    public @NotNull Response attachManager(@NotNull EconomyManager manager) {
        if(!(manager instanceof BungeeEconomyManager))
            return Response.FAILURE;

        this.manager = manager().orElse(manager);
        return Response.SUCCESS;
    }

    @Override
    public @NotNull Response detachManager() {
        if(manager != null)
            manager = null;
        return Response.SUCCESS;
    }
}
