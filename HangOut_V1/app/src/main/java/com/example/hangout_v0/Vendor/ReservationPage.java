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
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mTimes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_reservation);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        vendorId = extras.getLong("vendorId", 1);
        shopId = extras.getLong("shopId", 3);

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
                            for (int i = 0; i < vendorReservations.length(); i++) {
                                JSONObject reservation = vendorReservations.getJSONObject(i);
                                if (reservation.getLong("shopId") == shopId) {
                                    mNames.add("CustomerId:" + reservation.getString("customerId"));
                                    mTimes.add("Arrival Time" + reservation.getString("arrivalTime"));
                                }
                            }
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                RecyclerView recyclerView = findViewById(R.id.ReservationList);
                                ReservationAdapterVendor adapter = new ReservationAdapterVendor(mNames, mTimes, ReservationPage.this);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(ReservationPage.this));
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
}