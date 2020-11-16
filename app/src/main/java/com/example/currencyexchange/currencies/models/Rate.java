package com.example.currencyexchange.currencies.models;

/**
 * Represents a class storing a currency rate.
 * */
public class Rate {
    private String name;
    private double spotRate;

    /**
     * Initializes a new rate.
     * @param name The name of the rate.
     * @param spotRate The current spot rate of the defined rate.
     * */
    public Rate(String name, double spotRate) {
        this.name = name;
        this.spotRate = spotRate;
    }

    /**
     * Gets the name of the rate.
     * @return A String containing the name of the rate.
     * */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the rate.
     * @param name Provide the name of the rate.
     * */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the current spot rate.
     * @return A double containing the current spot rate.
     * */
    public double getSpotRate() {
        return spotRate;
    }

    /**
     * Sets the spot rate.
     * @param spotRate Provide the value of the spot rate.
     * */
    private void setSpotRate(double spotRate) {
        this.spotRate = spotRate;
    }
}
