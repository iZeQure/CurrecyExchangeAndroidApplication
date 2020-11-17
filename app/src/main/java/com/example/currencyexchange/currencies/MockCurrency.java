package com.example.currencyexchange.currencies;

import android.content.Context;

import com.example.currencyexchange.currencies.interfaces.CurrencyDAO;
import com.example.currencyexchange.currencies.models.Rate;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a mocked class with static data.
 * */
public class MockCurrency implements CurrencyDAO {
    /**
     * Gets the current rates.
     *
     * @param base              The base of the currency to differentiate.
     * @param amountToCalculate The amount the user has input, to get the currency for.
     * @param context           The context, where the cache dir is stored.
     * @return A list of rates.
     * @see Rate for possible data.
     */
    @Override
    public ArrayList<Rate> getRates(String base, double amountToCalculate, Context context) {
        return new ArrayList<Rate>(Arrays.asList(
                new Rate("USD", 1.636492),
                new Rate("EUR", 1.739516),
                new Rate("AUD", 1.566015),
                new Rate("CAD", 1.560132),
                new Rate("CHF", 1.154727),
                new Rate("CNY", 7.827874),
                new Rate("GBP", 0.882047),
                new Rate("JPY", 132.360679)
        ));
    }
}