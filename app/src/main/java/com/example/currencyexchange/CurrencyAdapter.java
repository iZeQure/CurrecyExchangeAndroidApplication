package com.example.currencyexchange;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.currencyexchange.currencies.models.Rate;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends ArrayAdapter<Rate> {
    private Context mContext;
    private List<Rate> ratesList = new ArrayList<>();

    public CurrencyAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Rate> list) {
        super(context, 0 , list);
        mContext = context;
        ratesList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.currency_layout,parent,false);

        Rate currentRate = ratesList.get(position);

//        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_currency_poster);
//        image.setImageResource();

        TextView name = (TextView) listItem.findViewById(R.id.textView_currency_name);
        name.setText(currentRate.getName());

        TextView currencySpotRate = (TextView) listItem.findViewById(R.id.textView_currency_rate);
        currencySpotRate.setText(String.valueOf(currentRate.getSpotRate()));

        return listItem;
    }
}
