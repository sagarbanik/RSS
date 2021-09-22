package com.rss.rss.Dashboard.Services;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rss.rss.MainActivity;
import com.rss.rss.R;


public class ServiceSecondFragment extends Fragment {

    private static final String TAG = "ServiceSecondFragment";

    //Widget
    private Button btnOK;

    public ServiceSecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service_second, container, false);
        initWidget(view);
        return view;
    }

    private void initWidget(View view) {
        btnOK = view.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
    }
}