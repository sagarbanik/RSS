package com.rss.rss.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.rss.rss.R;

public class ShoppingActivity extends AppCompatActivity {

    private static final String TAG = "ShoppingActivity";

    private AppBarConfiguration mAppBarConfiguration;

    //WIDGET
    private Toolbar toolbar;
    private NavigationView navigationView;
    public static TextView userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_shop,
                R.id.nav_wishlist
                //R.id.nav_statistics,
                //R.id.nav_rules,
                //R.id.nav_feedback,
                //R.id.nav_social,
                //R.id.nav_about,
                //R.id.nav_privacy_policy,
                //R.id.nav_sign_out
        )
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //setUpNavigationHeader();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.affiliate_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }




    /*private void setUpNavigationHeader(){
        View hView =  navigationView.getHeaderView(0);

        ImageView loginImage = hView.findViewById(R.id.loginImage);
        TextView loginText = hView.findViewById(R.id.loginText);
        userName = hView.findViewById(R.id.userName);

        Session session = new Session(MainActivity.this);
        if (session.getLoginStatus()){
            userName.setText(session.getUserName());
        }else {
            userName.setText("Guest");
        }




        loginImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.getLoginStatus()){
                    Toast.makeText(MainActivity.this, "আপনি ইতিমধ্যে লগইন করেছেন", Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }

            }
        });
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.getLoginStatus()){
                    Toast.makeText(MainActivity.this, "আপনি ইতিমধ্যে লগইন করেছেন", Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        });

    }
    }*/
}