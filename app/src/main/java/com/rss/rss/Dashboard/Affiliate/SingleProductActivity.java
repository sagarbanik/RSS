package com.rss.rss.Dashboard.Affiliate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.rss.rss.Dashboard.Affiliate.shopping_cart.ShoppingCartActivity;
import com.rss.rss.MainActivity;
import com.rss.rss.Model.affiliate.Product;
import com.rss.rss.R;
import com.rss.rss.adapter.affiliate.RelatedProductAdapter;
import com.rss.rss.network.ApiInterface;
import com.rss.rss.network.RetrofitApiClient;
import com.rss.rss.room.DataDao;
import com.rss.rss.room.RoomDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleProductActivity extends AppCompatActivity {

    private static final String TAG = "SingleProductActivity";

    //Widget
    private Toolbar toolbar;

    private ImageView productImage;
    private TextView productName;
    private TextView productPrice;
    private TextView productDiscountPrice;
    private TextView productWeight;
    private TextView productDescription;
    private RecyclerView relatedProductRv;
    private MaterialButton addToCart;
    private ProgressBar progressBar;

    private ImageView plus;
    private ImageView minus;
    private TextView counter;

    //View Cart Layout
    public static RelativeLayout cartViewLayout;
    public static TextView item_number;
    public static TextView btnViewCart;
    public static TextView totalAmount;

    //Room persistent database
    private RoomDB database;
    public static DataDao dataDao;

    //Variable
    private int quantity = 0;

    //Parameters
    private int p_id;
    private String p_name;
    private String p_desc;
    private String p_image;
    private float p_price;
    private String uom;
    private String unit;
    private float price_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Product Details");
        setSupportActionBar(toolbar);
        //toolbar.setTitle("Product Details");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleProductActivity.this, MainActivity.class));
            }
        });

        initWidget();

        getIntentData();


    }

    private void initWidget() {
        database = Room.databaseBuilder(SingleProductActivity.this,RoomDB.class,"urmart_db").allowMainThreadQueries().build();
        dataDao = database.dataDao();

        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        productDiscountPrice = findViewById(R.id.productDiscountPrice);
        productWeight = findViewById(R.id.productWeight);
        productDescription = findViewById(R.id.productDescription);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        initViewCartLayout();

        relatedProductRv = findViewById(R.id.relatedProductRv);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        relatedProductRv.setLayoutManager(manager);

        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        counter = findViewById(R.id.counter);
        addToCart = findViewById(R.id.addToCart);

        plus.setVisibility(View.GONE);
        minus.setVisibility(View.GONE);
        counter.setVisibility(View.GONE);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Product> result = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    result = Product.cartList.stream()
                            .filter(a -> Objects.equals(a.getId(), p_id))
                            .collect(Collectors.toList());
                }

                if (result!= null && result.size() > 0){
                    Toast.makeText(getApplicationContext(), "Product already added to the bag", Toast.LENGTH_SHORT).show();
                }else {
                    //notificationCountCart++;
                    Product productItem = new Product(p_id,p_name,p_price,p_image,1);
                    Product.cartList.add(productItem);
                    Toast.makeText(getApplicationContext(), "Item added to the bag", Toast.LENGTH_SHORT).show();
                }

                showCardViewLayout(Product.cartList);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity>0){
                    quantity = quantity-1;
                    counter.setText(""+quantity);
                }else if (quantity<=0){
                    minus.setVisibility(View.GONE);
                    counter.setVisibility(View.GONE);
                }

                showCardViewLayout(Product.cartList);
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Product> result = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    result = Product.cartList.stream()
                            .filter(a -> Objects.equals(a.getId(), p_id))
                            .collect(Collectors.toList());
                }

                if (result!= null && result.size() > 0){
                    Toast.makeText(getApplicationContext(), "Product already added to the bag", Toast.LENGTH_SHORT).show();
                }else {
                    if (price_off > 0.0f){
                       // notificationCountCart++;
                        Product product = new Product(p_id,p_name,price_off,p_image,1);
                        Product.cartList.add(product);
                        Toast.makeText(getApplicationContext(), "Item added to the bag", Toast.LENGTH_SHORT).show();
                    }else {
                       // notificationCountCart++;
                        Product product = new Product(p_id,p_name,p_price,p_image,1);
                        Product.cartList.add(product);
                        Toast.makeText(getApplicationContext(), "Item added to the bag", Toast.LENGTH_SHORT).show();
                    }
                }

                showCardViewLayout(Product.cartList);
            }
        });

        initRelatedProduct();
    }

    private void getIntentData(){
        Intent intent = getIntent();
        p_id = intent.getIntExtra("p_id",0);
        p_name = intent.getStringExtra("p_name");
        p_desc = intent.getStringExtra("p_desc");
        p_image = intent.getStringExtra("p_image");
        uom = intent.getStringExtra("uom");
        unit = intent.getStringExtra("unit");
        price_off = intent.getFloatExtra("price_off",0.0f);
        p_price = intent.getFloatExtra("p_price",0.0f);

        Log.d(TAG, "getIntentData: p_id: "+p_id);
        Log.d(TAG, "getIntentData: p_name: "+p_name);
        Log.d(TAG, "getIntentData: p_desc: "+p_desc);
        Log.d(TAG, "getIntentData: p_image: "+p_image);
        Log.d(TAG, "getIntentData: uom: " +uom);
        Log.d(TAG, "getIntentData: unit: " +unit);
        Log.d(TAG, "getIntentData: price_off: "+price_off);
        Log.d(TAG, "getIntentData: p_price: "+p_price);
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
                Intent intent = new Intent(getApplicationContext(), ShoppingCartActivity.class);
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

    private void setDataToWidget() {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar);

        Glide.with(SingleProductActivity.this).load(p_image).apply(options).into(productImage);

        productName.setText(p_name);

        productWeight.setText(unit+" "+uom);
        productDescription.setText(stripHtml(p_desc));

        if (price_off > 0.0f){

            productPrice.setText("\u09F3"+price_off);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                productDiscountPrice.setText(Html.fromHtml("<del> &#2547;"+ p_price + "</del>", Html.FROM_HTML_MODE_COMPACT));
            } else {
                productDiscountPrice.setText(Html.fromHtml("<del> &#2547;"+ p_price + "</del>"));
            }
        }else {
            productPrice.setText("\u09F3"+p_price);
            productDiscountPrice.setVisibility(View.GONE);
        }
    }

    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }

    private void initRelatedProduct() {

        SharedPreferences mPrefs =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = mPrefs.getString("product", "");
        Product obj = gson.fromJson(json, Product.class);

        /*if (obj != null){
            progressBar.setVisibility(View.VISIBLE);
            ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
            Call<RelatedProductResponse> call = apiInterface.getRelatedProduct(obj);
            call.enqueue(new Callback<RelatedProductResponse>() {
                @Override
                public void onResponse(Call<RelatedProductResponse> call, Response<RelatedProductResponse> response) {
                    if (response.isSuccessful()){
                        progressBar.setVisibility(View.GONE);
                        List<Product> productList = response.body().getProducts();
                        if (productList != null){
                            RelatedProductAdapter adapter = new RelatedProductAdapter(productList,getContext());
                            relatedProductRv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<RelatedProductResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Log.d(TAG, "onFailure: "+t.getMessage());
                }
            });
        }*/

        List<Product> productList = new ArrayList<>();

        productList.add(new Product(
                1,1,1,"pc","1","A511","Best Watch For Men ","product_1",
                150.0f,1,20.0f,120.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                2,1,1,"pc","1","A512","T-Shirt For Men ","product_2",
                250.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://strausskhan.com/wp-content/uploads/2021/04/adbe1a6b-6c37-488f-86b1-111767216bdd.jpg",
                "https://strausskhan.com/wp-content/uploads/2021/04/adbe1a6b-6c37-488f-86b1-111767216bdd.jpg",
                "https://strausskhan.com/wp-content/uploads/2021/04/adbe1a6b-6c37-488f-86b1-111767216bdd.jpg",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                3,1,1,"pc","1","A513","Vintage T-Shirt For Men ","product_3",
                350.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://i.pinimg.com/236x/3f/33/c2/3f33c2850434f50ec5c4ac3748abbd5c.jpg",
                "https://i.pinimg.com/236x/3f/33/c2/3f33c2850434f50ec5c4ac3748abbd5c.jpg",
                "https://i.pinimg.com/236x/3f/33/c2/3f33c2850434f50ec5c4ac3748abbd5c.jpg",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                4,1,1,"pc","1","A514","Cool Lighter rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr ","product_4",
                450.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/582635/pexels-photo-582635.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/582635/pexels-photo-582635.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/582635/pexels-photo-582635.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                5,1,1,"pc","1","A515","Pure Black Coffee ","product_5",
                550.0f,1,20.0f,450.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://media.istockphoto.com/photos/mug-on-plate-filled-with-coffee-surrounded-by-coffee-beans-picture-id157528129?k=6&m=157528129&s=612x612&w=0&h=-Jm8OkpkDbTHIAXLuXaZ1_VUsz8_0B9okYWQJdgvnpI=",
                "https://media.istockphoto.com/photos/mug-on-plate-filled-with-coffee-surrounded-by-coffee-beans-picture-id157528129?k=6&m=157528129&s=612x612&w=0&h=-Jm8OkpkDbTHIAXLuXaZ1_VUsz8_0B9okYWQJdgvnpI=",
                "https://media.istockphoto.com/photos/mug-on-plate-filled-with-coffee-surrounded-by-coffee-beans-picture-id157528129?k=6&m=157528129&s=612x612&w=0&h=-Jm8OkpkDbTHIAXLuXaZ1_VUsz8_0B9okYWQJdgvnpI=",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                6,1,1,"pc","1","A516","Watch ","product_6",
                650.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                100.0f,"test"
        )) ;

        RelatedProductAdapter adapter = new RelatedProductAdapter(productList,SingleProductActivity.this);
        relatedProductRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setDataToWidget();
    }

    public void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (ft != null) {
            ft.replace(R.id.rootLayout, fragment);
            ft.commit();
        }
    }

}