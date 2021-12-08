package com.example.android.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.android.foodorderingapp.R;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFERENCE_FILE = "shared_preference_file";
    static int bucketCost;


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;


    final static ArrayList<Flower> birthdayFlower = new ArrayList<>();
    final static ArrayList<Flower> weddingFlower = new ArrayList<>();
    final static ArrayList<Flower> valentineFlower = new ArrayList<>();

    static ArrayList<Order> last_orders = new ArrayList<>();

    static int[] birthdayCount = new int[] {0 , 0 , 0 , 0 , 0 , 0};
    static int[] weddingCount = new int[] {0 , 0 , 0 , 0 , 0 , 0};
    static int[] valentineCount = new int[] {0 , 0 , 0 , 0 , 0 , 0};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bucketCost = 0;

        // getting shared preferences file
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_FILE , Context.MODE_PRIVATE);

        // creating toolbar, with hamburger button
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_drawer);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout , toolbar , R.string.open , R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // setting navigation header text
        View view = navigationView.getHeaderView(0);
        TextView nameInDrawer = view.findViewById(R.id.navigation_drawer_text);
        nameInDrawer.setText("Hi, " + sharedPreferences.getString("userName" , "Saurav"));

        // navigation , on menu item click
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.close();
                switch (item.getItemId()){
                    case R.id.menu_item_basket:
                        replaceFragment(1);
                        break;
                    case R.id.menu_item_placed_orders:
                        replaceFragment(2);
                        break;
                    case R.id.menu_item_user_detail:
                        replaceFragment(3);
                        break;
                    case R.id.menu_item_about_us:
                        replaceFragment(4);
                        break;
                    case R.id.menu_item_logout:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.apply();
                        startActivity(new Intent(getApplicationContext() , SplashScreen.class));
                        finish();
                    default: return false;
                }
                return true;
            }
        });

        weddingFlower.add(new Flower("American Fresh" , "wedding" , R.drawable.breakfast_1, 7, 0));
        weddingFlower.add(new Flower("Mexican Duoto" , "wedding" , R.drawable.breakfast_2, 5, 0));
        weddingFlower.add(new Flower("German Pie" , "wedding" , R.drawable.breakfast_3, 5, 0));
        weddingFlower.add(new Flower("Australian Toasts" , "wedding" , R.drawable.breakfast_4, 8, 0));
        weddingFlower.add(new Flower("Russian Beans" , "wedding" , R.drawable.breakfast_5, 4, 0));
        weddingFlower.add(new Flower("Indian Omelet" , "wedding" , R.drawable.breakfast_6, 6, 0));


        birthdayFlower.add(new Flower("Roti Roles" , "birthday" , R.drawable.lunch_1, 4, 0));
        birthdayFlower.add(new Flower("Burger & Fries" , "birthday" , R.drawable.lunch_2, 7, 0));
        birthdayFlower.add(new Flower("Sub Bread" , "birthday" , R.drawable.lunch_3, 6, 0));
        birthdayFlower.add(new Flower("Aalo Poori" , "birthday" , R.drawable.lunch_4, 7, 0));
        birthdayFlower.add(new Flower("Salad" , "birthday" , R.drawable.lunch_5, 5, 0));
        birthdayFlower.add(new Flower("Corn Soup" , "birthday" , R.drawable.lunch_6, 4, 0));

        valentineFlower.add(new Flower("Bean Onion" , "valentine" , R.drawable.dinner_1, 6, 0));
        valentineFlower.add(new Flower("Paneer Crisps" , "valentine" , R.drawable.dinner_2, 10, 0));
        valentineFlower.add(new Flower("Mexican Fato" , "valentine" , R.drawable.dinner_3, 8, 0));
        valentineFlower.add(new Flower("Soya Yato" , "valentine" , R.drawable.dinner_4, 6, 0));
        valentineFlower.add(new Flower("Egg Chunks" , "valentine" , R.drawable.dinner_5, 8, 0));
        valentineFlower.add(new Flower("Potato Balls" , "valentine" , R.drawable.dinner_6, 6, 0));

        replaceFragment(0);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("last_orders" , null);
        Type type = new TypeToken<ArrayList<Order>>(){}.getType();
        last_orders = gson.fromJson(json, type);
        if(last_orders == null){
            last_orders = new ArrayList<Order>();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(last_orders);
        editor.putString("last_orders" , json);
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.option_menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.go_to_home){
            replaceFragment(0);
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(int idx){
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.host_container , idx == 0? HomeFragment.class : (idx == 1? Basket.class : (idx == 2 ? PlacedOrders.class : (idx == 3 ? UserDetail.class : AboutUs.class))) , null)
                .commit();
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isOpen()){
            drawerLayout.close();
        }
        moveTaskToBack(true);
    }

}