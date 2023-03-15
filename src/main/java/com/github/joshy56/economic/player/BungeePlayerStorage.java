package com.github.joshy56.economic.player;

import com.github.joshy56.economic.Economic;
import com.github.joshy56.economic.Response;
import com.github.joshy56.economic.storage.EconomyManager;
import com.github.joshy56.economic.storage.EconomyRepository;
import com.github.joshy56.economic.storage.EconomyStorage;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 8/3/2023.
 */
public class BungeePlayerStorage extends AbstractPlayerStorage {
    private final Economic plugin;

    public BungeePlayerStorage(@NotNull final Economic plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull Response save(@NotNull String economyName, @NotNull String playerName) {
        if(manager().isEmpty())
            return Response.FAILURE;

        

        if(plugin.bungeeManager().isEmpty())
            return Response.FAILURE;

        EconomyManager economyManager = plugin.bungeeManager().get();
        if(economyManager.storage().isEmpty() || economyManager.repository().isEmpty())
            return Response.FAILURE;

        EconomyRepository economyRepository = economyManager.repository().get();
        if(!economyRepository.currencies().containsKey(economyName) && economyManager.storage().map(economyStorage -> economyStorage.load(economyName)).orElse(Response.FAILURE) != Response.SUCCESS)
            return Response.FAILURE;


        return null;
    }

    @Override
    public @NotNull Response save(@NotNull String economyName) {
        return null;
    }

    @Override
    public @NotNull Response saveAll() {
        return null;
    }

    @Override
    public @NotNull Response load(@NotNull String economyName, @NotNull String playerName) {
        return null;
    }

    @Override
    public @NotNull Response load(@NotNull String economyName) {
        return null;
    }

    @Override
    public @NotNull Response loadAll() {
        return null;
    }
}
