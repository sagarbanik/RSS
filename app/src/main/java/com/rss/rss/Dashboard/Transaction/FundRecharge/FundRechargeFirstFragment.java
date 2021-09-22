package com.rss.rss.Dashboard.Transaction.FundRecharge;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.rss.rss.MainActivity;
import com.rss.rss.R;

import java.util.Objects;

public class FundRechargeFirstFragment extends Fragment {

    private static final String TAG = "FundRechargeFirstFragment";

    //Widget
    private Button btnNext;
    private EditText inputIdNumber;
    private EditText inputFullName;
    private Spinner gatewaySpinner;
    private EditText inputAmount;
    private EditText inputPassword;

    public FundRechargeFirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fund_recharge_first, container, false);
        
        initWidget(view);
        
        return view;
    }

    private void initWidget(View view) {
        initToolbar();

        btnNext = view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new FundRechargeSecondFragment());
            }
        });

        inputIdNumber = view.findViewById(R.id.inputIdNumber);
        inputFullName = view.findViewById(R.id.inputFullName);
        inputAmount = view.findViewById(R.id.inputAmount);
        inputPassword = view.findViewById(R.id.inputPassword);

        initSpinner(view);

    }

    private void initSpinner(View view) {
        gatewaySpinner = view.findViewById(R.id.gatewaySpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.packageArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        gatewaySpinner.setAdapter(adapter);


        gatewaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initToolbar(){

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Transaction|Fund Recharge");

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back_arrow_white);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(upArrow);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform whatever you want on back arrow click
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                ((AppCompatActivity)getActivity()).finish();
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