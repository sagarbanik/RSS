package com.rss.rss.Dashboard.AdPost.SocialMediaPromotion;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rss.rss.Dashboard.AdPost.AdPostActivity;
import com.rss.rss.R;


public class SMPSecondFragment extends Fragment {

    private static final String TAG = "SMPSecondFragment";

    public SMPSecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_s_m_p_second, container, false);

        initWidget(view);

        return view;
    }

    private void initWidget(View view) {

        Button btnNext = view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AdPostActivity.class));
            }
        });
    }
}