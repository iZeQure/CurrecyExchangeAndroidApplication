package com.example.currencyexchange.presenters;

import android.content.Context;

import com.example.currencyexchange.currencies.models.Rate;
import com.example.currencyexchange.managers.CurrencyManager;

import java.util.ArrayList;

/**
 * Handles the communication between MainActivity.
 * */
public class MainActivityPresenter {
    private CurrencyManager currencyManager;
    private View view;

    public MainActivityPresenter(View view) {
        this.currencyManager = new CurrencyManager();
        this.view = view;
    }

    public void calculateCurrency(String base, double amountToCalculate, Context context) {
        this.view.getRatesLoaded(currencyManager.getRates(base, amountToCalculate, context));
    }

    public interface View {
        void getRatesLoaded(ArrayList<Rate> rates);
    }
}
