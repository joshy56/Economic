package com.github.joshy56.economic.storage;

import com.github.joshy56.economic.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 6/3/2023.
 */
public class BungeeEconomyManager implements EconomyManager {
    private EconomyStorage storage;
    private EconomyRepository repository;

    @Override
    public @NotNull Optional<EconomyStorage> storage() {
        return Optional.ofNullable(storage);
    }

    @Override
    public @NotNull Optional<EconomyRepository> repository() {
        if(repository == null)
            return Optional.empty();

        if(repository.manager().filter(manager -> !manager.equals(this)).isPresent())
            repository = null;

        return Optional.ofNullable(repository);
    }

    @Override
    public @NotNull Response attachRepository(@NotNull EconomyRepository repository) {
        if(!(repository instanceof BungeeEconomyRepository))
            return Response.FAILURE;

        this.repository = repository().orElse(repository);

        if(!this.repository.manager().isPresent())
            this.repository.attachManager(this);

        return Response.SUCCESS;
    }

    @Override
    public @NotNull Response attachStorage(@NotNull EconomyStorage storage) {
        if(!(storage instanceof BungeeEconomyStorage))
            return Response.FAILURE;

        this.storage = storage().orElse(storage);

        if(!this.storage.manager().isPresent())
            this.storage.attachManager(this);

        return Response.SUCCESS;
    }

    @Override
    public @NotNull Response detachRepository() {
        if(repository().isPresent())
            repository = null;
        return Response.SUCCESS;
    }

    @Override
    public @NotNull Response detachStorage() {
        if(storage().isPresent())
            storage = null;
        return Response.SUCCESS;
    }
}
