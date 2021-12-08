package com.example.android.foodorderingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.foodorderingapp.R;


public class AboutUs extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button callButton = view.findViewById(R.id.contact_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8146848091"));
                startActivity(intent);
            }
        });

        Button feedbackButton = view.findViewById(R.id.feedback_button);
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = view.findViewById(R.id.feedback_input);
                String string = editText.getText().toString();
                if(string.isEmpty() || string.length() > 200){

                    LayoutInflater layoutInflater = getLayoutInflater();
                    View layout = layoutInflater.inflate(R.layout.custom_toast, (ViewGroup)view.findViewById(R.id.toast_root));
                    TextView toastText = layout.findViewById(R.id.toast_message);
                    ImageView toastImage = layout.findViewById(R.id.toast_image);

                    toastText.setText("Invalid, feedback!!");
                    toastImage.setImageResource(R.drawable.sad_mood);

                    Toast toast= new Toast(getContext());
                    toast.setGravity(Gravity.CENTER, 0 , 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }else{
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "mcknightconner555@gmail.com"));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback : Suggestion | Complain");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, editText.getText());
                    startActivity(Intent.createChooser(emailIntent, "Send email through"));
                }
            }
        });
    }
}