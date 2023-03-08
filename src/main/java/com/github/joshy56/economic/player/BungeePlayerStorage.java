package com.github.joshy56.economic.player;

import com.github.joshy56.economic.Response;
import com.github.joshy56.economic.storage.EconomyManager;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 8/3/2023.
 */
public class BungeePlayerStorage implements PlayerStorage {
    private PlayerManager manager;



    @Override
    public @NotNull Response save(@NotNull String economyName, @NotNull String playerName) {
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
