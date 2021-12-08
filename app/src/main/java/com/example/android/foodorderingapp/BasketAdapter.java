package com.example.android.foodorderingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.foodorderingapp.R;

import java.util.ArrayList;

public class BasketAdapter extends ArrayAdapter<Flower> {
    Activity activity;

    public BasketAdapter(Activity context, ArrayList<Flower> flowers){
        super(context , 0 , flowers);
        activity = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item_for_basket , parent, false);
        }
        Flower currentFlower = getItem(position);


        TextView flowerName = listItem.findViewById(R.id.flower_name_bucket);
        flowerName.setText(currentFlower.getName());


        TextView flowerCost = listItem.findViewById(R.id.flower_cost_bucket);
        flowerCost.setText("$" + currentFlower.getCost());


        TextView flowerCategory = listItem.findViewById(R.id.flower_category_bucket);
        flowerCategory.setText(currentFlower.getCategory());

        TextView flowerCount = listItem.findViewById(R.id.flower_count_bucket);
        flowerCount.setText("Quantity: " + String.valueOf(currentFlower.count));

        return listItem;

    }


}
