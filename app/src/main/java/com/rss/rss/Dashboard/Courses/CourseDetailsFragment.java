package com.rss.rss.Dashboard.Courses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;
import com.rss.rss.MainActivity;
import com.rss.rss.Model.course.Course;
import com.rss.rss.Model.course.CourseParts;
import com.rss.rss.R;
import com.rss.rss.adapter.course.TopicListAdapter;

import java.util.List;
import java.util.Objects;


public class CourseDetailsFragment extends Fragment {

    private static final String TAG = "CourseDetailsFragment";

    //WIDGET
    private ExtendedFloatingActionButton btnBuy;
    private TextView courseTitle;
    private TextView bestSeller;
    private ImageView courseImage;
    private WebView courseDescription;
    private RecyclerView topicRV;

    public CourseDetailsFragment() {
        // Required empty public constructor
    }

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_details, container, false);
    
        initWidgets(view);
        
        return view;
    }

    private void initWidgets(View view) {

        initToolbar();

        btnBuy = view.findViewById(R.id.btnBuy);
        courseTitle = view.findViewById(R.id.courseTitle);
        bestSeller = view.findViewById(R.id.bestSeller);
        courseImage = view.findViewById(R.id.courseImage);
        courseDescription = view.findViewById(R.id.courseDescription);

        topicRV = view.findViewById(R.id.topicRV);
        topicRV.setLayoutManager(new LinearLayoutManager(getContext()));

        setValue();



    }

    private void setValue() {

        if (getCourse() != null){
            Course course = getCourse();

            courseTitle.setText(course.getName());
            courseDescription.setBackgroundColor(getResources().getColor(R.color.white_grey));
            //courseDescription.setBackgroundResource(getResources().getColor(R.color.white_grey));
            courseDescription.loadDataWithBaseURL(null,course.getDescription(),"text/html","utf-8",null);
            String imgURL = "https://rssint.com/rss/public/assets/course/"+course.getFile_name();
            Log.d(TAG, "onBindViewHolder: "+imgURL);
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.courses)
                    .error(R.drawable.courses);

            Glide.with(getContext()).load(imgURL).apply(options)
                    .into(courseImage);


            List<CourseParts> topicList = getCourse().getCourse_parts();
            if (topicList != null){
                intiTopicRV(topicList);
            }
        }

    }

    private void intiTopicRV(List<CourseParts> topicList) {
        TopicListAdapter adapter = new TopicListAdapter(topicList,getContext());
        topicRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initToolbar(){

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Course Details");

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
                //Intent intent = new Intent(getContext(), CoursesActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                //startActivity(intent);
                ((AppCompatActivity)getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Course getCourse(){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = prefs.getString("course", "");
        Course obj = gson.fromJson(json, Course.class);

        return obj;
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