package com.rss.rss.Dashboard.Affiliate.checkout.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.room.Room;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.rss.rss.Dashboard.Affiliate.checkout.CheckoutActivity;
import com.rss.rss.Model.affiliate.ShippingAddress;
import com.rss.rss.R;
import com.rss.rss.room.DataDao;
import com.rss.rss.room.RoomDB;

public class DialogShippingAddress extends AppCompatDialogFragment {

    private static final String TAG = "DialogShippingAddress";

    //Widgets
    private TextInputEditText title_edit_text;
    private TextInputEditText details_edit_text;
    private MaterialButton next_submit;
    private MaterialButton cancel_button;

    //Room persistent database
    private RoomDB database;
    public static DataDao dataDao;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_shipping_address, null);
        builder.setView(view);

        database = Room.databaseBuilder(getContext(),RoomDB.class,"super_finix").allowMainThreadQueries().build();
        dataDao = database.dataDao();

        title_edit_text = view.findViewById(R.id.title_edit_text);
        details_edit_text = view.findViewById(R.id.details_edit_text);
        next_submit = view.findViewById(R.id.next_submit);
        cancel_button = view.findViewById(R.id.cancel_button);

        next_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String title = title_edit_text.getText().toString();
                String details = details_edit_text.getText().toString();

                if (title != null && details != null){
                    ShippingAddress address = new ShippingAddress(1,title,details);
                    dataDao.insertShippingAddress(address);
                    CheckoutActivity.addressList.add(address);
                    CheckoutActivity.addressList.add(address);
                    CheckoutActivity.addressAdapter.notifyDataSetChanged();
                    CheckoutActivity.addressAdapter.notifyDataSetChanged();
                    DialogShippingAddress.this.dismiss();

                }else {
                    Toast.makeText(getContext(), "Please insert all the field.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogShippingAddress.this.dismiss();
            }
        });


        return builder.create();
    }



}
