package com.rss.rss.adapter.course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rss.rss.Model.course.CourseParts;
import com.rss.rss.R;

import java.util.List;

public class TopicListAdapter extends RecyclerView.Adapter<TopicListAdapter.MyViewHolder> {

    private static final String TAG = "TopicListAdapter";

    private List<CourseParts> topicList;
    private Context context;
    private int serial = 0;

    public TopicListAdapter(List<CourseParts> topicList, Context context) {
        this.topicList = topicList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_topic,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CourseParts topic = topicList.get(position);
        serial = position+1;

        holder.topicName.setText(topic.getName());
        holder.topicDuration.setText("Duration: "+topic.getDuration());
        holder.itemPosition.setText(""+serial);
        holder.videoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView itemPosition;
        private TextView topicName;
        private TextView topicDuration;
        private ImageView videoIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            videoIcon = itemView.findViewById(R.id.videoIcon);
            itemPosition = itemView.findViewById(R.id.itemPosition);
            topicName = itemView.findViewById(R.id.topicName);
            topicDuration = itemView.findViewById(R.id.topicDuration);
        }
    }
}
