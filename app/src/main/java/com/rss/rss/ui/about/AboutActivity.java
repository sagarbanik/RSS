package com.rss.rss.ui.about;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.rss.rss.R;
import com.rss.rss.ui.about.adapter.CompanyProfilePagerAdapter;
import com.rss.rss.ui.about.fragment.Profile1Fragment;
import com.rss.rss.ui.about.fragment.Profile2Fragment;
import com.rss.rss.ui.about.fragment.Profile3Fragment;
import com.rss.rss.ui.about.fragment.Profile4Fragment;
import com.rss.rss.ui.about.fragment.Profile5Fragment;
import com.rss.rss.ui.home.HomeFragment;
import com.rss.rss.ui.home.ViewPagerAdapter;
import com.rss.rss.ui.profile.ProfileFragment;

public class AboutActivity extends AppCompatActivity {

    private static final String TAG = "AboutActivity";

    //Widget
    private ViewPager view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        view_pager = findViewById(R.id.view_pager);
        CompanyProfilePagerAdapter adapter = new CompanyProfilePagerAdapter(getSupportFragmentManager(),this);
        adapter.addFragment(new Profile1Fragment());
        adapter.addFragment(new Profile2Fragment());
        adapter.addFragment(new Profile3Fragment());
        adapter.addFragment(new Profile4Fragment());
        adapter.addFragment(new Profile5Fragment());
        view_pager.setAdapter(adapter);


    }
}