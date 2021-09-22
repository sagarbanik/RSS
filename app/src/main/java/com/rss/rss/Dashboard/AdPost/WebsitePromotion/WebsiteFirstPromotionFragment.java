package com.rss.rss.Dashboard.AdPost.WebsitePromotion;

import static java.lang.Math.abs;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rss.rss.Dashboard.AdPost.AdPostActivity;
import com.rss.rss.Dashboard.AdPost.ProductPromotion.ProductPromotionSecondFragment;
import com.rss.rss.MainActivity;
import com.rss.rss.Model.adpost.product_promotion.ProductPromotionPostResponse;
import com.rss.rss.Model.adpost.website_promotion.WebPromotionPostResponse;
import com.rss.rss.R;
import com.rss.rss.network.ApiInterface;
import com.rss.rss.network.RetrofitApiClient;
import com.rss.rss.utils.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WebsiteFirstPromotionFragment extends Fragment {

    private static final String TAG = "WebsitePromoFragment";

    //WIDGETS
    private TextInputEditText inputPdTitle;
    private TextInputEditText inputAdBudget;
    private TextInputEditText inputWebAddress;
    private TextInputEditText inputPhone;
    private TextInputEditText inputEmail;
    private TextInputEditText inputPdInfo;

    private TextInputLayout layoutInputPdName;
    private TextInputLayout layoutInputAdBudget;
    private TextInputLayout layoutInputWebAddress;
    private TextInputLayout layoutInputPhone;
    private TextInputLayout layoutInputEmail;
    private TextInputLayout layoutInputPdInfo;

    private Button btnSubmit;
    private ProgressBar progressBar;

    public WebsiteFirstPromotionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_website_promotion, container, false);

        initWidget(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getContext()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getContext()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppBarLayout appbar = view.findViewById(R.id.appbar);
        CollapsingToolbarLayout collapsingToolbar = view.findViewById(R.id.collapsingToolbar);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //Check if the view is collapsed
                if (abs(verticalOffset) >= appbar.getTotalScrollRange()) {
                    collapsingToolbar.setTitle("AdPost | Website Promotion");
                    collapsingToolbar.setExpandedTitleColor(Color.WHITE);

                } else {
                    collapsingToolbar.setTitle("");
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AdPostActivity.class);
                startActivity(intent);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void initWidget(View view) {

        btnSubmit = view.findViewById(R.id.btnSubmit);
        inputPdTitle = view.findViewById(R.id.inputPdName);
        inputAdBudget = view.findViewById(R.id.inputAdBudget);
        inputWebAddress = view.findViewById(R.id.inputWebAddress);
        inputPhone = view.findViewById(R.id.inputPhone);
        inputEmail = view.findViewById(R.id.inputEmail);
        inputPdInfo = view.findViewById(R.id.inputPdInfo);

        layoutInputPdName = view.findViewById(R.id.layoutInputPdName);
        layoutInputAdBudget = view.findViewById(R.id.layoutInputAdBudget);
        layoutInputWebAddress = view.findViewById(R.id.layoutInputWebAddress);
        layoutInputPhone = view.findViewById(R.id.layoutInputPhone);
        layoutInputEmail = view.findViewById(R.id.layoutInputEmail);
        layoutInputPdInfo = view.findViewById(R.id.layoutInputPdInfo);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pushFragment(new ProductPromotionSecondFragment());
                sendData();
            }
        });
        
    }

    private void sendData() {

        if (!isInputValid(inputPdTitle.getText())){
            layoutInputPdName.setError(showErrorMessage("Product name"));
        }else {
            layoutInputPdName.setError(null);
            if (!isInputValid(inputAdBudget.getText())){
                layoutInputAdBudget.setError(showErrorMessage("Ad budget"));
            }else {
                layoutInputAdBudget.setError(null);
                if (!isInputValid(inputWebAddress.getText())){
                    layoutInputWebAddress.setError(showErrorMessage("Web address"));
                }else {
                    layoutInputWebAddress.setError(null);
                    if (!isInputValid(inputPhone.getText())){
                        layoutInputPhone.setError(showErrorMessage("Phone number"));
                    }else {
                        layoutInputPhone.setError(null);
                        if (!isInputValid(inputEmail.getText())){
                            layoutInputEmail.setError(showErrorMessage("Email address"));
                        }else {
                            layoutInputEmail.setError(null);
                            if (!isInputValid(inputPdInfo.getText())){
                                layoutInputPdInfo.setError(showErrorMessage("Product information"));
                            }else {
                                layoutInputPdInfo.setError(null);


                                String product_name = inputPdTitle.getText().toString();
                                String ad_budget = inputAdBudget.getText().toString();
                                String web_address = inputWebAddress.getText().toString();
                                String email = inputEmail.getText().toString();
                                String phone = inputPhone.getText().toString();
                                String product_info = inputPdInfo.getText().toString();

                                progressBar.setVisibility(View.VISIBLE);

                                Session session = new Session(getContext());

                                Map<String, String> map = new HashMap<>();
                                map.put("Authorization", String.valueOf(session.getAuthToken()));
                                map.put("userId",String.valueOf(session.getUserID()));

                                ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
                                Call<WebPromotionPostResponse> call = apiInterface.postWebPromotionPost(map,product_name,Double.parseDouble(ad_budget),web_address,email,phone,product_info);
                                call.enqueue(new Callback<WebPromotionPostResponse>() {
                                    @Override
                                    public void onResponse(Call<WebPromotionPostResponse> call, Response<WebPromotionPostResponse> response) {
                                        if (response.isSuccessful()){
                                            progressBar.setVisibility(View.GONE);
                                            WebPromotionPostResponse result = response.body();

                                            if (result.isSuccess().equals("Success")){
                                                Toast.makeText(getContext(), ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                                                pushFragment(new WebsiteSecondPromotionFragment());
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<WebPromotionPostResponse> call, Throwable t) {
                                        progressBar.setVisibility(View.GONE);
                                        Log.e(TAG, "onFailure: "+t.getMessage() );
                                        Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }
                    }
                }
            }
        }
    }

    public void pushFragment(Fragment fragment) {

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rootLayout, fragment);
        fragmentTransaction.commit();
    }

    private boolean isInputValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }

    private String showErrorMessage(String widget){
        return widget+" can not be empty!";
    }


}