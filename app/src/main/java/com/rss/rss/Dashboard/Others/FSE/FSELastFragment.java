package com.rss.rss.Dashboard.Others.FSE;

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

public class FSELastFragment extends Fragment {

    private static final String TAG = "FSELastFragment";

    //WIDGET
    private Button btnOK;

    public FSELastFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_f_s_e_last, container, false);

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