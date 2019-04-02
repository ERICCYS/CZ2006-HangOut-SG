package com.example.hangout_v0.Me.reservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hangout_v0.R;

import java.util.ArrayList;

public class ReservationAdaptor extends BaseAdapter{

    ArrayList<String> shopName;
    ArrayList<String> shopAddress;
    ArrayList<String> shopDateTime;
    LayoutInflater mInflater;

    public ReservationAdaptor(Context c, ArrayList<String> name, ArrayList<String> shopAdd,ArrayList<String> shopDT){
        shopAddress = shopAdd;
        shopName = name;
        shopDateTime = shopDT;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return shopName.size();
    }

    @Override
    public Object getItem(int position) {
        return shopName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v= mInflater.inflate(R.layout.me_reservation_shop_in_list,null);
        TextView shopNameTextView =  v.findViewById(R.id.me_reservation_shopNameTextView);
        TextView shopAddressTextView = v.findViewById(R.id.me_reservation_shopAddressTextView);
        TextView shopDateTimeTextView = v.findViewById(R.id.me_reservation_shopDateTimeTextView);

        String name = shopName.get(position);
        String address = shopAddress.get(position);
        String dateTime= shopDateTime.get(position);

        shopNameTextView.setText(name);
        shopAddressTextView.setText(address);
        shopDateTimeTextView.setText(dateTime);

        //Handle buttons and add onClickListeners
        ImageButton deleteBtn = (ImageButton) v.findViewById(R.id.me_reservation_shop_deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                shopName.remove(position);
                shopAddress.remove(position);
                shopDateTime.remove(position);
                notifyDataSetChanged();
            }
        });

        return v;
    }
}


