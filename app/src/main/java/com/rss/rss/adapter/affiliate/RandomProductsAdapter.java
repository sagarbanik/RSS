package com.rss.rss.adapter.affiliate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import com.google.gson.Gson;
import com.rss.rss.Dashboard.Affiliate.SingleProductActivity;
import com.rss.rss.Model.affiliate.Product;
import com.rss.rss.R;

import java.util.List;

public class RandomProductsAdapter extends RecyclerView.Adapter<RandomProductsAdapter.ViewHolder> {

    private static final String TAG = "ProductsAdapter";

    private List<Product> randomProductList;
    private Context context;

    public RandomProductsAdapter(List<Product> randomProductList, Context context) {
        this.randomProductList = randomProductList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item__rand_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Product product = randomProductList.get(position);

        holder.productName.setText(product.getName());
        holder.productPrice.setText("\u09F3"+product.getPrice());

        String imgURL = product.getImage();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar);

        Glide.with(holder.itemView.getContext()).load(imgURL).apply(options).into(holder.productImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int adapterPosition = holder.getAdapterPosition();

                Product products = getProducts(adapterPosition);

                saveProductToFindRelatedProduct(products);

                Intent intent = new Intent(context, SingleProductActivity.class);
                intent.putExtra("p_id",products.getId());
                intent.putExtra("p_name",products.getName());
                intent.putExtra("p_desc",products.getDescription());
                intent.putExtra("p_image",products.getImage());
                intent.putExtra("p_price",products.getPrice());
                intent.putExtra("uom",products.getUom_id());
                intent.putExtra("unit",products.getUnit());
                intent.putExtra("price_off",products.getPromotion_price());

                Log.d(TAG, "p_id: "+products.getId());
                Log.d(TAG, "p_name : " +products.getName());
                Log.d(TAG, "p_desc : "+products.getDescription());
                Log.d(TAG, "p_image : "+products.getImage());
                Log.d(TAG, "p_price : "+products.getPrice());
                Log.d(TAG, "uom : "+products.getUom_id());
                Log.d(TAG, "unit : "+products.getUnit());
                Log.d(TAG, "price_off : "+products.getPromotion_price());

                context.startActivity(intent);
            }
        });
    }

    private Product getProducts(int pos){
        return randomProductList.get(pos);
    }

    @Override
    public int getItemCount() {
        return randomProductList.size();
    }

    private void saveProductToFindRelatedProduct(Product product){
        SharedPreferences mPrefs =  PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(product);
        editor.putString("product", json);
        editor.apply();

        /*
        //Retrieve the data
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        MyObject obj = gson.fromJson(json, MyObject.class);*/
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView productName;
        private TextView productPrice;
        private ImageView productImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
        }
    }
}
