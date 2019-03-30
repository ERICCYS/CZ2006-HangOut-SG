package com.example.hangout_v0.Vendor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hangout_v0.R;

import java.util.ArrayList;

public class ReservationPage extends AppCompatActivity {


    //customer names and times
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mTimes = new ArrayList<>();
//    private ArrayList<String> mImgaeUrls = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_reservation);

        initImageBitmaps();


    }

    private void initImageBitmaps(){
        mNames.add("Harry Porter");
        mTimes.add("9:00 29-03-2019");

        mNames.add("Steven Kin");
        mTimes.add("17:00 30-03-2019");

        mNames.add("Jeremy");
        mTimes.add("15:00 30-03-2019");

        mNames.add("Peter");
        mTimes.add("15:00 31-03-2019");

        mNames.add("Jay Chou");
        mTimes.add("16:00 30-03-2019");

        mNames.add("JJ Lin");
        mTimes.add("12:00 01-04-2019");

        mNames.add("Naruto Uzumaki");
        mTimes.add("15:00 30-04-2019");
        initRecyclerView();
    }
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.ReservationList);
        ReservationAdapterVendor adapter = new ReservationAdapterVendor(mNames, mTimes,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}