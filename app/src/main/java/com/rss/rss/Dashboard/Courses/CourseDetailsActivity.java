package com.rss.rss.Dashboard.Courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;
import com.rss.rss.Model.course.Course;
import com.rss.rss.Model.course.CourseParts;
import com.rss.rss.R;
import com.rss.rss.adapter.course.TopicListAdapter;

import java.util.List;

public class CourseDetailsActivity extends AppCompatActivity {

    private static final String TAG = "CourseDetailsActivity";

    //Widget
    //WIDGET
    private ExtendedFloatingActionButton btnBuy;
    private TextView courseTitle;
    private TextView bestSeller;
    private ImageView courseImage;
    private WebView courseDescription;
    private RecyclerView topicRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        initWidget();
    }

    private void initWidget() {
        initToolbar();

        btnBuy = findViewById(R.id.btnBuy);
        courseTitle = findViewById(R.id.courseTitle);
        bestSeller = findViewById(R.id.bestSeller);
        courseImage = findViewById(R.id.courseImage);
        courseDescription = findViewById(R.id.courseDescription);

        topicRV = findViewById(R.id.topicRV);
        topicRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        setValue();
    }

    private Course getCourse(){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = prefs.getString("course", "");
        Course obj = gson.fromJson(json, Course.class);

        return obj;
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

            Glide.with(getApplicationContext()).load(imgURL).apply(options)
                    .into(courseImage);


            List<CourseParts> topicList = getCourse().getCourse_parts();
            if (topicList != null){
                intiTopicRV(topicList);
            }
        }
    }

    private void intiTopicRV(List<CourseParts> topicList) {
        TopicListAdapter adapter = new TopicListAdapter(topicList,getApplicationContext());
        topicRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Course Details");

        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back_arrow_white);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform whatever you want on back arrow click
                Intent intent = new Intent(getApplicationContext(), CoursesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }
}