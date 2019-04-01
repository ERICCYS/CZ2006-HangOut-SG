package com.example.hangout_v0.Vendor.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hangout_v0.R;
import com.example.hangout_v0.Vendor.Shop;

import java.util.ArrayList;

public class ShopAdapterVendor extends BaseAdapter {
    Activity context;
    ArrayList<Shop> shops;
    private static LayoutInflater inflater = null;

    public ShopAdapterVendor(Activity context, ArrayList<Shop> shops){
        this.context = context;
        this.shops = shops;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = convertView;
        itemView = (itemView == null)? inflater.inflate(R.layout.shop_in_vendor_view, null): itemView;
        TextView shopName = (TextView) itemView.findViewById(R.id.shopNameTextView);
        TextView shopNumber = (TextView) itemView.findViewById(R.id.shopNumberTextView);
        Shop selectedShop = shops.get(position);
        shopName.setText(selectedShop.getName());
        shopNumber.setText(selectedShop.getContactNumber());
        //selectedShop.getShopId();
        return itemView;
    }
    @Override
    public int getCount(){
        return shops.size();
    }
    @Override
    public Shop getItem(int position){
        return shops.get(position);
    }
    @Override
    public long getItemId(int position){
    return position;
    }
}
