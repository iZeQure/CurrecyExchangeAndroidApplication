package com.example.currencyexchange;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyexchange.currencies.models.Rate;
import com.example.currencyexchange.presenters.MainActivityPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

    private CurrencyAdapter currencyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityPresenter presenter = new MainActivityPresenter(this);

        setCurrencyBaseSpinnerItems();

        Button currencyCalculateButton = findViewById(R.id.currencyCalculateButton);
        EditText currencyInputEditText = findViewById(R.id.currencyInput);

        /**
         * Calculate exchange button.
         * */
        currencyCalculateButton
                /**
                 * Set on click listener.
                 * */
                .setOnClickListener(
                    v -> {
                        // Get dropdown value.
                        String selectedItemValue = ((Spinner) findViewById(R.id.currencySpinner)).getSelectedItem().toString();

                        // Check if the value is NOT null.
                        if (selectedItemValue != null) {
                            String currencyInputValue = currencyInputEditText.getText().toString();

                            if (!currencyInputValue.equals("") && !currencyInputValue.equals("0"))
                                presenter.calculateCurrency(selectedItemValue, Double.valueOf(currencyInputValue), this);
                            else {
                                presenter.calculateCurrency(selectedItemValue, 1, this);
                            }
                        }
                    });
    }

    @Override
    public void getRatesLoaded(ArrayList<Rate> rates) {
        if (rates != null) {
            if (!rates.isEmpty()) {

                try {
                    currencyAdapter = new CurrencyAdapter(this, rates);
                    ((ListView) findViewById(R.id.currencyListView)).setAdapter(currencyAdapter);
                    Thread.sleep(1500);

                    currencyAdapter.notifyDataSetChanged();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * Sets the base currencies into a spinner.
     * */
    private void setCurrencyBaseSpinnerItems() {
        // Define static values.
        String[] menu = new String[] {"USD", "EUR", "DKK"};

        // Get the spinner dropdown adapter, with provided layout.
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, menu);

        // Set the adapter to the spinner view.
        ((Spinner) findViewById(R.id.currencySpinner)).setAdapter(adapter);
    }
}