package com.rss.rss.Dashboard.Affiliate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rss.rss.Dashboard.Affiliate.shopping_cart.ShoppingCartActivity;
import com.rss.rss.Model.affiliate.Product;
import com.rss.rss.R;
import com.rss.rss.adapter.affiliate.multiple.MultipleProductAdapter;
import com.rss.rss.adapter.affiliate.multiple.MultipleRandomProductAdapter;
import com.rss.rss.room.DataDao;
import com.rss.rss.room.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class MultipleProductActivity extends AppCompatActivity {

    private static final String TAG = "MultipleProductActivity";

    //Widget
    private RecyclerView multipleProductRV;

    //View Cart Layout
    public static RelativeLayout cartViewLayout;
    public static TextView item_number;
    public static TextView btnViewCart;
    public static TextView totalAmount;

    //Room persistent database
    private RoomDB database;
    public static DataDao dataDao;

    //Filter data
    private EditText searchData;

    //Var
    private int flag;
    private String tag;

    //Products
    private List<Product> productList = new ArrayList<>();
    private List<Product> randomProductList = new ArrayList<>();
    private MultipleProductAdapter multipleProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_product);

        initToolbar();

        insertRandomProduct();
        //insertProduct();

        initViewCartLayout();

        multipleProductRV = findViewById(R.id.multipleProductRV);
        int numberOfColumns = 2;
        multipleProductRV.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        setupData();

        searchData = findViewById(R.id.searchData);
        searchData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Product List");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MultipleProductActivity.this,AffiliateActivity.class));
            }
        });
    }

    private void filter(String toString) {
        if (flag == 1 && randomProductList != null && randomProductList.size() > 0){
            List<Product> filteredList = new ArrayList<>();
            for (Product product: randomProductList){
                if (product.getName().toLowerCase().contains(toString.toLowerCase())){
                    filteredList.add(product);
                }
            }
            multipleProductAdapter.filterList(filteredList);
        }
    }

    private void setupData() {

        Intent intent = getIntent();
        if (intent != null){
            flag = intent.getIntExtra("flag",0);
            tag = intent.getStringExtra("tag");
        }

        switch (flag){
            case 1:
                loadProduct();
                break;
        }
    }

    private void loadProduct() {
        if (randomProductList != null) {
            multipleProductAdapter = new MultipleProductAdapter(getApplicationContext(),randomProductList);
            multipleProductRV.setAdapter(multipleProductAdapter);
            multipleProductAdapter.notifyDataSetChanged();
        }
    }

    private void initViewCartLayout() {
        cartViewLayout = findViewById(R.id.cartViewLayout);
        totalAmount = findViewById(R.id.totalAmount);
        cartViewLayout.setVisibility(View.GONE);
        item_number = findViewById(R.id.item_number);
        btnViewCart = findViewById(R.id.btnViewCart);
        btnViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Hello");
                //pushFragment(new MessageFragment());
                //HomeActivity.view_pager.setCurrentItem(2);
                Intent intent = new Intent(MultipleProductActivity.this, ShoppingCartActivity.class);
                intent.putExtra("from","SingleProduct");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public static void showCardViewLayout(List<Product> productList){

        float sum = 0.0f;
        if (productList != null){
            for (int i=0;i<productList.size();i++){
                float bal = productList.get(i).getPrice();
                int quantity = productList.get(i).getFinalCheckoutQuantity();
                sum = sum+bal*quantity;
                Log.d(TAG, "showCardViewLayout: "+sum);
            }
        }

        cartViewLayout.setVisibility(View.VISIBLE);
        item_number.setText(""+ Product.cartList.size());
        totalAmount.setText("\u09F3"+sum);
        hideCardViewLayout();
    }

    public static void hideCardViewLayout(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cartViewLayout.setVisibility(View.GONE);
            }
        }, 3500);
    }

    private void insertProduct(){
        productList.add(new Product(
                1,1,1,"pc","1","A501","Product1 ","product_1",
                150.0f,1,20.0f,120.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                2,1,1,"pc","1","A502","Product2 ","product_2",
                250.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                3,1,1,"pc","1","A503","Product3 ","product_3",
                1050.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                4,1,1,"pc","1","A504","Product4 ","product_4",
                1750.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                5,1,1,"pc","1","A505","Product5 ","product_5",
                2150.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                6,1,1,"pc","1","A506","Product6 ","product_6",
                1250.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                100.0f,"test"
        )) ;
    }

    public void insertRandomProduct(){
        randomProductList.add(new Product(
                1,1,1,"pc","1","A511","Best Watch For Men ","product_1",
                150.0f,1,20.0f,120.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                100.0f,"test"
        )) ;

        randomProductList.add(new Product(
                2,1,1,"pc","1","A512","T-Shirt For Men ","product_2",
                250.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://strausskhan.com/wp-content/uploads/2021/04/adbe1a6b-6c37-488f-86b1-111767216bdd.jpg",
                "https://strausskhan.com/wp-content/uploads/2021/04/adbe1a6b-6c37-488f-86b1-111767216bdd.jpg",
                "https://strausskhan.com/wp-content/uploads/2021/04/adbe1a6b-6c37-488f-86b1-111767216bdd.jpg",
                100.0f,"test"
        )) ;

        randomProductList.add(new Product(
                3,1,1,"pc","1","A513","Vintage T-Shirt For Men ","product_3",
                350.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://i.pinimg.com/236x/3f/33/c2/3f33c2850434f50ec5c4ac3748abbd5c.jpg",
                "https://i.pinimg.com/236x/3f/33/c2/3f33c2850434f50ec5c4ac3748abbd5c.jpg",
                "https://i.pinimg.com/236x/3f/33/c2/3f33c2850434f50ec5c4ac3748abbd5c.jpg",
                100.0f,"test"
        )) ;

        randomProductList.add(new Product(
                4,1,1,"pc","1","A514","Cool Lighter rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr ","product_4",
                450.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/582635/pexels-photo-582635.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/582635/pexels-photo-582635.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/582635/pexels-photo-582635.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                100.0f,"test"
        )) ;

        randomProductList.add(new Product(
                5,1,1,"pc","1","A515","Pure Black Coffee ","product_5",
                550.0f,1,20.0f,450.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://media.istockphoto.com/photos/mug-on-plate-filled-with-coffee-surrounded-by-coffee-beans-picture-id157528129?k=6&m=157528129&s=612x612&w=0&h=-Jm8OkpkDbTHIAXLuXaZ1_VUsz8_0B9okYWQJdgvnpI=",
                "https://media.istockphoto.com/photos/mug-on-plate-filled-with-coffee-surrounded-by-coffee-beans-picture-id157528129?k=6&m=157528129&s=612x612&w=0&h=-Jm8OkpkDbTHIAXLuXaZ1_VUsz8_0B9okYWQJdgvnpI=",
                "https://media.istockphoto.com/photos/mug-on-plate-filled-with-coffee-surrounded-by-coffee-beans-picture-id157528129?k=6&m=157528129&s=612x612&w=0&h=-Jm8OkpkDbTHIAXLuXaZ1_VUsz8_0B9okYWQJdgvnpI=",
                100.0f,"test"
        )) ;

        randomProductList.add(new Product(
                6,1,1,"pc","1","A516","Watch ","product_6",
                650.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                100.0f,"test"
        )) ;
    }
}