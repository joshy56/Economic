package com.github.joshy56.economic.player;

import com.github.joshy56.economic.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 7/3/2023.
 */
public interface PlayerRepository {
    @NotNull Map<String, Set<String>> players();
    @NotNull Optional<PlayerManager> manager();
    @NotNull Response attachManager(@NotNull final PlayerManager manager);
    @NotNull Response detachManager();
}
