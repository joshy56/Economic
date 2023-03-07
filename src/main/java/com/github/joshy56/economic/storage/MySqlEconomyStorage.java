package com.github.joshy56.economic.storage;

import com.github.joshy56.economic.Response;
import com.google.gson.Gson;
import com.google.gson.internal.sql.SqlTypesSupport;
import net.milkbowl.vault.economy.Economy;
import org.jetbrains.annotations.NotNull;
import org.mariadb.jdbc.client.impl.ConnectionHelper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ConnectionBuilder;
import java.sql.JDBCType;
import java.util.Optional;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 7/3/2023.
 */
public class MySqlEconomyStorage implements EconomyStorage {
    private final Gson gson;
    private EconomyManager manager;
    private Connection connection;

    public MySqlEconomyStorage(@NotNull final Gson gson) {
        this.gson = gson;
    }

    @Override
    public @NotNull Response save(@NotNull Economy economy) {
        return null;
    }

    @Override
    public @NotNull Response saveAll() {
        return null;
    }

    @Override
    public @NotNull Response load(@NotNull String currencyName) {
        return null;
    }

    @Override
    public @NotNull Response loadAll() {
        return null;
    }

    @Override
    public @NotNull Optional<EconomyManager> manager() {
        if(manager == null)
            return Optional.empty();

        if(manager.storage().filter(storage -> storage.equals(this)).isEmpty())
            detachManager();

        return Optional.ofNullable(manager);
    }

    @Override
    public @NotNull Response attachManager(@NotNull EconomyManager manager) {
        EconomyManager actual = manager().orElse(manager);

        if(actual.storage().filter(storage -> storage.equals(this)).isEmpty())
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
