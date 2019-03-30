package com.example.hangout_v0.Me.plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.hangout_v0.Me.plan.PlanHistoryActivity;
import com.example.hangout_v0.R;

import java.util.ArrayList;

public class PlanDetailActivity extends AppCompatActivity {

    ListView shopsInPlansListView;
    ArrayList<String> shopName;
    ArrayList<String> shopAddress;
    ArrayList<String> shopDateTime;
    Button historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_plan_existing_plan);

        this.getSupportActionBar().hide();

        shopsInPlansListView =  findViewById(R.id.shopsInPlanListView);

//        Intent in= getIntent();
//        String planName= in.getStringExtra("PlanName");

        PlanDataStub planDataStub = new PlanDataStub("User1");
//
        shopName= planDataStub.getAllShopName("Plan A"); // planName to replace hardcode "Plan A"
        shopAddress = planDataStub.getAllShopAddress("Plan A");
        shopDateTime = planDataStub.getAllShopDateTime("Plan A");
//
        com.example.hangout_v0.Me.plan.ShopInPlanAdapter shopInPlanAdapter = new com.example.hangout_v0.Me.plan.ShopInPlanAdapter(this, shopName,shopAddress, shopDateTime);
        shopsInPlansListView.setAdapter(shopInPlanAdapter);

       shopsInPlansListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view,
                                   int position, long id) {
               shopName.remove(position);
               shopDateTime.remove(position);
               shopAddress.remove(position);
//               shopInPlanAdapter.notifyDataSetChanged();

           }
       });

       historyButton = findViewById(R.id.planHistoryButton);
       historyButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent myIntent = new Intent(PlanDetailActivity.this, PlanHistoryActivity.class);
               PlanDetailActivity.this.startActivity(myIntent);
           }
       });
    }

}
