package com.github.joshy56.economic.player;

import com.github.joshy56.economic.Response;
import org.jetbrains.annotations.NotNull;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 9/3/2023.
 */
public class MySqlPlayerStorage extends AbstractPlayerStorage {
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
}
