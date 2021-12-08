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

public class OrderAdapter extends ArrayAdapter<Order> {

    public OrderAdapter(Activity context, ArrayList<Order> orders){
        super(context , 0 , orders);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item_for_placed_orders , parent, false);
        }
        Order currentOrder = getItem(position);


        TextView textView = listItem.findViewById(R.id.order_id_of_placed_orders);
        textView.setText("ORDER ID: " +currentOrder.getOrderId());

        textView = listItem.findViewById(R.id.date_of_placed_orders);
        textView.setText("ORDER DATE:" + currentOrder.getDate());

        textView = listItem.findViewById(R.id.order_cost);
        textView.setText("$"+  currentOrder.getOrderCost());

        return listItem;

    }
}
