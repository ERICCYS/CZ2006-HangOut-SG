package com.example.hangout_v0.Me.reservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hangout_v0.Me.plan.PlanDataStub;
import com.example.hangout_v0.R;

import java.util.ArrayList;

public class ReservationActivity extends AppCompatActivity {

    ListView reservationListView;

    ArrayList<String> reservationShopName;
    ArrayList<String> reservationShopAddress;
    ArrayList<String> reservationShopDateTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_reservation_list);

        this.getSupportActionBar().hide();

        reservationListView = findViewById(R.id.me_reservation_reservationListView);

        PlanDataStub planDataStub = new PlanDataStub("User1");
//
        reservationShopName= planDataStub.getReservationShopName(); // planName to replace hardcode "Plan A"
        reservationShopAddress = planDataStub.getReservationShopAddress();
        reservationShopDateTime = planDataStub.getReservationShopDateTime();
//
        com.example.hangout_v0.Me.reservation.ReservationAdaptor reservationAdaptor = new com.example.hangout_v0.Me.reservation.ReservationAdaptor(this, reservationShopName,reservationShopAddress,reservationShopDateTime);
        reservationListView.setAdapter(reservationAdaptor);

//        reservationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                reservationShopName.remove(position);
//                reservationShopAddress.remove(position);
//
//            }
//        });


    }

}
