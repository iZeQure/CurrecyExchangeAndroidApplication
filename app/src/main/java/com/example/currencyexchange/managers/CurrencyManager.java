package com.example.currencyexchange.managers;

import android.content.Context;

import com.example.currencyexchange.currencies.FixerCurrency;
import com.example.currencyexchange.currencies.interfaces.CurrencyDAO;
import com.example.currencyexchange.currencies.models.Rate;
import com.example.currencyexchange.presenters.OnDataChanged;

import java.util.ArrayList;

/**
 * Handles the management for currency.
 * */
public class CurrencyManager {
    private CurrencyDAO currency = new FixerCurrency();

    /**
     * Gets a list of live rates.
     * @param base The base currency, to calculate the currency from.
     * @param context the view context, to provide the cache dir for.
     * @return A list of rate.
     * @see Rate for what for the object contains.
     * */
    public ArrayList<Rate> getRates(String base, double amountToCalculate, Context context) {
        return currency.getRates(base, amountToCalculate, context);
    }

    public void addListener(OnDataChanged dataChanged) {
        ((FixerCurrency)currency).addListener(dataChanged);
    }
}
