package com.rss.rss.Dashboard.Others;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.rss.rss.Dashboard.Others.FSE.FSEFirstFragment;
import com.rss.rss.Dashboard.Others.JobPlacement.JobPlacementFragment;
import com.rss.rss.Dashboard.Others.Query.QueryFirstFragment;
import com.rss.rss.Model.other.FreelanceSupportEngineer;
import com.rss.rss.R;

public class OthersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);

        Intent intent = getIntent();
        if (intent != null && intent.getStringExtra("open").equals("FSE")){
            pushFragment(new FSEFirstFragment());
        }else if (intent != null && intent.getStringExtra("open").equals("JOB")){
            pushFragment(new JobPlacementFragment());
        }else if (intent != null && intent.getStringExtra("open").equals("QUERY")){
            pushFragment(new QueryFirstFragment());
        }
    }

    public void pushFragment( Fragment fragment) {

        if (fragment == null)
            return;

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.rootLayout, fragment);
                ft.commit();
            }
        }
    }
}