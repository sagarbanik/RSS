package com.rss.rss.ui.profile.earning_status;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.rss.rss.R;

public class EarningStatusActivity extends AppCompatActivity {

    private static final String TAG = "EarningStatusActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning_status);

        pushFragment(new EarningStatusFragment());

    }

    public void pushFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rootLayout, fragment);
        fragmentTransaction.commit();
    }

}