package com.github.joshy56.economic.player;

import com.github.joshy56.economic.Response;
import org.jetbrains.annotations.NotNull;

/**
 * Created by joshy23 (justJoshy23 - joshy56) on 16/3/2023.
 */
public interface PlayerAccount {
    double balance();
    default boolean hasBalance(double amount) {
        return amount <= balance();
    }
    @NotNull Response withdrawBalance(double amount);
    @NotNull Response depositBalance(double amount);
    @NotNull PlayerAccountCredentials credentials();
}
