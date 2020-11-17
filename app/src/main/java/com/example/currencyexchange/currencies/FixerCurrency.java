package com.example.currencyexchange.currencies;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.currencyexchange.currencies.interfaces.CurrencyDAO;
import com.example.currencyexchange.currencies.models.Rate;

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

    private ArrayList<Rate> rates = new ArrayList<>();
    private JsonObjectRequest apiRequest;
    private String currencyBase;
    private double currencyAmount;

    /**
     * Make API Request to fixer.
     * */
    public void getSpotRateDataFromFixerAPI(Context context) {
        // Placeholder json request. // BASE_API_URL + ENDPOINT + "?access_key=" + ACCESS_KEY
        // final String placeholder = "https://jsonplaceholder.typicode.com/todos/1";
        final String requestURL = BASE_API_URL + ENDPOINT + "?access_key=" + ACCESS_KEY + "&" + currencyBase;

        // Make new api request, to obtain json objects.
        JsonObjectRequest newReq = new JsonObjectRequest(
                Request.Method.GET,
                requestURL,
                null,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());

                        JSONObject ratesJsonObject = jsonObject.getJSONObject("rates");

                        Iterator<String> keys = ratesJsonObject.keys();

                        for (int i = 0; i < ratesJsonObject.length(); i++) {
                            String tempKey = keys.next();
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

        // Return the data.
        return rates;
    }
}
