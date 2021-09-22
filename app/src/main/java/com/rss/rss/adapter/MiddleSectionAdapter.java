package com.rss.rss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rss.rss.Model.MiddleSection;
import com.rss.rss.R;

import java.util.List;

public class MiddleSectionAdapter extends RecyclerView.Adapter<MiddleSectionAdapter.MyViewHolder> {

    private static final String TAG = "MiddleSectionAdapter";

    private List<MiddleSection> sectionList;
    private Context context;

    public MiddleSectionAdapter(List<MiddleSection> sectionList, Context context) {
        this.sectionList = sectionList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_middle_section,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MiddleSection section = sectionList.get(position);

        String imgURL = section.getIconUrl();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_eco)
                .error(R.drawable.ic_eco);

        Glide.with(holder.itemView.getContext()).load(imgURL).apply(options).into(holder.icon);

        holder.caption.setText(section.getCaption());

    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView caption;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            caption = itemView.findViewById(R.id.caption);

        }
    }
}
