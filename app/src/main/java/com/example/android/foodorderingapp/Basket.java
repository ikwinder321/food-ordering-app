package com.example.android.foodorderingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.foodorderingapp.R;

import java.util.ArrayList;


public class Basket extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.bucketCost = 0;
        ArrayList<Flower> flowerBasket = new ArrayList<>();

        boolean is = false;
        for(int i = 0 ; i < 6 ; i++){
            if(MainActivity.weddingCount[i] != 0){
                String name = MainActivity.weddingFlower.get(i).getName();
                String category = "wedding";
                int imageId = MainActivity.weddingFlower.get(i).getImageResourceId();
                int count = MainActivity.weddingCount[i];
                int cost = Integer.parseInt(MainActivity.weddingFlower.get(i).getCost()) * count;

                MainActivity.bucketCost += cost;
                flowerBasket.add(new Flower(name, category, imageId, cost , count));
                is = true;
            }
        }
        for(int i = 0 ; i < 6 ; i++){
            if(MainActivity.birthdayCount[i] != 0){
                String name = MainActivity.birthdayFlower.get(i).getName();
                String category = "birthday";
                int imageId = MainActivity.birthdayFlower.get(i).getImageResourceId();
                int count = MainActivity.birthdayCount[i];
                int cost = Integer.parseInt(MainActivity.birthdayFlower.get(i).getCost()) * count;
                MainActivity.bucketCost += cost;
                flowerBasket.add(new Flower(name, category, imageId, cost , count));
                is = true;
            }
        }
        for(int i = 0 ; i < 6 ; i++){
            if(MainActivity.valentineCount[i] != 0){
                String name = MainActivity.valentineFlower.get(i).getName();
                String category = "valentine";
                int imageId = MainActivity.valentineFlower.get(i).getImageResourceId();
                int count = MainActivity.valentineCount[i];
                int cost = Integer.parseInt(MainActivity.valentineFlower.get(i).getCost()) * count;
                MainActivity.bucketCost += cost;
                flowerBasket.add(new Flower(name, category, imageId, cost , count));
                is = true;
            }
        }

        if(is){
            view.findViewById(R.id.place_order_button).setVisibility(View.VISIBLE);
            ListView listView = view.findViewById(R.id.basket_list_view);
            BasketAdapter adapter = new BasketAdapter(getActivity(), flowerBasket);
            listView.setAdapter(adapter);

            view.findViewById(R.id.place_order_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.host_container , PlaceOrder.class , null)
                            .commit();

                }
            });

        }else{
            view.findViewById(R.id.place_order_button).setVisibility(View.GONE);
        }

        view.findViewById(R.id.clear_all_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.bucketCost = 0;
                for(int i = 0 ; i < 6 ; i++){
                    MainActivity.weddingCount[i] = 0;
                    MainActivity.birthdayCount[i] = 0;
                    MainActivity.valentineCount[i] = 0;

                }
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.host_container , HomeFragment.class , null)
                        .commit();
            }
        });

    }
}