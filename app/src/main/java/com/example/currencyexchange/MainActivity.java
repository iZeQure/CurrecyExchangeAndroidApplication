package com.example.currencyexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.currencyexchange.currencies.models.Rate;
import com.example.currencyexchange.presenters.MainActivityPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityPresenter presenter = new MainActivityPresenter(this);
    }

    @Override
    public void getRatesLoaded(ArrayList<Rate> rates) {
        String[] menu = new String[] {"USD", "EUR", "DKK"};

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, menu);
        CurrencyAdapter adapter2 = new CurrencyAdapter(this, rates);

        ((Spinner) findViewById(R.id.currencySpinner)).setAdapter(adapter);
        ((ListView) findViewById(R.id.currencyListView)).setAdapter(adapter2);
    }
}