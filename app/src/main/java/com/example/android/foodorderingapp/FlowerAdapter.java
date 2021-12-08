package com.example.android.foodorderingapp;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.foodorderingapp.R;

import java.util.ArrayList;

public class FlowerAdapter extends ArrayAdapter<Flower> {
    Activity activity;

    public FlowerAdapter(Activity context, ArrayList<Flower> flowers){
        super(context , 0 , flowers);
        activity = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item_for_home , parent, false);
        }
        Flower currentFlower = getItem(position);

        ImageView imageView = listItem.findViewById(R.id.home_icon);
        imageView.setImageResource(currentFlower.getImageResourceId());


        TextView flowerName = listItem.findViewById(R.id.home_flower_name);
        flowerName.setText(currentFlower.getName());

        TextView flowerCost = listItem.findViewById(R.id.home_flower_cost);
        flowerCost.setText("$" + currentFlower.getCost());

        imageView.setOnClickListener(v -> {
            CustomAlertDialog customAlertDialog = new CustomAlertDialog(getContext() , currentFlower);
            customAlertDialog.show();
        });

        TextView flowerCount = listItem.findViewById(R.id.home_flower_count);
        flowerCount.setText(String.valueOf(currentFlower.count));

        ImageButton minusButton = listItem.findViewById(R.id.home_minus_button);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentFlower.count == 0){
                    showToast(false, "Invalid input!!");
                }else{
                    currentFlower.count-=1;
                    flowerCount.setText(String.valueOf(currentFlower.count));
                }
            }
        });

        ImageButton plusButton = listItem.findViewById(R.id.home_plus_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentFlower.count == 5){
                    showToast(false, "Should not exceed 5");
                }else{
                    currentFlower.count += 1;
                    flowerCount.setText(String.valueOf(currentFlower.count));
                }
            }
        });

        ImageButton addToCart = listItem.findViewById(R.id.home_add_to_cart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentFlower.count == 0){
                    showToast(false, "Invalid input!!");
                }else {
                    if(currentFlower.getCategory().equals("wedding")){
                        MainActivity.weddingCount[position] = currentFlower.count;
                    }else if(currentFlower.getCategory().equals("birthday")){
                        MainActivity.birthdayCount[position] = currentFlower.count;
                    }else MainActivity.valentineCount[position] = currentFlower.count;

                    showToast(true, "Items added/updated successfully!!");
                }
            }
        });

        return listItem;

    }

    private void showToast(boolean feel, String message){
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.custom_toast, (ViewGroup)activity.findViewById(R.id.toast_root));
        TextView toastText = layout.findViewById(R.id.toast_message);
        ImageView toastImage = layout.findViewById(R.id.toast_image);
        toastText.setText(message);
        if(feel){
            toastImage.setImageResource(R.drawable.happy_mood);
        }else {
            toastImage.setImageResource(R.drawable.sad_mood);
        }

        Toast toast= new Toast(getContext());
        toast.setGravity(Gravity.CENTER, 0 , 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }
}
