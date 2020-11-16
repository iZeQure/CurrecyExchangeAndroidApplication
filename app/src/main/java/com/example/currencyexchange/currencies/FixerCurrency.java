package com.example.currencyexchange.currencies;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.currencyexchange.currencies.interfaces.CurrencyDAO;
import com.example.currencyexchange.currencies.models.Rate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

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

    private ArrayList<Rate> rates;
    private JsonObjectRequest apiRequest;

    /**
     * Gets the current rates.
     *
     * @param base The base of the currency to differentiate.
     * @return A list of rates.
     * @see Rate for possible data.
     */
    @Override
    public ArrayList<Rate> getRates(String base) {
        // Call to make a request for the data.
        makeRequest();

        // Return the data.
        return rates;
    }

    /**
     * Make API Request to fixer.
     * TODO : This shit ain't working.
     * */
    private void makeRequest() {
        // Make new api request, to obtain json objects.
        apiRequest = new JsonObjectRequest(
                Request.Method.GET,
                BASE_API_URL + ENDPOINT + "?access_key=" + ACCESS_KEY,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Type rateListType = new TypeToken<ArrayList<Rate>>(){}.getType();
                        rates = new Gson().fromJson(response.toString(), rateListType);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println("Error: " + error.toString());
                    }
                }
        );
    }
}
