package com.example.hangout_v0.Vendor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hangout_v0.R;
import com.skyforce.SkyForceWebService.model.Shop;

import com.example.hangout_v0.Vendor.Utils.ShopAdapterVendor;

import java.util.ArrayList;

public class VendorMainActivity extends AppCompatActivity {

    ListView listView;
    ImageView avator;
    String url = "https://avatarfiles.alphacoders.com/873/thumb-87368.jpg";
    Button addShop;
    ShopAdapterVendor adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vendor);

        addShop = (Button) findViewById(R.id.addShopButton);
        addShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(VendorMainActivity.this,"hi",Toast.LENGTH_SHORT).show();
                Intent addyourshop = new Intent(VendorMainActivity.this, com.example.hangout_v0.Vendor.AddShop.class);
                startActivity(addyourshop);
            }
        });

        avator = (ImageView)findViewById(R.id.vendorAvatar);
        avator.setImageResource(R.drawable.image1);

        //listview
        listView = (ListView) findViewById(R.id.shopList);
        final ArrayList<Shop> arrayList = new ArrayList<>(); //you should change 'string' here to 'shop' later when connect with back end

        arrayList.add(new Shop("PUTIEN Jurong", "12345678"));
        arrayList.add(new Shop("PUTIEN Changi", "91234567"));
        adapter = new ShopAdapterVendor(this, arrayList);
        listView.setAdapter(adapter);
        //listview end
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){

                String tempListView = arrayList.get(position).getName();
                Intent intent = new Intent(VendorMainActivity.this, com.example.hangout_v0.Vendor.VendorShop.class);
                intent.putExtra("ClickedValue", tempListView);
                startActivity(intent);

            }
        });

    }
    public void populateListView(){

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }



}
