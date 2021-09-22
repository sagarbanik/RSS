package com.rss.rss.Dashboard.Services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.rss.rss.Dashboard.Others.OthersActivity;
import com.rss.rss.MainActivity;
import com.rss.rss.R;

public class ServicesActivity extends AppCompatActivity {

    private static final String TAG = "ServicesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        initWidget();

    }

    private void initWidget() {
        pushFragment(new ServiceFirstFragment());
    }

    public void pushFragment( Fragment fragment) {

        if (fragment == null)
            return;
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.rootLayout, fragment);
                ft.commit();
            }
        }
    }
}