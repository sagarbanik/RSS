package com.rss.rss.adapter.affiliate.checkout;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.rss.rss.Dashboard.Affiliate.checkout.CheckoutActivity;
import com.rss.rss.Dashboard.Affiliate.checkout.dialog.EditShippingAddress;
import com.rss.rss.Model.affiliate.ShippingAddress;
import com.rss.rss.R;

import java.util.List;

public class ShippingAddressAdapter extends RecyclerView.Adapter<ShippingAddressAdapter.MyViewHolder> {

    private static final String TAG = "ShippingAddressAdapter";

    private Context context;
    private List<ShippingAddress> addressList;

    public ShippingAddressAdapter(Context context, List<ShippingAddress> addressList) {
        this.context = context;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public ShippingAddressAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shipping_address,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShippingAddressAdapter.MyViewHolder holder, int position) {

        ShippingAddress address = addressList.get(position);

        holder.addressDetails.setText(address.getDetails());
        holder.addressTitle.setText(address.getTitle());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(address.getId());
            }
        });
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Log.d(TAG, "onCheckedChanged: "+holder.getAdapterPosition());
                    CheckoutActivity.isAddressClicked = true;
                    CheckoutActivity.addressPosition = holder.getAdapterPosition();
                }else {
                    CheckoutActivity.isAddressClicked = false;
                }
            }
        });

    }

    private void openDialog(int address_id) {

        EditShippingAddress dialog = EditShippingAddress.newInstance(address_id);
        dialog.show(((AppCompatActivity)context).getSupportFragmentManager(), "dialog_edit");

        /*EditShippingAddress exampleDialog = new EditShippingAddress();
        exampleDialog.show(((AppCompatActivity)context).getSupportFragmentManager(), "dialog_edit");*/
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private MaterialCheckBox checkbox;
        private TextView addressTitle;
        private TextView addressDetails;
        private ImageView btnEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            checkbox = itemView.findViewById(R.id.checkbox);
            addressTitle = itemView.findViewById(R.id.addressTitle);
            addressDetails = itemView.findViewById(R.id.addressDetails);
            btnEdit = itemView.findViewById(R.id.btnEdit);

        }
    }
}

