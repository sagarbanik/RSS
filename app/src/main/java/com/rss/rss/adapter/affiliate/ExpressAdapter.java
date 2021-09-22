package com.rss.rss.adapter.affiliate;

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
import com.rss.rss.Model.affiliate.Express;
import com.rss.rss.R;

import java.util.List;

public class ExpressAdapter extends RecyclerView.Adapter<ExpressAdapter.MyViewHolder> {


    private static final String TAG = "ExpressAdapter";

    private List<Express> expressList;
    private Context context;

    public ExpressAdapter(List<Express> expressList, Context context) {
        this.expressList = expressList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_express,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Express express = expressList.get(position);

        holder.expressName.setText(express.getTitle());

        String imgURL = express.getImage();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar);

        Glide.with(holder.itemView.getContext()).load(imgURL).apply(options).into(holder.expressImage);

    }

    @Override
    public int getItemCount() {
        return expressList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView expressImage;
        private TextView expressName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            expressName = itemView.findViewById(R.id.expressName);
            expressImage = itemView.findViewById(R.id.expressImage);

        }
    }
}
