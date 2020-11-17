package com.example.currencyexchange.currencies.interfaces;

import android.content.Context;

import com.example.currencyexchange.currencies.models.Rate;

import java.util.ArrayList;

/**
 * Represents the usage of DAO Pattern, to get currency rates.
 * */
public interface CurrencyDAO {
    /**
     * Gets the current rates.
     * @param base The base of the currency to differentiate.
     * @param amountToCalculate The amount the user has input, to get the currency for.
     * @param context The context, where the cache dir is stored.
     * @return A list of rates.
     * @see Rate for possible data.
     * */
    ArrayList<Rate> getRates(String base, double amountToCalculate, Context context);
}
