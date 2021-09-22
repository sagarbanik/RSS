package com.rss.rss.adapter.affiliate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.rss.rss.Dashboard.Affiliate.SingleProductActivity;
import com.rss.rss.Model.affiliate.Product;
import com.rss.rss.R;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.MyViewHolder> {

    private static final String TAG = "RelatedProductAdapter";

    private List<Product> productList;
    private Context context;

    public RelatedProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_related_product,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Product product = productList.get(position);

        String imgURL = product.getImage();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar);

        Glide.with(holder.itemView.getContext()).load(imgURL).apply(options)
                .into(holder.productImage);

        holder.productName.setText(product.getName());
        holder.productPrice.setText("\u09F3"+product.getPrice());
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                List<Product> result = Product.cartList.stream()
                        .filter(a -> Objects.equals(a.getId(), product.getId()))
                        .collect(Collectors.toList());

                if (result!= null && result.size() > 0){
                    Toast.makeText(context, "Product already added to the bag", Toast.LENGTH_SHORT).show();
                }else {
                    if (product.getPromotion_price() > 0.0f){
                        //notificationCountCart++;
                        Product productItem = new Product(product.getId(),product.getName(),product.getPromotion_price(),product.getImage(),1);
                        Product.cartList.add(productItem);
                        Toast.makeText(context, "Item added to the bag", Toast.LENGTH_SHORT).show();
                    }else {
                        //notificationCountCart++;
                        Product productItem = new Product(product.getId(),product.getName(),product.getPrice(),product.getImage(),1);
                        Product.cartList.add(productItem);
                        Toast.makeText(context, "Item added to the bag", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveProductToFindRelatedProduct(product);

                Intent intent = new Intent(context, SingleProductActivity.class);
                intent.putExtra("p_id",product.getId());
                intent.putExtra("p_name",product.getName());
                intent.putExtra("p_desc",product.getDescription());
                intent.putExtra("p_image",product.getImage());
                intent.putExtra("p_price",product.getPrice());
                intent.putExtra("uom",product.getUom_id());
                intent.putExtra("unit",product.getUnit());
                intent.putExtra("price_off",product.getPromotion_price());

                Log.d(TAG, "p_id: "+product.getId());
                Log.d(TAG, "p_name : " +product.getName());
                Log.d(TAG, "p_desc : "+product.getDescription());
                Log.d(TAG, "p_image : "+product.getImage());
                Log.d(TAG, "p_price : "+product.getPrice());
                Log.d(TAG, "uom : "+product.getUom_id());
                Log.d(TAG, "unit : "+product.getUnit());
                Log.d(TAG, "price_off : "+product.getPromotion_price());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName;
        private TextView productPrice;
        private ImageView plus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            plus = itemView.findViewById(R.id.plus);

        }
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
}

