package com.rss.rss.adapter.affiliate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rss.rss.Model.affiliate.FeatureTopic;
import com.rss.rss.R;

import java.util.List;

public class FeatureTopicAdapter extends RecyclerView.Adapter<FeatureTopicAdapter.ViewHolder> {

    private static final String TAG = "FeatureCategoryAdapter";

    private List<FeatureTopic> featureTopicList;
    private Context context;

    public FeatureTopicAdapter(List<FeatureTopic> featureTopicList, Context context) {
        this.featureTopicList = featureTopicList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feature_topic,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FeatureTopic featureCategory = featureTopicList.get(position);

        holder.catTitle.setText(featureCategory.getName());

        String imgURL = featureCategory.getImage();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar);

        Glide.with(holder.itemView.getContext()).load(imgURL).apply(options).into(holder.icon);

    }

    @Override
    public int getItemCount() {
        return featureTopicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView icon;
        private TextView catTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            catTitle = itemView.findViewById(R.id.catTitle);
        }
    }
}
