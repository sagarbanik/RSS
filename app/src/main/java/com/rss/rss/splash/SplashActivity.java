package com.rss.rss.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.rss.rss.Dashboard.Login.LoginActivity;
import com.rss.rss.MainActivity;
import com.rss.rss.R;
import com.rss.rss.utils.InternetChecker;
import com.rss.rss.utils.Session;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    //WIDGET
    private ImageView logo;
    private TextView appTitle;
    private TextView appSlogan;
    private MaterialButton btnRetry;
    private TextView tvCheck;
    private ProgressBar progressBar;

    //Animation
    Animation topAnimation,middleAnimation,bottomAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        middleAnimation = AnimationUtils.loadAnimation(this,R.anim.middle_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        logo = findViewById(R.id.logo);
        appTitle = findViewById(R.id.appTitle);
        appSlogan = findViewById(R.id.appSlogan);

        logo.setAnimation(topAnimation);
        appTitle.setAnimation(middleAnimation);
        appSlogan.setAnimation(bottomAnimation);

        btnRetry = findViewById(R.id.btnRetry);
        btnRetry.setVisibility(View.GONE);
        tvCheck = findViewById(R.id.tvCheck);
        tvCheck.setVisibility(View.GONE);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRetry.setVisibility(View.GONE);
                tvCheck.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        InternetChecker checker = new InternetChecker(getApplicationContext());
                        if (checker.hasActiveInternetConnection(getApplicationContext())){
                            progressBar.setVisibility(View.GONE);
                            Session session = new Session(getApplicationContext());

                            if (session.getRememberMe()){
                                /* Create an Intent that will start the Menu-Activity. */
                                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(mainIntent);
                                finish();
                            }else {
                                /* Create an Intent that will start the Menu-Activity. */
                                Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                                startActivity(mainIntent);
                                finish();
                            }
                        }else {
                            tvCheck.setVisibility(View.VISIBLE);
                            btnRetry.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(SplashActivity.this, "Check your internet connection.", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, 3000);
            }
        });

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                InternetChecker checker = new InternetChecker(getApplicationContext());
                if (checker.hasActiveInternetConnection(getApplicationContext())){
                    Session session = new Session(getApplicationContext());

                    if (session.getRememberMe()){
                        /* Create an Intent that will start the Menu-Activity. */
                        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }else {
                        /* Create an Intent that will start the Menu-Activity. */
                        Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }else {
                    tvCheck.setVisibility(View.VISIBLE);
                    btnRetry.setVisibility(View.VISIBLE);
                    Toast.makeText(SplashActivity.this, "Check your internet connection.", Toast.LENGTH_SHORT).show();

                }

            }
        }, 2500);

    }
}