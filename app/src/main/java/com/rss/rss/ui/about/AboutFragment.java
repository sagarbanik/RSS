package com.rss.rss.ui.about;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.rss.rss.R;
import com.rss.rss.ui.about.adapter.CompanyProfilePagerAdapter;
import com.rss.rss.ui.about.fragment.Profile1Fragment;
import com.rss.rss.ui.about.fragment.Profile2Fragment;
import com.rss.rss.ui.about.fragment.Profile3Fragment;
import com.rss.rss.ui.about.fragment.Profile4Fragment;
import com.rss.rss.ui.about.fragment.Profile5Fragment;

public class AboutFragment extends Fragment {

    //Widget
    private ViewPager view_pager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_about, container, false);

        /*Intent intent = new Intent(getContext(),AboutActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/

        view_pager = root.findViewById(R.id.view_pager);

        CompanyProfilePagerAdapter adapter = new CompanyProfilePagerAdapter(getChildFragmentManager(),getContext());
        adapter.addFragment(new Profile1Fragment());
        adapter.addFragment(new Profile2Fragment());
        adapter.addFragment(new Profile3Fragment());
        adapter.addFragment(new Profile4Fragment());
        adapter.addFragment(new Profile5Fragment());
        view_pager.setAdapter(adapter);

        return root;
    }
}