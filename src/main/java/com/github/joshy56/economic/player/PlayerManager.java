package com.github.joshy56.economic.player;

import com.github.joshy56.economic.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 7/3/2023.
 */
public interface PlayerManager {
    @NotNull Optional<PlayerStorage> storage();
    @NotNull Optional<PlayerRepository> repository();
    @NotNull Response attachStorage(@NotNull final PlayerStorage storage);
    @NotNull Response attachRepository(@NotNull final PlayerRepository repository);
    @NotNull Response detachStorage();
    @NotNull Response detachRepostory();
}
