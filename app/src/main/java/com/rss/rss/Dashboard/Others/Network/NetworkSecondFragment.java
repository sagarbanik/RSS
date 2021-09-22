package com.rss.rss.Dashboard.Others.Network;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rss.rss.R;

import java.util.Objects;

public class NetworkSecondFragment extends Fragment {

    private static final String TAG = "NetworkSecondFragment";
    
    //WIDGET
    

    public NetworkSecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_network_second, container, false);
        
        initWidget(view);
        
        return view;
    }

    private void initWidget(View view) {
        Objects.requireNonNull(((AppCompatActivity)
                Objects.requireNonNull(
                        getActivity())).getSupportActionBar()).setTitle("Other's|Individual Information");
    }
}