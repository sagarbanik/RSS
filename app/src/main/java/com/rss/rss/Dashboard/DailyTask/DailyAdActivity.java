package com.rss.rss.Dashboard.DailyTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.rss.rss.Model.load_ad.DailyAdResponse;
import com.rss.rss.R;
import com.rss.rss.network.ApiInterface;
import com.rss.rss.network.RetrofitApiClient;
import com.rss.rss.prefs.StoreAdListPrefs;
import com.rss.rss.utils.Session;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyAdActivity extends AppCompatActivity {

    private static final String TAG = "DailyAdActivity";

    public static final String STORE_FILE_NAME = "daily_ad_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_ad);

        loadTodayAd();


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

    private void saveAdListToPrefs(){

    }

    private void loadTodayAd(){

        Session  session = new Session(getApplicationContext());
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", String.valueOf(session.getAuthToken()));
        map.put("userId",String.valueOf(session.getUserID()));

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Call<DailyAdResponse> call = apiInterface.getDailyAdArray(map);
        call.enqueue(new Callback<DailyAdResponse>() {
            @Override
            public void onResponse(Call<DailyAdResponse> call, Response<DailyAdResponse> response) {
                Log.d(TAG, "onResponse: "+response);
                Log.d(TAG, "onResponse: "+response.body());
                pushFragment(new DailyAdHomeFragment());
                StoreAdListPrefs prefs = new StoreAdListPrefs(getApplicationContext());
                prefs.setList(STORE_FILE_NAME,response.body().getData());
                Log.d(TAG, "onResponse: Added Array");
            }

            @Override
            public void onFailure(Call<DailyAdResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    public class StoreData{

    }
}