package com.example.hangout_v0.Me.reservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.Me.plan.PlanDataStub;
import com.example.hangout_v0.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.*;

public class ReservationActivity extends AppCompatActivity {

    ListView reservationListView;

    private ArrayList<String> reservationShopName = new ArrayList<>();
    private ArrayList<String> reservationShopAddress = new ArrayList<>();
    private ArrayList<String> reservationShopDateTime = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_reservation_list);

        this.getSupportActionBar().hide();

        reservationListView = findViewById(R.id.me_reservation_reservationListView);

        Long customerId = HangOutApi.userId;
        System.out.println("--------------"+customerId);

        OkHttpClient client = new OkHttpClient();
        String url = HangOutApi.baseUrl + "reservation-customer-formatted";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        httpBuilder.addQueryParameter("customerId", customerId.toString());
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
                        JSONArray reservations = new JSONArray(myResponse);
                        for (int i = 0; i < reservations.length(); i++) {
                            JSONObject reservation = (JSONObject)reservations.get(i);
                            reservationShopDateTime.add((reservation.get("arrivalTime").toString()).substring(5,16));
                            //System.out.println((reservation.get("arrivalTime").toString()));
                            reservationShopName.add(reservation.get("shopName").toString());
                            reservationShopAddress.add(reservation.get("shopAddress").toString());
                        }
                        ReservationActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                com.example.hangout_v0.Me.reservation.ReservationAdaptor reservationAdaptor = new com.example.hangout_v0.Me.reservation.ReservationAdaptor(ReservationActivity.this, reservationShopName,reservationShopAddress,reservationShopDateTime);
                                reservationListView.setAdapter(reservationAdaptor);
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
