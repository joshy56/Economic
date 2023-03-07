package com.github.joshy56.economic.storage;

import com.github.joshy56.economic.Response;
import net.milkbowl.vault.economy.Economy;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

/**
 * @since 5/3/2023
 * @author joshy56
 */
public interface EconomyRepository {
    @NotNull Map<String, Economy> currencies();
    @NotNull Optional<EconomyManager> manager();
    @NotNull Response attachManager(@NotNull final EconomyManager manager);
    @NotNull Response detachManager();
}
