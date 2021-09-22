package com.rss.rss.Dashboard.AdPost.SocialMediaPromotion;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rss.rss.Dashboard.AdPost.AdPostActivity;
import com.rss.rss.Dashboard.AdPost.ProductPromotion.ProductPromotionSecondFragment;
import com.rss.rss.MainActivity;
import com.rss.rss.Model.adpost.product_promotion.ProductPromotionPostResponse;
import com.rss.rss.Model.adpost.social_media_promotion.SocialMediaPromotionPostResponse;
import com.rss.rss.R;
import com.rss.rss.network.ApiInterface;
import com.rss.rss.network.RetrofitApiClient;
import com.rss.rss.utils.Session;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SMPFirstFragment extends Fragment {

    private static final String TAG = "SMPFragment";

    //WIDGET
    private PowerSpinnerView spinnerPromotionType;
    private TextInputEditText inputAdBudget;
    private TextInputEditText inputLink;
    private TextInputEditText inputPhone;
    private TextInputEditText inputEmail;
    private TextInputEditText inputPdInfo;

    private TextInputLayout layoutInputAdBudget;
    private TextInputLayout layoutInputLinkAddress;
    private TextInputLayout layoutInputPhone;
    private TextInputLayout layoutInputEmail;
    private TextInputLayout layoutInputPdInfo;

    private Button btnSubmit;
    private ProgressBar progressBar;

    private String promotionType = null;

    public SMPFirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_s_m_p, container, false);

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
                    collapsingToolbar.setTitle("AdPost | Social Media Promotion");
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

        initSpinner(view);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        inputAdBudget = view.findViewById(R.id.inputAdBudget);
        inputLink = view.findViewById(R.id.inputLink);
        inputPhone = view.findViewById(R.id.inputPhone);
        inputEmail = view.findViewById(R.id.inputEmail);
        inputPdInfo = view.findViewById(R.id.inputPdInfo);

        layoutInputAdBudget = view.findViewById(R.id.layoutInputAdBudget);
        layoutInputLinkAddress = view.findViewById(R.id.layoutInputLinkAddress);
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

    private void initSpinner(View view) {
        spinnerPromotionType = view.findViewById(R.id.spinnerPromotionType);

        spinnerPromotionType.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<Object>() {
            @Override
            public void onItemSelected(int i, @Nullable Object o, int i1, Object t1) {
                Log.d(TAG, "onItemSelected: i = "+i);
                Log.d(TAG, "onItemSelected: o = "+o);
                Log.d(TAG, "onItemSelected: i1 = "+i1);
                Log.d(TAG, "onItemSelected: t1 = "+t1);

                promotionType = t1.toString();
            }
        });
    }

    private void sendData(){

        if (promotionType.equals("--select promotion type--")){
            Toast.makeText(getContext(), "Please select promotion type!", Toast.LENGTH_SHORT).show();
        }else {
            Log.d(TAG, "sendData: "+promotionType);
            if (!isInputValid(inputAdBudget.getText())){
                layoutInputAdBudget.setError(showErrorMessage("Ad budget"));
            }else {
                layoutInputAdBudget.setError(null);
                if (!isInputValid(inputLink.getText())){
                    layoutInputLinkAddress.setError(showErrorMessage("Social media link"));
                }else {
                    layoutInputLinkAddress.setError(null);
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


                                String ad_budget = inputAdBudget.getText().toString();
                                String link = inputLink.getText().toString();
                                String email = inputEmail.getText().toString();
                                String phone = inputPhone.getText().toString();
                                String product_info = inputPdInfo.getText().toString();

                                progressBar.setVisibility(View.VISIBLE);
                                ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

                                Session session = new Session(getContext());

                                Map<String, String> map = new HashMap<>();
                                map.put("Authorization", String.valueOf(session.getAuthToken()));
                                map.put("userId",String.valueOf(session.getUserID()));

                                Call<SocialMediaPromotionPostResponse> call = apiInterface.postSocialPromotionPost(map,promotionType,Double.parseDouble(ad_budget),link,email,phone,product_info);
                                call.enqueue(new Callback<SocialMediaPromotionPostResponse>() {
                                    @Override
                                    public void onResponse(Call<SocialMediaPromotionPostResponse> call, Response<SocialMediaPromotionPostResponse> response) {
                                        if (response.isSuccessful()){
                                            progressBar.setVisibility(View.GONE);
                                            SocialMediaPromotionPostResponse result = response.body();

                                            if (result.getStatus().equals("Success")){
                                                Toast.makeText(getContext(), ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                                                pushFragment(new SMPSecondFragment());
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<SocialMediaPromotionPostResponse> call, Throwable t) {
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