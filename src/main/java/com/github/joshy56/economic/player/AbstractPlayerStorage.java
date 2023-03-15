package com.github.joshy56.economic.player;

import com.github.joshy56.economic.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 9/3/2023.
 */
public abstract class AbstractPlayerStorage implements PlayerStorage {
    private PlayerManager manager;

    @Override
    public abstract @NotNull Response save(@NotNull String economyName, @NotNull String playerName);

    @Override
    public abstract @NotNull Response save(@NotNull String economyName);

    @Override
    public abstract @NotNull Response saveAll();

    @Override
    public abstract @NotNull Response load(@NotNull String economyName, @NotNull String playerName);

    @Override
    public abstract @NotNull Response load(@NotNull String economyName);

    @Override
    public abstract @NotNull Response loadAll();

    @Override
    public @NotNull Optional<PlayerManager> manager() {
        if(manager == null)
            return Optional.empty();

        if(manager.storage().filter(storage -> storage.equals(this)).isEmpty())
            manager = null;

        return Optional.ofNullable(manager);
    }

    @Override
    public @NotNull Response attachManager(@NotNull PlayerManager manager) {
        if(manager().isPresent())
            return Response.FAILURE;

        PlayerStorage actual = manager.storage().orElse(this);

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
