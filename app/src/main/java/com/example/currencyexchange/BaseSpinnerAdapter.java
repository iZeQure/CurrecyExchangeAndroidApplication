package com.example.currencyexchange;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.currencyexchange.currencies.models.Rate;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BaseSpinnerAdapter extends ArrayAdapter<Rate> {
    private Context context;
    private List<Rate> ratesList;

    /**
     * Initialize a new currency adapter, which inherits from Array Adapter.
     * @see ArrayAdapter
     * @param context
     * @param list The list of data to use.
     * */
    public BaseSpinnerAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Rate> list) {
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
            listItem = LayoutInflater.from(context).inflate(R.layout.base_spinner_dropdown,parent,false);

        notifyDataSetChanged();

        // Return list.
        return listItem;
    }
}
