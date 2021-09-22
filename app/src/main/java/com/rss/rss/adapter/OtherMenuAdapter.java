package com.rss.rss.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rss.rss.Dashboard.Others.OthersActivity;
import com.rss.rss.Model.OtherMenu;
import com.rss.rss.R;

import java.util.List;

public class OtherMenuAdapter extends RecyclerView.Adapter<OtherMenuAdapter.MyViewHolder> {

    private static final String TAG = "OtherMenuAdapter";

    private List<OtherMenu> otherMenuList;
    private Context context;

    public OtherMenuAdapter(List<OtherMenu> otherMenuList, Context context) {
        this.otherMenuList = otherMenuList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_others_menu,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        OtherMenu otherMenu = otherMenuList.get(position);

        String imgURL = otherMenu.getImagePath();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.others_section_icon)
                .error(R.drawable.others_section_icon);

        Glide.with(holder.itemView.getContext()).load(imgURL).apply(options).into(holder.icon);

        holder.title.setText(otherMenu.getTitle());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                switch (holder.getAdapterPosition()){
                    case 0:
                        intent = new Intent(context, OthersActivity.class);
                        intent.putExtra("open","JOB");
                        context.startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(context, OthersActivity.class);
                        intent.putExtra("open","FSE");
                        context.startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(context, OthersActivity.class);
                        intent.putExtra("open","QUERY");
                        context.startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return otherMenuList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);

        }
    }
}
