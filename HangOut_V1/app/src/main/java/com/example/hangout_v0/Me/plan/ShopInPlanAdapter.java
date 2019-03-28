package com.example.hangout_v0.Me.plan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hangout_v0.R;

import java.util.ArrayList;

public class ShopInPlanAdapter extends BaseAdapter {

    ArrayList<String> shopName;
    ArrayList<String> shopAddress;
    ArrayList<String> shopDateTime;
    LayoutInflater mInflater;

    public ShopInPlanAdapter(Context c, ArrayList<String> name,ArrayList<String> shopAdd, ArrayList<String> shopDT){
        shopDateTime = shopDT;
        shopAddress = shopAdd;
        shopName = name;
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
        View v= mInflater.inflate(R.layout.me_plan_shop_in_plan,null);
        TextView shopNameTextView = (TextView) v.findViewById(R.id.shopNameTextView);
        TextView shopDateTimeTextView = (TextView) v.findViewById(R.id.shopDateTimeTextView);

        String name = shopName.get(position);
        String dt = shopDateTime.get(position);

        shopNameTextView.setText(name);
        shopDateTimeTextView.setText(dt);

        //Handle buttons and add onClickListeners
        ImageButton deleteBtn = (ImageButton) v.findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                shopName.remove(position);
                shopDateTime.remove(position);
                shopAddress.remove(position);
                notifyDataSetChanged();
            }
        });

        return v;
    }
}
