package com.example.currencyexchange;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.currencyexchange.currencies.models.Rate;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the logic to control the currency list view.
 * */
public class CurrencyAdapter extends ArrayAdapter<Rate> {
    private Context context;
    private List<Rate> ratesList;

    /**
     * Initialize a new currency adapter, which inherits from Array Adapter.
     * @see ArrayAdapter
     * @param context
     * @param list The list of data to use.
     * */
    public CurrencyAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Rate> list) {
        super(context, 0 , list);
        this.context = context;
        ratesList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            // Set new layout from the context, and use the currency list layout.
            listItem = LayoutInflater.from(context).inflate(R.layout.currency_list_layout,parent,false);

        // Get the current rate from the list.
        Rate currentRate = ratesList.get(position);

        // Set flag to the right currency.
//        ImageView image = (ImageView)listItem.findViewById(R.drawable);
//        image.setImageResource();

        // Get view elements to append data to.
        TextView name = (TextView) listItem.findViewById(R.id.textView_currency_name);
        TextView currencySpotRate = (TextView) listItem.findViewById(R.id.textView_currency_rate);

        // Set data to the specified view elements.
        name.setText(currentRate.getName()); // Set the currency name.
        currencySpotRate.setText(String.valueOf(currentRate.getSpotRate())); // Set the spot rate for the currency.

        // Return list.
        return listItem;
    }
}
