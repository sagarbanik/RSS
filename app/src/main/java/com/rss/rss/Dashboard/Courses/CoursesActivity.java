package com.rss.rss.Dashboard.Courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.rss.rss.Dashboard.Courses.adapter.Pager;
import com.rss.rss.R;

import static java.lang.Math.abs;

public class CoursesActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private static final String TAG = "CoursesActivity";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppBarLayout appbar;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    private Point windowSize = new Point();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appbar = findViewById(R.id.appbar);
        collapsingToolbar = findViewById(R.id.collapsingToolbar);


        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //Check if the view is collapsed
                if (abs(verticalOffset) >= appbar.getTotalScrollRange()) {
                    collapsingToolbar.setTitle("Course");
                } else {
                    collapsingToolbar.setTitle("");
                }

                //tabLayout.setTranslationY(tabLayout.getTranslationY()+tabLayout.getTranslationY()* (verticalOffset / Float.parseFloat(String.valueOf(appBarLayout.getTotalScrollRange()))));

            }
        });

        //Initializing the tab layout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tabLayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    int scrollXxx = tabLayout.getScrollX(); // Current x scrolling position
                    // We know that we have at least one child

                    // Calculate the position if this window
                    getWindowManager().getDefaultDisplay().getSize(windowSize);
                    int maxScrollWidth = tabLayout.getChildAt(0).getMeasuredWidth() - windowSize.x;

                    v.setPivotY(scrollXxx);
                }
            });
        }
        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout.setupWithViewPager(viewPager);
        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager());
        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //Title bar back press triggers onBackPressed()
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ) {
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }

    public void pushFragment(Fragment fragment) {

        if (fragment == null)
            return;

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.rootLayout, fragment).addToBackStack(null);
                ft.commit();
                ft.addToBackStack(null);
            }
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}