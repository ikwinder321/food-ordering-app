package com.example.android.foodorderingapp;

import android.content.Context;
import android.content.SharedPreferences;
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


public class UserDetail extends Fragment {

    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFERENCE_FILE = "shared_preference_file";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFERENCE_FILE , Context.MODE_PRIVATE);

        EditText temp = view.findViewById(R.id.update_user_input_pincode);
        temp.setText(sharedPreferences.getString("userPincode" , "110061"));

        temp = view.findViewById(R.id.update_user_input_address);
        temp.setText(sharedPreferences.getString("userAddress", "Hoshiarpur - 110061"));

        Button updateButton = view.findViewById(R.id.account_update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pincode check
                EditText editText = view.findViewById(R.id.update_user_input_pincode);
                String user_input = editText.getText().toString();
                if(user_input.isEmpty() || user_input.charAt(0) == '0' || user_input.length() != 6){
                    showToast(view, false, "Invalid pincode!!");
                    return;
                }

                // address check
                editText = view.findViewById(R.id.update_user_input_address);
                user_input = editText.getText().toString();
                if(user_input.isEmpty() || user_input.length() > 120){
                    showToast(view, false, "Invalid address!!");
                    return;
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editText = view.findViewById(R.id.update_user_input_pincode);
                editor.putString("userPincode" , editText.getText().toString());
                editText = view.findViewById(R.id.update_user_input_address);
                editor.putString("userAddress" , editText.getText().toString());
                editor.apply();
                showToast(view, true, "Details updated successfully!!");

                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.host_container, HomeFragment.class , null)
                        .commit();

            }
        });

    }
    private void showToast(View view, boolean feel, String message){
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.custom_toast, (ViewGroup)view.findViewById(R.id.toast_root));
        TextView toastText = layout.findViewById(R.id.toast_message);
        ImageView toastImage = layout.findViewById(R.id.toast_image);
        toastText.setText(message);
        if(feel){
            toastImage.setImageResource(R.drawable.happy_mood);
        }else{
            toastImage.setImageResource(R.drawable.sad_mood);
        }
        Toast toast= new Toast(getContext());
        toast.setGravity(Gravity.CENTER, 0 , 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }
}