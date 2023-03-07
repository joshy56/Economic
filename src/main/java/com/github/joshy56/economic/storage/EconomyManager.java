package com.github.joshy56.economic.storage;

import com.github.joshy56.economic.Response;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @since 5/3/2023
 * @author joshy56
 */
public interface EconomyManager {
    @NotNull Optional<EconomyStorage> storage();
    @NotNull Optional<EconomyRepository> repository();
    @NotNull Response attachRepository(@NotNull final EconomyRepository repository);
    @NotNull Response attachStorage(@NotNull final EconomyStorage storage);
    @NotNull Response detachRepository();
    @NotNull Response detachStorage();
}
