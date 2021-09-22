package com.rss.rss.Dashboard.Courses;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.rss.rss.MainActivity;
import com.rss.rss.Model.course.Course;
import com.rss.rss.Model.course.CourseResponse;
import com.rss.rss.R;
import com.rss.rss.adapter.course.CourseAdapter;
import com.rss.rss.network.ApiInterface;
import com.rss.rss.network.RetrofitApiClient;
import com.rss.rss.utils.Session;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VideoCoursesFragment extends Fragment {

    private static final String TAG = "CoursesHomeFragment";
    private static final String ARG_TYPE = "type";

    //var
    private String type;

    //WIDGET
    private RecyclerView coursesRV;
    private ProgressBar progressBar;

    public VideoCoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(ARG_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_courses, container, false);
        
        initWidget(view);
        
        return view;
    }

    private void initWidget(View view) {

        initToolbar();

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        coursesRV = view.findViewById(R.id.coursesRV);
        coursesRV.setLayoutManager(new LinearLayoutManager(getContext()));

        getData();

    }

    private void getData() {

        progressBar.setVisibility(View.VISIBLE);

        Session session = new Session(getContext());

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", String.valueOf(session.getAuthToken()));

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Call<CourseResponse> call = apiInterface.getAdvanceCourse(map);
        call.enqueue(new Callback<CourseResponse>() {
            @Override
            public void onResponse(Call<CourseResponse> call, Response<CourseResponse> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    CourseResponse result = response.body();
                    assert result != null;
                    if (result.getData() != null){
                        CourseAdapter adapter = new CourseAdapter(result.getData(),getContext());
                        Log.d(TAG, "onResponse: "+result.getData().get(0).getCourse_parts());
                        coursesRV.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<CourseResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void initToolbar(){

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Our Courses");

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
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                ((AppCompatActivity)getActivity()).finish();
            }
        });
    }

    public void pushFragment(Fragment fragment) {

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rootLayout, fragment).addToBackStack(null);
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(null);
    }
}