package com.example.hangout_v0.Vendor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReservationPage extends AppCompatActivity {

    public static final String baseUrl = HangOutApi.baseUrl;
    public Long vendorId;
    public Long shopId;
    //customer names and times
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mTimes = new ArrayList<>();
    //    private ArrayList<String> mImgaeUrls = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_reservation);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        vendorId = extras.getLong("vendorId", 1);
        shopId = extras.getLong("shopId",3);
//        vendorId = new Long(1);
//        shopId = new Long(3);

        OkHttpClient client = new OkHttpClient();
        String url = baseUrl + "reservation-vendor";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        httpBuilder.addQueryParameter("vendorId", vendorId.toString());
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
                        JSONArray vendorReservations = new JSONArray(myResponse);
                        HangOutData.setReservationList(vendorReservations);

                        System.out.print("Get success");
                        if (vendorReservations != null) {
                            for (int i=0;i<vendorReservations.length();i++){
                                JSONObject reservation = vendorReservations.getJSONObject(i);
                                if(reservation.getLong("shopId") == shopId){
                                    mNames.add("CustomerId:" + reservation.getString("customerId"));
                                    mTimes.add("Arrival Time" + reservation.getString("arrivalTime"));
                                }
                            }
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                RecyclerView recyclerView = findViewById(R.id.ReservationList);
                                ReservationAdapterVendor adapter = new ReservationAdapterVendor(mNames, mTimes,ReservationPage.this);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(ReservationPage.this));
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //textView.setText("Vendor now have reservation " + myResponse);
                    // Able to get the access token.
                }
            }
        });


    }

//    private void initImageBitmaps(){
//        mNames.add("Harry Porter");
//        mTimes.add("9:00 29-03-2019");
//
//        mNames.add("Steven Kin");
//        mTimes.add("17:00 30-03-2019");
//
//        mNames.add("Jeremy");
//        mTimes.add("15:00 30-03-2019");
//
//        mNames.add("Peter");
//        mTimes.add("15:00 31-03-2019");
//
//        mNames.add("Jay Chou");
//        mTimes.add("16:00 30-03-2019");
//
//        mNames.add("JJ Lin");
//        mTimes.add("12:00 01-04-2019");
//
//        mNames.add("Naruto Uzumaki");
//        mTimes.add("15:00 30-04-2019");
//        initRecyclerView();
//    }
//    private void initRecyclerView(){
//        RecyclerView recyclerView = findViewById(R.id.ReservationList);
//        ReservationAdapterVendor adapter = new ReservationAdapterVendor(mNames, mTimes,ReservationPage.this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(ReservationPage.this));
//
//    }
}