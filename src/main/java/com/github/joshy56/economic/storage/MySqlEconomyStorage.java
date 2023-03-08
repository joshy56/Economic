package com.github.joshy56.economic.storage;

import com.github.joshy56.economic.Response;
import com.google.gson.Gson;
import com.google.gson.internal.sql.SqlTypesSupport;
import net.milkbowl.vault.economy.Economy;
import org.jetbrains.annotations.NotNull;
import org.mariadb.jdbc.Configuration;
import org.mariadb.jdbc.Driver;
import org.mariadb.jdbc.client.impl.ConnectionHelper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 7/3/2023.
 */
public class MySqlEconomyStorage implements EconomyStorage {
    private final Gson gson;
    private EconomyManager manager;
    private Connection connection;

    public MySqlEconomyStorage(@NotNull final Gson gson, @NotNull String urlConnection) {
        this.gson = gson;
        try {
            Configuration configuration = new Configuration.Builder()
                    .cachePrepStmts(true)
                    .prepStmtCacheSize(250)
                    .useServerPrepStmts(true)
                    .connectTimeout(3000)
                    .build();
            Driver driver = new Driver();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull Response save(@NotNull Economy economy) {
        return null;
    }

    @Override
    public @NotNull Response save(@NotNull String economyName, @NotNull String playerName) {
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
    public @NotNull Response load(@NotNull String economyName, @NotNull String playerName) {
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
