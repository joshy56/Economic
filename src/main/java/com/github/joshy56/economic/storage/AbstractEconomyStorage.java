package com.github.joshy56.economic.storage;

import com.github.joshy56.economic.Response;
import net.milkbowl.vault.economy.Economy;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 9/3/2023.
 */
public abstract class AbstractEconomyStorage implements EconomyStorage {
    private EconomyManager manager;

    @Override
    public abstract @NotNull Response save(@NotNull Economy economy);

    @Override
    public abstract @NotNull Response save(@NotNull String economyName, @NotNull String playerName);

    @Override
    public abstract @NotNull Response saveAll();

    @Override
    public abstract @NotNull Response load(@NotNull String currencyName);

    @Override
    public abstract @NotNull Response load(@NotNull String economyName, @NotNull String playerName);

    @Override
    public abstract @NotNull Response loadAll();

    @Override
    public @NotNull Optional<EconomyManager> manager() {
        if(manager == null)
            return Optional.empty();

        if(manager.storage().filter(storage -> storage.equals(this)).isEmpty())
            manager = null;

        return Optional.ofNullable(manager);
    }

    @Override
    public @NotNull Response attachManager(@NotNull EconomyManager manager) {
        if(manager().isPresent())
            return Response.FAILURE;

        EconomyStorage actual = manager.storage().orElse(this);
        if(!actual.equals(this))
            return Response.FAILURE;

        this.manager = manager;
        return Response.SUCCESS;
    }

    @Override
    public @NotNull Response detachManager() {
        if(manager != null)
            manager = null;
        return Response.SUCCESS;
    }
}
