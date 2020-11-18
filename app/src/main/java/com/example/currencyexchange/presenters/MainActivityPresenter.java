package com.example.currencyexchange.presenters;

import android.content.Context;

import com.example.currencyexchange.currencies.models.Rate;
import com.example.currencyexchange.managers.CurrencyManager;

import java.util.ArrayList;

/**
 * Handles the communication between MainActivity.
 * */
public class MainActivityPresenter implements OnDataChanged {
    private CurrencyManager currencyManager;
    private View view;
    private ArrayList<Rate> rates = new ArrayList<>();

    public MainActivityPresenter(View view) {
        this.currencyManager = new CurrencyManager();
        this.view = view;
        this.currencyManager.addListener(this::onDataReceived);
    }

    public void calculateCurrency(String base, double amountToCalculate, Context context) {
        rates = (currencyManager.getRates(base, amountToCalculate, context));
    }

    @Override
    public void onDataReceived(Object object) {
        this.view.getRatesLoaded((ArrayList<Rate>) object);
    }

    public interface View {
        void getRatesLoaded(ArrayList<Rate> rates);
    }
}
