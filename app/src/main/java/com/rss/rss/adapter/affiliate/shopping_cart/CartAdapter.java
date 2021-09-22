package com.rss.rss.adapter.affiliate.shopping_cart;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rss.rss.Dashboard.Affiliate.shopping_cart.ShoppingCartActivity;
import com.rss.rss.Model.affiliate.Product;
import com.rss.rss.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{

    private static final String TAG = "HomeAdapter";

    private List<Product> productList;
    private Context context;

    int quantity = 0;

    public CartAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final Product product = productList.get(position);
        quantity = product.getFinalCheckoutQuantity();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+quantity);
            }
        });

        String imgURL = product.getImage();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar);

        Glide.with(holder.itemView.getContext()).load(imgURL).apply(options)
                .into(holder.productImage);

        holder.productName.setText(product.getName());
        holder.productUnitWithMeasurement.setText("375"+"gm");

        if (product.getPercent_off() > 0.0f){
            holder.productUnitPrice.setText("\u09F3"+product.getPromotion_price());
            holder.totalAmount.setText("Total: "+"\u09F3"+product.getPromotion_price()*product.getFinalCheckoutQuantity());
        }else {
            holder.productUnitPrice.setText("\u09F3"+product.getPrice());
            holder.totalAmount.setText("Total: "+"\u09F3"+product.getPrice()*product.getFinalCheckoutQuantity());
        }


        holder.counter.setText(""+product.getFinalCheckoutQuantity());

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                holder.counter.setText(""+quantity);

                product.setFinalCheckoutAmount(product.getPrice()*quantity);
                product.setFinalCheckoutQuantity(quantity);
                holder.totalAmount.setText("Total: "+"\u09F3"+product.getPrice()*quantity);
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.getFinalCheckoutQuantity()>0){
                    quantity = product.getFinalCheckoutQuantity()-1;
                    holder.counter.setText(""+quantity);

                    product.setFinalCheckoutAmount(product.getPrice()*quantity);
                    product.setFinalCheckoutQuantity(quantity);
                    holder.totalAmount.setText("Total: "+"\u09F3"+product.getPrice()*quantity);

                }else if (quantity<=0){
                    //holder.minus.setVisibility(View.GONE);
                    //holder.counter.setVisibility(View.GONE);

                    product.setFinalCheckoutAmount(product.getPrice()*quantity);
                    product.setFinalCheckoutQuantity(quantity);

                    removeItem(product);
                    Toast.makeText(context, "Item removed from the cart", Toast.LENGTH_SHORT).show();

                    //context.startActivity(new Intent(context, HomeActivity.class));
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(product);

                if (Product.cartList.size() <= 0 ){
                    ShoppingCartActivity.btnProceedCheckout.setVisibility(View.GONE);
                    ShoppingCartActivity.emptyCart.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void removeItem(Product product) {
        int position = productList.indexOf(product);
        productList.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public float getSubTotalBalance(){
        float sum = 0.0f;
        for (int i=0;i<Product.cartList.size();i++){
            float bal = Product.cartList.get(i).getFinalCheckoutAmount();
            sum = bal+sum;
        }
        return sum;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productName;
        private TextView productUnitWithMeasurement;
        private TextView productUnitPrice;
        private ImageView plus;
        private ImageView minus;
        private ImageView delete;
        private TextView counter;
        private TextView totalAmount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productUnitWithMeasurement = itemView.findViewById(R.id.productUnitWithMeasurement);
            productUnitPrice = itemView.findViewById(R.id.productUnitPrice);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            counter = itemView.findViewById(R.id.counter);
            delete = itemView.findViewById(R.id.delete);
            totalAmount = itemView.findViewById(R.id.totalAmount);

        }
    }
}
