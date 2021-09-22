package com.rss.rss.Dashboard.AdPost.SmsPromotion;

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
import com.rss.rss.Model.adpost.sms_promotion.SmsPromotionPostResponse;
import com.rss.rss.Model.adpost.social_media_promotion.SocialMediaPromotionPostResponse;
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

public class SmsPromoFirstFragment extends Fragment {

    private static final String TAG = "SmsPromoFirstFragment";

    //Widget
    private PowerSpinnerView spinnerSmsCategory;
    private PowerSpinnerView spinnerPackage;
    private TextInputEditText inputCompanyName;
    private TextInputEditText inputPhone;
    private TextInputEditText inputEmail;
    private TextInputEditText inputAdditionalInfo;
    private Button btnSubmit;
    private ProgressBar progressBar;

    private TextInputLayout inputPhoneLay;
    private TextInputLayout inputEmailLay;
    private TextInputLayout inputInfoLay;

    private String spinnerItem1;
    private String spinnerItem2;

    public SmsPromoFirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sms_promo_first, container, false);
        initWidget(view);
        return view;
    }

    private void initWidget(View view) {

        initFirstSpinner(view);

        inputCompanyName = view.findViewById(R.id.inputCompanyName);
        inputPhone = view.findViewById(R.id.inputPhone);
        inputEmail = view.findViewById(R.id.inputEmail);
        inputAdditionalInfo = view.findViewById(R.id.inputAdditionalInfo);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        inputInfoLay = view.findViewById(R.id.inputInfoLay);
        inputEmailLay = view.findViewById(R.id.inputEmailLay);
        inputPhoneLay = view.findViewById(R.id.inputPhoneLay);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });


    }

    private void sendData(){

        if (spinnerItem1.isEmpty()){
            Toast.makeText(getContext(), "Please select promotion type!", Toast.LENGTH_SHORT).show();
        }else {
            if (spinnerItem2.isEmpty()){
                Toast.makeText(getContext(), "Please select package type!", Toast.LENGTH_SHORT).show();
            }else {
                if (!isInputValid(inputPhone.getText())){
                    inputPhoneLay.setError(showErrorMessage("Phone number"));
                }else {
                    inputPhoneLay.setError(null);
                    if (!isInputValid(inputEmail.getText())){
                        inputEmailLay.setError(showErrorMessage("Email address"));
                    }else {
                        inputEmailLay.setError(null);
                        if (!isInputValid(inputAdditionalInfo.getText())){
                            inputInfoLay.setError(showErrorMessage("Additional information"));
                        }else {
                            inputInfoLay.setError(null);

                            String email = inputEmail.getText().toString();
                            String phone = inputPhone.getText().toString();
                            String additional_info = inputAdditionalInfo.getText().toString();

                            Session session = new Session(getContext());
                            Map<String, String> map = new HashMap<>();
                            map.put("Authorization", String.valueOf(session.getAuthToken()));
                            map.put("userId",String.valueOf(session.getUserID()));

                            progressBar.setVisibility(View.VISIBLE);

                            ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
                            Call<SmsPromotionPostResponse> call = apiInterface.postSmsPromotionPost(map,spinnerItem1,spinnerItem2,phone,email,additional_info);
                            call.enqueue(new Callback<SmsPromotionPostResponse>() {
                                @Override
                                public void onResponse(Call<SmsPromotionPostResponse> call, Response<SmsPromotionPostResponse> response) {
                                    if (response.isSuccessful()){
                                        progressBar.setVisibility(View.GONE);
                                        SmsPromotionPostResponse result = response.body();

                                        if (result.getStatus().equals("Success")){
                                            Toast.makeText(getContext(), ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                                            pushFragment(new SMPSecondFragment());
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<SmsPromotionPostResponse> call, Throwable t) {
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

    public void pushFragment(Fragment fragment) {

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rootLayout, fragment);
        fragmentTransaction.commit();
    }

    private void initFirstSpinner(View view) {
        spinnerSmsCategory = view.findViewById(R.id.spinnerSmsCategory);
        spinnerSmsCategory.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<Object>() {
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
        if (spinnerItem1.equals("Masking SMS")){
            initMarkingSpinner();
        }else if (spinnerItem1.equals("Non-Masking SMS")){
            initNonMarkingSpinner();
        }
    }

    private void initMarkingSpinner() {
        spinnerPackage.setItems(R.array.package_array1);
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

    private void initNonMarkingSpinner() {
        spinnerPackage.setItems(R.array.item_array1);
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
                    collapsingToolbar.setTitle("Adpost | SMS Promotion");
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

    private boolean isInputValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }

    private String showErrorMessage(String widget){
        return widget+" can not be empty!";
    }

}