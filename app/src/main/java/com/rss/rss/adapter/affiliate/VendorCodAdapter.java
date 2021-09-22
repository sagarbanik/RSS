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
import com.rss.rss.Model.affiliate.VendorCod;
import com.rss.rss.R;

import java.util.List;

public class VendorCodAdapter extends RecyclerView.Adapter<VendorCodAdapter.MyViewHolder> {


    private static final String TAG = "ExpressAdapter";

    private List<VendorCod> VendorCodList;
    private Context context;

    public VendorCodAdapter(List<VendorCod> VendorCodList, Context context) {
        this.VendorCodList = VendorCodList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vendor_cod,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        VendorCod vendorCod = VendorCodList.get(position);

        holder.vendorName.setText(vendorCod.getTitle());

        String imgURL = vendorCod.getImage();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar);

        Glide.with(holder.itemView.getContext()).load(imgURL).apply(options).into(holder.vendorImage);

    }

    @Override
    public int getItemCount() {
        return VendorCodList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView vendorImage;
        private TextView vendorName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            vendorName = itemView.findViewById(R.id.vendorName);
            vendorImage = itemView.findViewById(R.id.vendorImage);

        }
    }
}
