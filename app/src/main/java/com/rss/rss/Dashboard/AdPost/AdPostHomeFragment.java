package com.rss.rss.Dashboard.AdPost;

import static java.lang.Math.abs;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.rss.rss.Dashboard.AdPost.CarPromotion.CarPromoFirstFragment;
import com.rss.rss.Dashboard.AdPost.EmailPromotion.EmailPromoFirstFragment;
import com.rss.rss.Dashboard.AdPost.ProductPromotion.ProductPromotionFirstFragment;
import com.rss.rss.Dashboard.AdPost.SmsPromotion.SmsPromoFirstFragment;
import com.rss.rss.Dashboard.AdPost.SocialMediaPromotion.SMPFirstFragment;
import com.rss.rss.Dashboard.AdPost.WebsitePromotion.WebsiteFirstPromotionFragment;
import com.rss.rss.MainActivity;
import com.rss.rss.R;

public class AdPostHomeFragment extends Fragment {

    private static final String TAG = "AdPostHomeFragment";

    //WIDGET
    private LinearLayout btnProductPromotion;
    private LinearLayout btnWebsitePromotion;
    private LinearLayout btnSocialMediaPromotion;
    private LinearLayout btnCarAdvertising;
    private LinearLayout btnEmailMarketing;
    private LinearLayout btnSmsMarketing;

    public AdPostHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ad_post_home, container, false);

        initWidget(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getContext()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getContext()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppBarLayout appbar = view.findViewById(R.id.appbar);
        CollapsingToolbarLayout collapsingToolbar = view.findViewById(R.id.collapsingToolbar);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //Check if the view is collapsed
                if (abs(verticalOffset) >= appbar.getTotalScrollRange()) {
                    collapsingToolbar.setTitle("Ad post | Home");
                    collapsingToolbar.setExpandedTitleColor(Color.WHITE);

                } else {
                    collapsingToolbar.setTitle("");
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private void initWidget(View view) {

        btnSocialMediaPromotion = view.findViewById(R.id.btnSocialMediaPromotion);
        btnWebsitePromotion = view.findViewById(R.id.btnWebsitePromotion);
        btnProductPromotion = view.findViewById(R.id.btnProductPromotion);
        btnCarAdvertising = view.findViewById(R.id.btnCarAdvertising);
        btnEmailMarketing = view.findViewById(R.id.btnEmailMarketing);
        btnSmsMarketing = view.findViewById(R.id.btnSmsMarketing);

        btnProductPromotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new ProductPromotionFirstFragment());
            }
        });

        btnWebsitePromotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new WebsiteFirstPromotionFragment());
            }
        });

        btnSocialMediaPromotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new SMPFirstFragment());
            }
        });

        btnCarAdvertising.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new CarPromoFirstFragment());
            }
        });

        btnEmailMarketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new EmailPromoFirstFragment());
            }
        });

        btnSmsMarketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new SmsPromoFirstFragment());
            }
        });

    }

    public void pushFragment(Fragment fragment) {

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rootLayout, fragment);
        fragmentTransaction.commit();
    }
}