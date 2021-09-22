package com.rss.rss.Dashboard.Others.Network;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rss.rss.R;

import java.util.Objects;

public class NetworkFirstFragment extends Fragment {

    private static final String TAG = "NetworkFirstFragment";

    //WIDGET
    private LinearLayout middle;

    public NetworkFirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_network_first, container, false);

        initWidget(view);

        return view;
    }

    private void initWidget(View view) {
        Objects.requireNonNull(((AppCompatActivity)
                Objects.requireNonNull(
                        getActivity())).getSupportActionBar()).setTitle("Other's|Downline Network");

        middle = view.findViewById(R.id.middle);
        middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new NetworkSecondFragment());
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