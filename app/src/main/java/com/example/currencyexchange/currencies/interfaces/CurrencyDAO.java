package com.example.currencyexchange.currencies.interfaces;

import com.example.currencyexchange.currencies.models.Rate;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the usage of DAO Pattern, to get currency rates.
 * */
public interface CurrencyDAO {
    /**
     * Gets the current rates.
     * @param base The base of the currency to differentiate.
     * @return A list of rates.
     * @see Rate for possible data.
     * */
    ArrayList<Rate> getRates(String base);
}
