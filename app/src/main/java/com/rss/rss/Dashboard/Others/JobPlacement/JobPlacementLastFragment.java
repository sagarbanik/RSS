package com.rss.rss.Dashboard.Others.JobPlacement;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rss.rss.Dashboard.Others.OthersActivity;
import com.rss.rss.R;

public class JobPlacementLastFragment extends Fragment {

    private static final String TAG = "JobPlacementLastFragment";

    //WIDGET
    private Button btnOK;

    public JobPlacementLastFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_placement_last, container, false);

        initWidget(view);

        return view;
    }

    private void initWidget(View view) {

        btnOK = view.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pushFragment(new OthersFragment());
                getContext().startActivity(new Intent(getContext(),OthersActivity.class));
            }
        });
    }

}