package com.example.currencyexchange.currencies;

import com.example.currencyexchange.currencies.interfaces.CurrencyDAO;
import com.example.currencyexchange.currencies.models.Rate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a mocked class with static data.
 * */
public class MockCurrency implements CurrencyDAO {

    /**
     * Gets the current rates.
     *
     * @param base Provide the name of the currency name.
     * @return A list of rates.
     * @see Rate for possible data.
     */
    @Override
    public ArrayList<Rate> getRates(String base) {
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