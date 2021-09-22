package com.rss.rss.ui.about.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rss.rss.ui.about.fragment.Profile1Fragment;
import com.rss.rss.ui.about.fragment.Profile2Fragment;
import com.rss.rss.ui.about.fragment.Profile3Fragment;
import com.rss.rss.ui.about.fragment.Profile4Fragment;
import com.rss.rss.ui.about.fragment.Profile5Fragment;

import java.util.ArrayList;
import java.util.List;

public class CompanyProfilePagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "CompanyProfilePagerAdap";

    //Var
    private Context context;
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public CompanyProfilePagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


        /*if (position == 0){
            return new Profile1Fragment();
        }else if (position == 1){
            return new Profile2Fragment();
        }else if (position == 2){
            return new Profile3Fragment();
        }else if (position == 3){
            return new Profile4Fragment();
        }else if (position == 4){
            return new Profile5Fragment();
        }*/

        /*switch (position) {
            case 0:
                return new Profile1Fragment();
            case 1:
                return new Profile2Fragment();
            case 2:
                return new Profile3Fragment();
            case 3:
                return new Profile4Fragment();
            case 4:
                return new Profile5Fragment();
        }*/

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

}
