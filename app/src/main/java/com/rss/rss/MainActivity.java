package com.rss.rss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rss.rss.ui.about.AboutFragment;
import com.rss.rss.ui.home.HomeFragment;
import com.rss.rss.ui.home.ViewPagerAdapter;
import com.rss.rss.ui.profile.ProfileFragment;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //app exit
    private int count = 0;

    //WIDGET
    //private BottomNavigationView navView;
    private ViewPager view_pager;
    private MenuItem prevMenuItem;

    //Meow
    private MeowBottomNavigation bottom_navigation;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bottom_navigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_profile));
        bottom_navigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_about_us));

        bottom_navigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment = new ProfileFragment();
                        break;
                    case 3:
                        fragment = new AboutFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });

        bottom_navigation.show(1,true);

        bottom_navigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(MainActivity.this, "Clicked : "+item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        bottom_navigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(MainActivity.this, "Reselected : "+item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        view_pager = findViewById(R.id.view_pager);

        initViewPager(view_pager);
        //initBottomNav();

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

    /*private void initBottomNav() {
        navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        view_pager.setCurrentItem(0);
                        break;
                    case R.id.navigation_profile:
                        //pushFragment(new MessageFragment());
                        view_pager.setCurrentItem(1);
                        break;
                    case R.id.navigation_about:
                        view_pager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

        //Objects.requireNonNull(getSupportActionBar()).hide();
    }*/

    private void initViewPager(ViewPager view_pager) {

        adapter = new ViewPagerAdapter(getSupportFragmentManager(),this);
        view_pager.setAdapter(adapter);


        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottom_navigation.show(position,true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /*public void pushFragment( Fragment fragment) {
        if (fragment == null)
            return;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (ft != null) {
            ft.replace(R.id.rootLayout, fragment);
            ft.commit();
        }
    }*/

    @Override
    public void onBackPressed() {
        //check condition
        if (count == 2){
            finishAffinity();
            System.exit(0);
        }else if (count == 1){
            Toast.makeText(this, "Please click again to exit", Toast.LENGTH_SHORT).show();
        }


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                count = 0;
            }
        };

        new Handler().postDelayed(runnable,2000);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.d(TAG, "back button pressed");
            count++;
        }
        return super.onKeyDown(keyCode, event);
    }
}