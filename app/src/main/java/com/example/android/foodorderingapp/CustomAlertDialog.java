package com.example.android.foodorderingapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.android.foodorderingapp.R;

public class CustomAlertDialog extends Dialog  implements View.OnClickListener{
    public Activity activity;

    Flower mFlower;

    public CustomAlertDialog(@NonNull Context context , Flower flower) {
        super(context);
        this.activity = (Activity) context;
        mFlower = flower;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        ImageView imageView = findViewById(R.id.custom_alert_flower_icon);
        imageView.setImageResource(mFlower.getImageResourceId());
        TextView textView = findViewById(R.id.custom_alert_flower_name);
        textView.setText(mFlower.getName());
    }

    @Override
    public void onClick(View v) {

    }

}
