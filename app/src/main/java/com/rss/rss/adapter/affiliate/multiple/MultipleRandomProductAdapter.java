package com.rss.rss.adapter.affiliate.multiple;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
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
import com.rss.rss.Dashboard.Affiliate.MultipleProductActivity;
import com.rss.rss.Dashboard.Affiliate.SingleProductActivity;
import com.rss.rss.Model.affiliate.Product;
import com.rss.rss.R;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class MultipleRandomProductAdapter extends RecyclerView.Adapter<MultipleRandomProductAdapter.ViewHolder> {

    private static final String TAG = "MultipleProductAdapter";

    private Context context;
    private List<Product> productList;

    public MultipleRandomProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_group,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Product product = productList.get(position);

        holder.productName.setText(product.getName());
        holder.productDescription.setText(stripHtml(product.getDescription()));
        holder.productPrice.setText("\u09F3"+product.getPrice());

        String imgURL = product.getImage();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar);

        Glide.with(holder.itemView.getContext()).load(imgURL).apply(options).into(holder.productImage);

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
                    Product productItem;
                    if (product.getPromotion_price() > 0.0f){
                        productItem = new Product(product.getId(), product.getName(), product.getPromotion_price(), product.getImage(), 1);
                    }else {
                        productItem = new Product(product.getId(), product.getName(), product.getPrice(), product.getImage(), 1);
                    }
                    Product.cartList.add(productItem);
                    Toast.makeText(context, "Item added to the bag", Toast.LENGTH_SHORT).show();
                }

                MultipleProductActivity.showCardViewLayout(Product.cartList);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();

                Product products = getProducts(adapterPosition);

                Intent intent = new Intent(context, SingleProductActivity.class);
                intent.putExtra("p_id",products.getId());
                intent.putExtra("p_name",products.getName());
                intent.putExtra("p_desc",products.getDescription());
                intent.putExtra("p_image",products.getImage());
                intent.putExtra("p_price",products.getPrice());
                intent.putExtra("uom",products.getUom_id());
                intent.putExtra("unit",products.getUnit());
                intent.putExtra("price_off",products.getPromotion_price());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });

    }

    private Product getProducts(int pos){
        return productList.get(pos);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public String stripHtml(String html) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(html).toString();
        }
    }

    public void filterList(List<Product> filteredList){
        productList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName;
        private TextView productDescription;
        private TextView productPrice;
        private ImageView plus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productDescription = itemView.findViewById(R.id.productDescription);
            productPrice = itemView.findViewById(R.id.productPrice);
            plus = itemView.findViewById(R.id.plus);

        }
    }
}
