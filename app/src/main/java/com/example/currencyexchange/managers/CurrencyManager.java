package com.example.currencyexchange.managers;

import com.example.currencyexchange.currencies.MockCurrency;
import com.example.currencyexchange.currencies.interfaces.CurrencyDAO;
import com.example.currencyexchange.currencies.models.Rate;

import java.util.ArrayList;

/**
 * Handles the management for currency.
 * */
public class CurrencyManager {
    private CurrencyDAO currency = new MockCurrency();

    public ArrayList<Rate> getRates(String base) {
        return currency.getRates(base);
    }
}
