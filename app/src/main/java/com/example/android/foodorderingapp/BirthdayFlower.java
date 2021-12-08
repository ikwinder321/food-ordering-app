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


public class BirthdayFlower extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_birthday_flower, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView birthdayListView = view.findViewById(R.id.birthday_list);
        FlowerAdapter birthdayFlowerAdapter = new FlowerAdapter(getActivity() , MainActivity.birthdayFlower);
        birthdayListView.setAdapter(birthdayFlowerAdapter);
    }
}