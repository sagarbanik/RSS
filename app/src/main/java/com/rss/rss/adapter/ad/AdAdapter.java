package com.rss.rss.adapter.ad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.rss.rss.Dashboard.DailyTask.AdSubmitFragment;
import com.rss.rss.Model.load_ad.Ad;
import com.rss.rss.R;

import java.util.List;

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.MyViewHolder> {

    private static final String TAG = "AdAdapter";

    private Context context;
    private List<Ad> adList;

    public AdAdapter(Context context, List<Ad> adList) {
        this.context = context;
        this.adList = adList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ad,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Ad ad = adList.get(position);

        //holder.title.setText(ad.getName());

        if (ad.getType().equals("video")){
            holder.title.setText("Video Ad");
        }else {
            holder.title.setText("Social Ad");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = AdSubmitFragment.newInstance(ad.getName(),ad.getLink(),ad.getType());
                pushFragment(fragment);
            }
        });

    }

    @Override
    public int getItemCount() {
        return adList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);

        }
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
