package com.rss.rss.Dashboard.Transaction;

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
import android.widget.TextView;

import com.rss.rss.MainActivity;
import com.rss.rss.R;
import com.rss.rss.Dashboard.Transaction.BalanceTransfer.BalanceTransferFirstFragment;
import com.rss.rss.Dashboard.Transaction.FundRecharge.FundRechargeFirstFragment;
import com.rss.rss.Dashboard.Transaction.MobileRecharge.MobileRechargeFirstFragment;
import com.rss.rss.Dashboard.Transaction.TransactionHistory.WithdrawStatusFragment;
import com.rss.rss.Dashboard.Transaction.Withdraw.WithdrawRequestFragment;

import java.util.Objects;

public class TransactionHomeFragment extends Fragment {

    private static final String TAG = "TransactionHomeFragment";

    //Widget
    private TextView btnWithdrawRequest;
    private TextView btnWithdrawStatus;
    private TextView btnFundRecharge;
    private TextView btnMobileRecharge;
    private TextView btnBalanceTransfer;

    public TransactionHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_home, container, false);

        initWidget(view);

        return view;
    }

    private void initWidget(View view) {

        initToolbar();

        btnWithdrawRequest = view.findViewById(R.id.btnWithdrawRequest);
        btnWithdrawStatus = view.findViewById(R.id.btnWithdrawStatus);
        btnFundRecharge = view.findViewById(R.id.btnFundRecharge);
        btnMobileRecharge = view.findViewById(R.id.btnMobileRecharge);
        btnBalanceTransfer = view.findViewById(R.id.btnBalanceTransfer);

        btnWithdrawRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new WithdrawRequestFragment());
            }
        });

        btnWithdrawStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new WithdrawStatusFragment());
            }
        });

        btnFundRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new FundRechargeFirstFragment());
            }
        });

        btnMobileRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new MobileRechargeFirstFragment());
            }
        });

        btnBalanceTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new BalanceTransferFirstFragment());
            }
        });
    }

    private void initToolbar(){

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Transaction");

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