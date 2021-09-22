package com.rss.rss.Dashboard.Affiliate.shopping_cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rss.rss.Dashboard.Affiliate.AffiliateActivity;
import com.rss.rss.Dashboard.Affiliate.MultipleProductActivity;
import com.rss.rss.Dashboard.Affiliate.bottom_sheet.CheckoutBottomSheet;
import com.rss.rss.Model.affiliate.Product;
import com.rss.rss.R;
import com.rss.rss.adapter.affiliate.shopping_cart.CartAdapter;

import java.util.Objects;

public class ShoppingCartActivity extends AppCompatActivity {

    private static final String TAG = "ShoppingCartActivity";

    //Widgets
    private RecyclerView cartRV;
    public static RelativeLayout emptyCart;
    private MaterialButton btnShop;
    public static FloatingActionButton btnProceedCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        initToolbar();
        initWidget();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_back_arrow_white);
        toolbar.setTitle("Shopping Cart");
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShoppingCartActivity.this, AffiliateActivity.class));
            }
        });
    }

    private void initWidget() {

        btnProceedCheckout = findViewById(R.id.btnProceedCheckout);
        btnProceedCheckout.setVisibility(View.GONE);

        emptyCart = findViewById(R.id.emptyCart);
        emptyCart.setVisibility(View.GONE);

        cartRV = findViewById(R.id.cartRV);
        cartRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        btnShop = findViewById(R.id.btnShop);
        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MultipleProductActivity.class);
                intent.putExtra("flag",5);
                intent.putExtra("tag","empty cart");
                startActivity(intent);
            }
        });

        btnProceedCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckoutBottomSheet bottomSheet = new CheckoutBottomSheet();
                assert getFragmentManager() != null;
                bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
            }
        });

        if (Product.cartList.size() <= 0 ){
            emptyCart.setVisibility(View.VISIBLE);
            btnProceedCheckout.setVisibility(View.GONE);
        }else {
            emptyCart.setVisibility(View.GONE);
            btnProceedCheckout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        CartAdapter cartItemAdapter = new CartAdapter(Product.cartList,ShoppingCartActivity.this);
        cartRV.setAdapter(cartItemAdapter);
        cartItemAdapter.notifyDataSetChanged();
    }
}