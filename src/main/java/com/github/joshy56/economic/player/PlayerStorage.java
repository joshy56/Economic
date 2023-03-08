package com.github.joshy56.economic.player;

import com.github.joshy56.economic.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 7/3/2023.
 */
public interface PlayerStorage {
    @NotNull Response save(@NotNull String economyName, @NotNull String playerName);
    @NotNull Response save(@NotNull String economyName);
    @NotNull Response saveAll();
    @NotNull Response load(@NotNull String economyName, @NotNull String playerName);
    @NotNull Response load(@NotNull String economyName);
    @NotNull Response loadAll();
    @NotNull Optional<PlayerManager> manager();
    @NotNull Response attachManager(@NotNull final PlayerManager manager);
    @NotNull Response detachManager();
}
