package com.rss.rss.Dashboard.Affiliate.bottom_sheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.rss.rss.Dashboard.Affiliate.checkout.CheckoutActivity;
import com.rss.rss.Model.affiliate.Product;
import com.rss.rss.R;

public class CheckoutBottomSheet extends BottomSheetDialogFragment {

    private static final String TAG = "CheckoutBottomSheet";

    //Widget
    private TextView totalAmount;
    private TextView vatAndTax;
    private TextView shippingCharge;
    private TextView subtotalAmount;
    private MaterialButton btnCheckout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.cart_bottom_sheet,
                container, false);
        initWidget(v);
        return v;
    }

    private void initWidget(View v) {

        totalAmount = v.findViewById(R.id.totalAmount);
        vatAndTax = v.findViewById(R.id.vatAndTax);
        shippingCharge = v.findViewById(R.id.shippingCharge);
        subtotalAmount = v.findViewById(R.id.subtotalAmount);

        float sumAmount = getSubTotalBalance();
        Log.d(TAG, "initWidget: "+sumAmount);
        Log.d(TAG, "initWidget: cart items " +
                Product.cartList.toString());

        subtotalAmount.setText("\u09F3"+String.format("%.2f",sumAmount));
        vatAndTax.setText("\u09F3"+String.format("%.2f",0.0f));
        shippingCharge.setText("\u09F3"+String.format("%.2f",20.0f));
        totalAmount.setText("\u09F3"+String.format("%.2f",sumAmount+20.0f));

        btnCheckout = v.findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pushFragment(new CheckoutHomeFragment());
                Intent i = new Intent(getContext(), CheckoutActivity.class);
                i.putExtra("totalAmount",sumAmount);
                startActivity(i);
                dismiss();
            }
        });
    }

    public static float getSubTotalBalance(){
        float sum = 0.0f;
        if (Product.cartList != null){
            for (int i=0;i<Product.cartList.size();i++){
                //float bal = Product.cartList.get(i).getFinalCheckoutAmount();
                //sum = bal+sum;
                float basePrice = Product.cartList.get(i).getPrice();
                int qty = Product.cartList.get(i).getFinalCheckoutQuantity();
                sum = sum+qty*basePrice;
            }
        }
        return sum;
    }

    public void pushFragment( Fragment fragment) {
        if (fragment == null)
            return;
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (ft != null) {
            ft.replace(R.id.rootLayout, fragment);
            ft.commit();
        }
    }

}
