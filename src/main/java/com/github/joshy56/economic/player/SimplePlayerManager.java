package com.github.joshy56.economic.player;

import com.github.joshy56.economic.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 16/3/2023.
 */
public class SimplePlayerManager implements PlayerManager {
    private PlayerStorage storage;
    private PlayerRepository repository;

    @Override
    public @NotNull Optional<PlayerStorage> storage() {
        if(storage == null)
            return Optional.empty();

        if(storage.manager().filter(manager -> manager.equals(this)).isEmpty())
            storage = null;

        return Optional.ofNullable(storage);
    }

    @Override
    public @NotNull Optional<PlayerRepository> repository() {
        if(repository == null)
            return Optional.empty();

        if(repository.manager().filter(manager -> manager.equals(this)).isEmpty())
            repository = null;

        return Optional.ofNullable(repository);
    }

    @Override
    public @NotNull Response attachStorage(@NotNull PlayerStorage storage) {
        if(storage().isPresent())
            return Response.FAILURE;

        if(!storage.manager().orElse(this).equals(this))
            return Response.FAILURE;

        this.storage = storage;
        return Response.SUCCESS;
    }

    @Override
    public @NotNull Response attachRepository(@NotNull PlayerRepository repository) {
        if(repository().isPresent())
            return Response.FAILURE;

        if(!repository.manager().orElse(this).equals(this))
            return Response.FAILURE;

        this.repository = repository;
        return Response.SUCCESS;
    }

    @Override
    public @NotNull Response detachStorage() {
        if(storage != null)
            storage = null;
        return Response.SUCCESS;
    }

    @Override
    public @NotNull Response detachRepository() {
        if(repository != null)
            repository = null;
        return Response.SUCCESS;
    }
}
