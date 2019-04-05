package com.example.hangout_v0.Vendor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.R;
import com.example.hangout_v0.Vendor.Utils.ShopAdapterVendor;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VendorMainActivity extends AppCompatActivity {
    public static final String baseUrl = HangOutApi.baseUrl;
    ListView listView;
    //String url = "https://avatarfiles.alphacoders.com/873/thumb-87368.jpg";
    Button addShop;
    ImageButton editProfile;
    ShopAdapterVendor adapter;
    TextView vendorNameTv, vendorEmailTv, vendorGenderTv;
    public Long id = new Long(1);
    public String accessToken;
    private String firstname, lastname, gender, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_vendor);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id = extras.getLong("vendorId", 1);
        accessToken = extras.getString("AccessToken");
        HangOutApi.vendorAT = accessToken;

        vendorNameTv = findViewById(R.id.vendorNameText);
        vendorEmailTv = findViewById(R.id.vendorEmailText);
        vendorGenderTv = findViewById(R.id.vendorGenderText);

        OkHttpClient client = new OkHttpClient();
        String url = baseUrl + "vendor";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        httpBuilder.addQueryParameter("vendorId", id.toString());
        Request request = new Request.Builder().url(httpBuilder.build()).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
                    try {
                        JSONObject vendor = new JSONObject(myResponse);
                        HangOutData.setVendor(vendor);
                        // shops display listview
                        listView = (ListView) findViewById(R.id.shopList);
                        final ArrayList<Shop> arrayList = new ArrayList<>(); //you should change 'string' here to 'shop' later when connect with back end
                        JSONArray shopList = new JSONArray();
                        try {
                            shopList = vendor.getJSONArray("shops");
                            System.out.print("Get success");
                            if (shopList != null) {
                                for (int i=0;i<shopList.length();i++){
                                    JSONObject shop = shopList.getJSONObject(i);
                                    arrayList.add(new Shop(shop.getString("name"), shop.getString("contactNumber"), shop.getLong("id")));
                                }
                            }
                            //get vendor info
                            firstname = vendor.get("firstName").toString();
                            lastname = vendor.get("lastName").toString();
                            gender = vendor.get("gender").toString();
                            email = vendor.get("email").toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        VendorMainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // set all text to info
                                vendorNameTv.setText(firstname+" "+lastname);
                                vendorGenderTv.setText("Gender: "+gender);
                                vendorEmailTv.setText("Email: "+email);
                            }
                        });

                        adapter = new ShopAdapterVendor(VendorMainActivity.this, arrayList);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listView.setAdapter(adapter);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long idd){

                                        Long tempListView = arrayList.get(position).getShopId();
                                        Intent intent = new Intent(VendorMainActivity.this, com.example.hangout_v0.Vendor.VendorShop.class);
                                        Bundle extras = new Bundle();
                                        extras.putLong("shopId", tempListView);
                                        extras.putLong("vendorId", id);
                                        intent.putExtras(extras);
                                        startActivity(intent);

                                    }
                                });
                            }
                        });





                    } catch (JSONException e) {
                        //textView.setText("Error");
                    }
                }
            }
        });


        addShop = (Button) findViewById(R.id.addShopButton);
        addShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(VendorMainActivity.this,"hi",Toast.LENGTH_SHORT).show();
                Intent addyourshop = new Intent(VendorMainActivity.this, com.example.hangout_v0.Vendor.AddShop.class);
                addyourshop.putExtra("vendorId", id);
                startActivity(addyourshop);
            }
        });

        editProfile = findViewById(R.id.editVendorProfileBtn);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editVendorProfile = new Intent(VendorMainActivity.this, com.example.hangout_v0.Vendor.VendorEditProfile.class);
                startActivity(editVendorProfile);
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
