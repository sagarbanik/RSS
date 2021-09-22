package com.rss.rss.Dashboard.AdPost.EmailPromotion;

import static java.lang.Math.abs;

import android.content.Intent;
import android.graphics.Color;
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
import com.rss.rss.Dashboard.AdPost.SocialMediaPromotion.SMPSecondFragment;
import com.rss.rss.Model.adpost.car_promotion.CarPromotionPostResponse;
import com.rss.rss.Model.adpost.email_promotion.EmailPromotionPostResponse;
import com.rss.rss.R;
import com.rss.rss.network.ApiInterface;
import com.rss.rss.network.RetrofitApiClient;
import com.rss.rss.utils.Session;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EmailPromoFirstFragment extends Fragment {

    private static final String TAG = "EmailPromoFirstFragment";

    //Widget
    private PowerSpinnerView spinnerEmailCategory;
    private PowerSpinnerView spinnerPackage;
    private TextInputEditText inputCompanyName;
    private TextInputEditText inputPhone;
    private TextInputEditText inputEmail;
    private TextInputEditText inputAdditionalInfo;
    private ProgressBar progressBar;
    private Button btnSubmit;

    private TextInputLayout inputCompanyNameLay;
    private TextInputLayout inputPhoneLay;
    private TextInputLayout inputEmailLay;
    private TextInputLayout inputAdditionalInfoLay;

    private String spinnerItem1;
    private String spinnerItem2;

    public EmailPromoFirstFragment() {
        // Required empty public constructor
    }
       

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email_promo_first, container, false);
        initWidget(view);
        return view;
    }

    private void initWidget(View view) {
        inputCompanyName = view.findViewById(R.id.inputCompanyName);
        inputPhone = view.findViewById(R.id.inputPhone);
        inputEmail = view.findViewById(R.id.inputEmail);
        inputAdditionalInfo = view.findViewById(R.id.inputAdditionalInfo);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        inputCompanyNameLay = view.findViewById(R.id.inputCompanyNameLay);
        inputPhoneLay = view.findViewById(R.id.inputPhoneLay);
        inputEmailLay = view.findViewById(R.id.inputEmailLay);
        inputAdditionalInfoLay = view.findViewById(R.id.inputAdditionalInfoLay);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });

        initFirstSpinner(view);
    }

    private void initFirstSpinner(View view) {
        spinnerEmailCategory = view.findViewById(R.id.spinnerEmailCategory);
        spinnerEmailCategory.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<Object>() {
            @Override
            public void onItemSelected(int i, @Nullable Object o, int i1, Object t1) {
                Log.d(TAG, "onItemSelected: i = "+i);
                Log.d(TAG, "onItemSelected: o = "+o);
                Log.d(TAG, "onItemSelected: i1 = "+i1);
                Log.d(TAG, "onItemSelected: t1 = "+t1);

                spinnerItem1 = t1.toString();
                initSecondaryServices(view);
            }
        });
    }

    private void initSecondaryServices(View view) {
        spinnerPackage = view.findViewById(R.id.spinnerPackage);
        if (spinnerItem1.equals("With email")){
            initWithSpinner();
        }else if (spinnerItem1.equals("Without email")){
            initWithoutSpinner();
        }
    }

    private void initWithSpinner() {
        spinnerPackage.setItems(R.array.item_array);
        spinnerPackage.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<Object>() {
            @Override
            public void onItemSelected(int i, @Nullable Object o, int i1, Object t1) {
                Log.d(TAG, "onItemSelected: i = "+i);
                Log.d(TAG, "onItemSelected: o = "+o);
                Log.d(TAG, "onItemSelected: i1 = "+i1);
                Log.d(TAG, "onItemSelected: t1 = "+t1);

                spinnerItem2 = t1.toString();
            }
        });
    }

    private void initWithoutSpinner() {
        spinnerPackage.setItems(R.array.package_array);
        spinnerPackage.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<Object>() {
            @Override
            public void onItemSelected(int i, @Nullable Object o, int i1, Object t1) {
                Log.d(TAG, "onItemSelected: i = "+i);
                Log.d(TAG, "onItemSelected: o = "+o);
                Log.d(TAG, "onItemSelected: i1 = "+i1);
                Log.d(TAG, "onItemSelected: t1 = "+t1);

                spinnerItem2 = t1.toString();
            }
        });
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
                    collapsingToolbar.setTitle("Adpost | Email Promotion");
                    collapsingToolbar.setExpandedTitleColor(Color.WHITE);

                } else {
                    collapsingToolbar.setTitle("");
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AdPostActivity.class);
                startActivity(intent);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void sendData(){

        if (spinnerItem1.isEmpty()){
            Toast.makeText(getContext(), "Please select promotion type!", Toast.LENGTH_SHORT).show();
        }else {
            if (spinnerItem2.isEmpty()){
                Toast.makeText(getContext(), "Please select package type!", Toast.LENGTH_SHORT).show();
            }else {
                if (!isInputValid(inputCompanyName.getText())){
                    inputCompanyNameLay.setError(showErrorMessage("Company name"));
                }else {
                    inputCompanyNameLay.setError(null);
                    if (!isInputValid(inputPhone.getText())){
                        inputPhoneLay.setError(showErrorMessage("Phone number"));
                    }else {
                        inputPhoneLay.setError(null);
                        if (!isInputValid(inputEmail.getText())){
                            inputEmailLay.setError(showErrorMessage("Email address"));
                        }else {
                            inputEmailLay.setError(null);
                            if (!isInputValid(inputAdditionalInfo.getText())){
                                inputAdditionalInfoLay.setError(showErrorMessage("Additional information"));
                            }else {
                                inputAdditionalInfoLay.setError(null);

                                String companyName = inputCompanyName.getText().toString();
                                String email = inputEmail.getText().toString();
                                String phone = inputPhone.getText().toString();
                                String additional_info = inputAdditionalInfo.getText().toString();

                                Session session = new Session(getContext());
                                Map<String, String> map = new HashMap<>();
                                map.put("Authorization", String.valueOf(session.getAuthToken()));
                                map.put("userId",String.valueOf(session.getUserID()));

                                progressBar.setVisibility(View.VISIBLE);

                                ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
                                Call<EmailPromotionPostResponse> call = apiInterface.postEmailPromotionPost(map,spinnerItem1,spinnerItem2,companyName,phone,email,additional_info);
                                call.enqueue(new Callback<EmailPromotionPostResponse>() {
                                    @Override
                                    public void onResponse(Call<EmailPromotionPostResponse> call, Response<EmailPromotionPostResponse> response) {
                                        if (response.isSuccessful()){
                                            progressBar.setVisibility(View.GONE);
                                            EmailPromotionPostResponse result = response.body();

                                            if (result.getStatus().equals("Success")){
                                                Toast.makeText(getContext(), ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                                                pushFragment(new SMPSecondFragment());
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<EmailPromotionPostResponse> call, Throwable t) {
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