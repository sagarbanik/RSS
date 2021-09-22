package com.rss.rss.Dashboard.Services;

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
import com.rosemaryapp.amazingspinner.AmazingSpinner;
import com.rss.rss.Dashboard.Others.OthersActivity;
import com.rss.rss.MainActivity;
import com.rss.rss.Model.services.ClientServicePostResponse;
import com.rss.rss.R;
import com.rss.rss.adapter.SpinnerAdapter;
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


public class ServiceFirstFragment extends Fragment {

    private static final String TAG = "ServiceFirstFragment";

    //Widget
    private PowerSpinnerView spinnerServiceType;
    private PowerSpinnerView spinnerServices;

    private TextInputEditText inputCompanyName;
    private TextInputEditText inputAddress;
    private TextInputEditText inputPhone;
    private TextInputEditText inputEmail;
    private TextInputEditText inputAdditionalInfo;

    private ProgressBar progressBar;
    private Button btnSubmit;

    //Spinner selected item
    private String spinnerItem1;
    private String spinnerItem2;

    public ServiceFirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service_first, container, false);

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
                    collapsingToolbar.setTitle("Services");
                    collapsingToolbar.setExpandedTitleColor(Color.WHITE);

                } else {
                    collapsingToolbar.setTitle("");
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void initWidget(View view) {

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        inputCompanyName = view.findViewById(R.id.inputCompanyName);
        inputAddress = view.findViewById(R.id.inputAddress);
        inputPhone = view.findViewById(R.id.inputPhone);
        inputEmail = view.findViewById(R.id.inputEmail);
        inputAdditionalInfo = view.findViewById(R.id.inputAdditionalInfo);

        spinnerServiceType = view.findViewById(R.id.spinnerServiceType);
        spinnerServices = view.findViewById(R.id.spinnerServices);

        initSpinnerServiceType();

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

    }

    private void initSpinnerServiceType() {
        spinnerServiceType.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<Object>() {
            @Override
            public void onItemSelected(int i, @Nullable Object o, int i1, Object t1) {
                Log.d(TAG, "onItemSelected: i = "+i);
                Log.d(TAG, "onItemSelected: o = "+o);
                Log.d(TAG, "onItemSelected: i1 = "+i1);
                Log.d(TAG, "onItemSelected: t1 = "+t1);

                spinnerItem1 = t1.toString();
                initSecondaryServices();
            }
        });

    }

    private void initSecondaryServices(){

        if (spinnerItem1.equals("Virtual Service")){
            initSecondaryVirtualSpinner();
        }else if (spinnerItem1.equals("Outdoor Service")){
            initSecondaryOutdoorSpinner();
        }

    }

    private void initSecondaryOutdoorSpinner() {

        spinnerServices.setItems(R.array.outdoor_service_array);
        spinnerServices.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<Object>() {
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

    private void initSecondaryVirtualSpinner() {

        spinnerServices.setItems(R.array.expertise_area_array);
        spinnerServices.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<Object>() {
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

    private void sendData(){

        String service_type = spinnerItem1;
        String service_name = spinnerItem2;

        String company_name = inputCompanyName.getText().toString();
        String address = inputAddress.getText().toString();
        String description = inputAdditionalInfo.getText().toString();
        String phone = inputPhone.getText().toString();
        String email = inputEmail.getText().toString();


        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        Session session = new Session(getContext());

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", String.valueOf(session.getAuthToken()));
        map.put("userId",String.valueOf(session.getUserID()));

        Call<ClientServicePostResponse> call = apiInterface.postClientService(map,service_type,service_name,company_name,address,description,phone,email);
        call.enqueue(new Callback<ClientServicePostResponse>() {
            @Override
            public void onResponse(Call<ClientServicePostResponse> call, Response<ClientServicePostResponse> response) {
                if (response.isSuccessful()){
                    ClientServicePostResponse result = response.body();
                    if (result.getStatus().equals("Success")){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                        pushFragment(new ServiceSecondFragment());
                    }
                }
            }

            @Override
            public void onFailure(Call<ClientServicePostResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "onFailure: "+t.getMessage() );
                Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void pushFragment(Fragment fragment) {

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rootLayout, fragment);
        fragmentTransaction.commit();
    }

}