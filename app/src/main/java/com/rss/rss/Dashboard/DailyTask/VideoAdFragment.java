package com.rss.rss.Dashboard.DailyTask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rss.rss.MainActivity;
import com.rss.rss.Model.load_ad.Ad;
import com.rss.rss.Model.load_ad.SingleAdResponse;
import com.rss.rss.R;
import com.rss.rss.adapter.ad.AdAdapter;
import com.rss.rss.network.ApiInterface;
import com.rss.rss.network.RetrofitApiClient;
import com.rss.rss.prefs.StoreAdListPrefs;
import com.rss.rss.utils.Session;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VideoAdFragment extends Fragment {

    private static final String TAG = "VideoAdFragment";
    private static final String ARG_VIDEO_ID = "video_id";
    public static final String STORE_FILE_NAME = "daily_ad_list";

    // TODO: Rename and change types of parameters
    private String video_id;
    private List<Ad> adList = new ArrayList<>();
    private RecyclerView adRV;

    //Widget
    private View item1;

    public VideoAdFragment() {
        // Required empty public constructor
    }
    
    public static VideoAdFragment newInstance(String video_id) {
        VideoAdFragment fragment = new VideoAdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_VIDEO_ID, video_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            video_id = getArguments().getString(ARG_VIDEO_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_ad, container, false);
        
        initWidget(view);
        
        return view;
    }

    private void initWidget(View view) {

        initToolbar();

        adRV = view.findViewById(R.id.adRV);

        List<Integer> integerList = getList();
        Log.d(TAG, "initWidget: "+integerList.toString());

        if (integerList != null && integerList.size() >0){
            for (int i=0; i<integerList.size();i++){

                getAds(integerList.get(i));
            }
        }



    }

    private void getAds(int i) {

        Session session = new Session(getContext());
        if (session.getLoginStatus()){

            Map<String, String> map = new HashMap<>();
            map.put("Authorization", String.valueOf(session.getAuthToken()));
            map.put("userId",String.valueOf(session.getUserID()));

            ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
            Call<SingleAdResponse> call = apiInterface.getSingleAd(map,i);
            call.enqueue(new Callback<SingleAdResponse>() {
                @Override
                public void onResponse(Call<SingleAdResponse> call, Response<SingleAdResponse> response) {
                    Log.d(TAG, "onResponse: "+response);
                    Log.d(TAG, "onResponse: "+response.body());
                    Ad ad = response.body().getData();
                    adList.add(ad);
                    if (adList != null && adList.size()>0){
                        Log.d(TAG, "initWidget: "+adList.toString());
                        adRV.setLayoutManager(new GridLayoutManager(getContext(),2));
                        AdAdapter adAdapter = new AdAdapter(getContext(),adList);
                        adRV.setAdapter(adAdapter);
                    }
                }

                @Override
                public void onFailure(Call<SingleAdResponse> call, Throwable t) {
                    Log.d(TAG, "onFailure: "+t.getMessage());
                }
            });


        }

    }

    public void pushFragment(Fragment fragment) {

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rootLayout, fragment);
        fragmentTransaction.commit();
    }

    private void initToolbar(){

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Daily Task|Video");

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back_arrow_white);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(upArrow);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform whatever you want on back arrow click
                Intent intent = new Intent(getContext(), DailyAdActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                ((AppCompatActivity)getActivity()).finish();
            }
        });
    }

    public List<Integer> getList(){
        List<Integer> integerList = new ArrayList<>();
        SharedPreferences prefs = getContext().getSharedPreferences(STORE_FILE_NAME, Context.MODE_PRIVATE);
        String serializedObject = prefs.getString(STORE_FILE_NAME, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Integer>>(){}.getType();
            integerList = gson.fromJson(serializedObject, type);
        }
        return integerList;
    }

}