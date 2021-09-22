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
import com.rss.rss.Model.affiliate.Brand;
import com.rss.rss.Model.affiliate.Category;
import com.rss.rss.R;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {

    private static final String TAG = "FeatureCategoryAdapter";

    private List<Brand> brandList;
    private Context context;

    public BrandAdapter(List<Brand> brandList, Context context) {
        this.brandList = brandList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Brand brand = brandList.get(position);

        holder.catTitle.setText(brand.getName());

        String imgURL = brand.getImage();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ad_post_icon)
                .error(R.drawable.ad_post_icon);

        Glide.with(holder.itemView.getContext()).load(imgURL).apply(options).into(holder.icon);

    }

    @Override
    public int getItemCount() {
        return brandList.size();
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
