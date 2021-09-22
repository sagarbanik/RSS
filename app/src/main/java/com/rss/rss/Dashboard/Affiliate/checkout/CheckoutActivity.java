package com.rss.rss.Dashboard.Affiliate.checkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rss.rss.Dashboard.Affiliate.checkout.dialog.DialogShippingAddress;
import com.rss.rss.Dashboard.Affiliate.shopping_cart.ShoppingCartActivity;
import com.rss.rss.Dashboard.Login.LoginActivity;
import com.rss.rss.Model.affiliate.Cart;
import com.rss.rss.Model.affiliate.Product;
import com.rss.rss.Model.affiliate.ShippingAddress;
import com.rss.rss.R;
import com.rss.rss.adapter.affiliate.checkout.ShippingAddressAdapter;
import com.rss.rss.room.DataDao;
import com.rss.rss.room.RoomDB;
import com.rss.rss.utils.Session;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {

    private static final String TAG = "CheckoutActivity";

    //Variables
    private float sum = 0.0f;
    private List<Cart> cartItemList = new ArrayList<>();


    //Widgets
    //RecyclerView for showing saved address
    private RecyclerView addressRV;
    //Button for adding new address
    private MaterialButton btnAdd;
    private RadioGroup radioButtonGroup;
    private RadioButton checkboxForCashOnDelivery;
    private RadioButton checkboxForBkash;
    private RadioButton checkboxForCard;
    private TextView numberOfCartItem;
    private TextView totalAmount;
    private MaterialButton placeOrder;
    private TextInputEditText coupon_edit_text;
    private TextInputEditText phone_edit_text;
    private TextInputLayout phone_text_input;
    private ProgressBar progressBar;

    private RelativeLayout rootLayout;

    //Variable
    private String paymentMethod = null;

    //Room persistent database
    private RoomDB database;
    public static DataDao dataDao;

    public static ShippingAddressAdapter addressAdapter;
    public static List<ShippingAddress> addressList;
    public static boolean isAddressClicked = false;
    public static int addressPosition = -1;
    private ShippingAddress address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        sum = getIntent().getFloatExtra("totalAmount",0.0f);
        database = Room.databaseBuilder(CheckoutActivity.this,RoomDB.class,"super_finix").allowMainThreadQueries().build();
        dataDao = database.dataDao();

       rootLayout = findViewById(R.id.rootLayout);


        Session session = new Session(getApplicationContext());
        if (!session.getLoginStatus()){
            Snackbar.make(CheckoutActivity.this.findViewById(android.R.id.content), "You are not logged in!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Login now", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(new Intent(getApplicationContext(), LoginActivity.class));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent);
                        }
                    })
                    .setBackgroundTint(getResources().getColor(R.color.black))
                    .setActionTextColor(getResources().getColor(R.color.white))
                    .show();
        }else {
            initWidget();
        }

    }

    private void initWidget() {
        initToolbar();

        addressRV = findViewById(R.id.addressRV);
        addressRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addressList = dataDao.getAddressList();
        if (dataDao.getAddressList() != null){
            addressAdapter = new ShippingAddressAdapter(getApplicationContext(),addressList);
            addressRV.setAdapter(addressAdapter);
            addressAdapter.notifyDataSetChanged();
        }

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        numberOfCartItem = findViewById(R.id.numberOfCartItem);
        totalAmount = findViewById(R.id.totalAmount);
        placeOrder = findViewById(R.id.placeOrder);
        coupon_edit_text = findViewById(R.id.coupon_edit_text);
        phone_edit_text = findViewById(R.id.phone_edit_text);
        phone_text_input = findViewById(R.id.phone_text_input);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        sum = sum+20.0f;
        totalAmount.setText("\u09F3"+sum);
        numberOfCartItem.setText("Total "+ Product.cartList.size()+" items in cart");

        radioButtonGroup = findViewById(R.id.radioButtonGroup);
        radioButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkboxForCashOnDelivery = (RadioButton) group.findViewById(R.id.checkboxForCashOnDelivery);
                checkboxForBkash = (RadioButton) group.findViewById(R.id.checkboxForBkash);
                checkboxForCard = (RadioButton) group.findViewById(R.id.checkboxForCard);

                switch (checkedId){
                    case R.id.checkboxForCashOnDelivery:
                        paymentMethod = checkboxForCashOnDelivery.getText().toString();
                        //Toast.makeText(getContext(), ""+paymentMethod, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.checkboxForBkash:
                        paymentMethod = checkboxForBkash.getText().toString();
                        //Toast.makeText(getContext(), ""+paymentMethod, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.checkboxForCard:
                        paymentMethod = checkboxForCard.getText().toString();
                        //Toast.makeText(getContext(), ""+paymentMethod, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        Session session = new Session(getApplicationContext());
        if (session.getLoginStatus()){
            placeOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isPhoneValid(phone_edit_text.getText())){
                        phone_text_input.setError("You must provide a valid phone number!");

                    }else {
                        phone_text_input.setError(null);

                        if (addressList != null){
                            if (isAddressClicked){
                                if (addressPosition != -1){
                                    address = addressList.get(addressPosition);
                                    sendData();
                                }
                            }else {
                                Toast.makeText(getApplicationContext(), "No  address is selected", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "Please add a delivery address", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }else {
        }
    }

    private void sendData() {
        /*Session session = new Session(getContext());
        Checkout checkout = null;

        for (Product product:Product.cartList){
            CartItem cartItem = new CartItem(product.getId(),product.getName(),product.getFinalCheckoutAmount(),String.valueOf(product.getFinalCheckoutQuantity()));
            cartItemList.add(cartItem);
        }
        if (!paymentMethod.equals("")){
            checkout = new Checkout(
                    String.valueOf(session.getUserID()),
                    String.valueOf(sum),
                    String.valueOf(Product.cartList.size()),
                    phone_edit_text.getText().toString(),
                    address.getTitle()+":"+address.getDetails(),
                    "empty"
                    ,paymentMethod,cartItemList);

            progressBar.setVisibility(View.VISIBLE);
            ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
            Call<CheckoutResponse> call = apiInterface.postCheckout(checkout);
            call.enqueue(new Callback<CheckoutResponse>() {
                @Override
                public void onResponse(Call<CheckoutResponse> call, Response<CheckoutResponse> response) {
                    if (response.isSuccessful()){
                        progressBar.setVisibility(View.GONE);
                        Log.d(TAG, "onResponse: "+response.body().toString());
                        if (response.body().isSuccess()){
                            Toast.makeText(getContext(), "Order placed successfully", Toast.LENGTH_SHORT).show();
                            Product.cartList.clear();
                            Intent intent = new Intent(getContext(),HomeActivity.class);
                            getActivity().startActivity(intent);
                        }
                    }
                }

                @Override
                public void onFailure(Call<CheckoutResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Log.d(TAG, "onFailure: "+t.getMessage());
                }
            });

        }else {
            Toast.makeText(getContext(), "Please select a payment method", Toast.LENGTH_SHORT).show();
        }

        if (paymentMethod != null){
            checkout = new Checkout(
                    String.valueOf(session.getUserID()),
                    String.valueOf(sum),
                    String.valueOf(Product.cartList.size()),
                    phone_edit_text.getText().toString(),
                    address.getTitle()+":"+address.getDetails(),
                    "not provided"
                    ,paymentMethod,cartItemList);

            progressBar.setVisibility(View.VISIBLE);
            ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
            Call<CheckoutResponse> call = apiInterface.postCheckout(checkout);
            call.enqueue(new Callback<CheckoutResponse>() {
                @Override
                public void onResponse(Call<CheckoutResponse> call, Response<CheckoutResponse> response) {
                    if (response.isSuccessful()){
                        progressBar.setVisibility(View.GONE);
                        Log.d(TAG, "onResponse: "+response.body().toString());
                        if (response.body().isSuccess()){
                            Toast.makeText(getContext(), "Order placed successfully", Toast.LENGTH_SHORT).show();
                            Product.cartList.clear();
                            Intent intent = new Intent(getContext(),HomeActivity.class);
                            getActivity().startActivity(intent);
                        }
                    }
                }

                @Override
                public void onFailure(Call<CheckoutResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Log.d(TAG, "onFailure: "+t.getMessage());
                }
            });

        }else {
            Toast.makeText(getContext(), "Please select a payment method", Toast.LENGTH_SHORT).show();
        }*/

        Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
    }

    private boolean isPhoneValid(Editable text) {
        return text != null && text.length() >= 11;
    }

    private void openDialog() {
        DialogShippingAddress exampleDialog = new DialogShippingAddress();
        exampleDialog.show(((AppCompatActivity) getApplicationContext()).getSupportFragmentManager(), "dialog");
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Checkout");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckoutActivity.this, ShoppingCartActivity.class));
            }
        });
    }
}