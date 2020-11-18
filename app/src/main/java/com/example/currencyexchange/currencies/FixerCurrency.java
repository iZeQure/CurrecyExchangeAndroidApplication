package com.example.currencyexchange.currencies;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.currencyexchange.currencies.interfaces.CurrencyDAO;
import com.example.currencyexchange.currencies.models.Rate;
import com.example.currencyexchange.presenters.OnDataChanged;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a class handling data from the fixer API.
 * */
public class FixerCurrency implements CurrencyDAO {
    /**
     * The base URL of the API.
     * */
    private final String BASE_API_URL = "http://data.fixer.io/api/";

    /**
     * The endpoint the use for the API.
     * */
    private final String ENDPOINT = "latest";

    /**
     * The access key for the API. // 1000 R / month.
     * */
    private final String ACCESS_KEY = "db38c1a7dd3b413b1ff93fa16c38387c";

    /**
     * Create a list of rate, to store the rates from the API.
     * */
    private ArrayList<Rate> rates = new ArrayList<>();

    /**
     * Create a list of OnDataChanged, which provides the ability
     * to have observers look at this collection, and hook onto it,
     * while the observable does it's thing.
     * */
    private ArrayList<OnDataChanged> listeners = new ArrayList<>();

    /**
     * Defines a field to store the currency base at.
     * */
    private String currencyBase;

    /**
     * Defines a field to store the currency amount, to be calculated with the base.
     * */
    private double currencyAmount;

    /**
     * Make API Request to fixer.
     * @param context Represents a place to store the cache dir.
     * */
    public void getSpotRateDataFromFixerAPI(Context context) {
        // Placeholder json request. // BASE_API_URL + ENDPOINT + "?access_key=" + ACCESS_KEY
        // final String placeholder = "https://jsonplaceholder.typicode.com/todos/1";
        final String requestURL = BASE_API_URL + ENDPOINT + "?access_key=" + ACCESS_KEY + "&" + currencyBase;

        // Make new api request, to obtain json objects.
        JsonObjectRequest newReq = new JsonObjectRequest(
                Request.Method.GET, // Which request method to use.
                requestURL,         // The URL to request to.
                null,
                response -> {
                    try {
                        /**
                         * Pack the response into a JSONObject.
                         * */
                        JSONObject jsonObject = new JSONObject(response.toString());

                        /**
                         * Get the rates from the packaged json object.
                         * */
                        JSONObject ratesJsonObject = jsonObject.getJSONObject("rates");

                        /**
                         * Store the keys from the rates into an iterator of strings.
                         * */
                        Iterator<String> keys = ratesJsonObject.keys();

                        /**
                         * Loop through all rates.
                         * */
                        for (int i = 0; i < ratesJsonObject.length(); i++) {
                            // Store a temp key string.
                            String tempKey = keys.next();

                            // Add new rate to the list of rates.
                            rates.add(new Rate(tempKey, (ratesJsonObject.getDouble(tempKey) * currencyAmount)));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> System.err.println("Error: " + error.toString())
        );

        Volley.newRequestQueue(context).add(newReq);
    }

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
        this.currencyBase = base;
        this.currencyAmount = amountToCalculate;

        // Make api request for the data.
        getSpotRateDataFromFixerAPI(context);

        for (OnDataChanged data : listeners) {
            data.onDataReceived(rates);
        }

        // Return the data.
        return rates;
    }

    /**
     * Add new observer onto the observable.
     * @param dataChanged Represents the observable to get notifications from.
     * */
    public void addListener(OnDataChanged dataChanged) {
        listeners.add(dataChanged);
    }

    /**
     * Remove an existing observer.
     * @param dataChanged Represents the observer to remove from the observable.
     * */
    public void removeListener(OnDataChanged dataChanged) {
        listeners.remove(dataChanged);
    }
}
