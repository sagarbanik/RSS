package com.rss.rss.adapter.course;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.rss.rss.Dashboard.Courses.CourseDetailsActivity;
import com.rss.rss.Dashboard.Courses.CourseDetailsFragment;
import com.rss.rss.Model.course.Course;
import com.rss.rss.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    private static final String TAG = "CourseAdapter";

    private List<Course> courseList;
    private Context context;

    public CourseAdapter(List<Course> courseList, Context context) {
        this.courseList = courseList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Course course = courseList.get(position);

        String imgURL = "https://rssint.com/rss/public/assets/course/"+course.getFile_name();
        Log.d(TAG, "onBindViewHolder: "+imgURL);
        Log.d(TAG, "onBindViewHolder: "+course.getFile_name());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.courses)
                .error(R.drawable.courses);

        Glide.with(holder.itemView.getContext()).load(imgURL).apply(options)
                .into(holder.courseImage);

        holder.courseTitle.setText(stripHtml(course.getName()));
        holder.courseDescription.setText(stripHtml(course.getDescription()));
        holder.coursePrice.setText("\u09F3"+course.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCourse(course);
                context.startActivity(new Intent(context, CourseDetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView courseImage;
        private TextView courseTitle;
        private TextView courseDescription;
        private RatingBar ratingBar;
        private TextView coursePrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            courseImage = itemView.findViewById(R.id.courseImage);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseDescription = itemView.findViewById(R.id.courseDescription);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            coursePrice = itemView.findViewById(R.id.coursePrice);

        }
    }

    public String stripHtml(String html) {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(html).toString();
        }*/
        return Html.fromHtml(html).toString();
    }

    private void saveCourse(Course courseToSave){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(courseToSave);
        editor.putString("course", json);
        editor.apply();

        //To retrieve:
        //Gson gson = new Gson();
        //String json = mPrefs.getString("MyObject", "");
        //MyObject obj = gson.fromJson(json, MyObject.class);

    }

    public void pushFragment( Fragment fragment) {
        if (fragment == null){
            return;
        }

        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (ft != null) {
            ft.replace(R.id.rootLayout, fragment);
            ft.commit();
        }
    }

}
