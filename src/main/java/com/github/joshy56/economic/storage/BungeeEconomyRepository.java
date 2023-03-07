package com.github.joshy56.economic.storage;

import com.github.joshy56.economic.Response;
import net.milkbowl.vault.economy.Economy;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 6/3/2023.
 */
public class BungeeEconomyRepository implements EconomyRepository {
    private final ConcurrentHashMap<String, Economy> currencies;
    private EconomyManager manager;

    public BungeeEconomyRepository(@NotNull final ConcurrentHashMap<String, Economy> currencies) {
        this.currencies = currencies;
    }

    @Override
    public @NotNull Map<String, Economy> currencies() {
        return currencies;
    }

    @Override
    public @NotNull Optional<EconomyManager> manager() {
        if(manager == null)
            return Optional.empty();

        if(manager.repository().filter(repository -> !repository.equals(this)).isPresent())
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
