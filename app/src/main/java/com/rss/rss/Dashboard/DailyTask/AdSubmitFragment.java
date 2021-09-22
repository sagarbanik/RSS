package com.rss.rss.Dashboard.DailyTask;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.rss.rss.MainActivity;
import com.rss.rss.R;

public class AdSubmitFragment extends Fragment {

    private static final String TAG = "AdSubmitFragment";
    
    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "video_id";
    private static final String ARG_PARAM3 = "ad_type";

    
    private String title;
    private String video_id;
    private String ad_type;

    //Widget
    private YouTubePlayerView youtube_player_view;
    private LinearLayout proofLayout;

    public AdSubmitFragment() {
        // Required empty public constructor
    }
    
    public static AdSubmitFragment newInstance(String title, String video_id, String ad_type) {
        AdSubmitFragment fragment = new AdSubmitFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        args.putString(ARG_PARAM2, video_id);
        args.putString(ARG_PARAM3, ad_type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
            video_id = getArguments().getString(ARG_PARAM2);
            ad_type = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ad_submit, container, false);
        
        initWidget(view);
        
        return view;
    }

    private void initWidget(View view) {

        initToolbar();

        proofLayout = view.findViewById(R.id.proofLayout);
        proofLayout.setVisibility(View.GONE);

        if (ad_type.equals("video")){
            proofLayout.setVisibility(View.GONE);
        }else {
            proofLayout.setVisibility(View.VISIBLE);
        }

        youtube_player_view = view.findViewById(R.id.youtube_player_view);
        youtube_player_view.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                //String videoId = "S0Q4gqBUs7c";
                youTubePlayer.loadVideo(video_id, 0);
            }
        });

    }

    private void initToolbar(){

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Daily Ad");

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
}