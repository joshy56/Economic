package com.github.joshy56.economic.player;

import com.github.joshy56.economic.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 16/3/2023.
 */
public class SimplePlayerRepository implements PlayerRepository {
    private final Map<String, Map<String, PlayerAccount>> players;
    private PlayerManager manager;

    public SimplePlayerRepository(@NotNull final Map<String, Map<String, PlayerAccount>> players) {
        this.players = players;
    }

    @Override
    public @NotNull Map<String, Map<String, PlayerAccount>> players() {
        return players;
    }

    @Override
    public @NotNull Optional<PlayerManager> manager() {
        if(manager == null)
            return Optional.empty();

        if(manager.repository().filter(repository -> repository.equals(this)).isEmpty())
            manager = null;

        return Optional.ofNullable(manager);
    }

    @Override
    public @NotNull Response attachManager(@NotNull PlayerManager manager) {
        if(manager().isPresent())
            return Response.FAILURE;

        if(!manager.repository().orElse(this).equals(this))
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
