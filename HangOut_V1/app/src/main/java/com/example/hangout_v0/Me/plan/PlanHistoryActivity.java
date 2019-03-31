package com.example.hangout_v0.Me.plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.Me.plan.PlanHistoryAdapter;
import com.example.hangout_v0.R;

import java.util.ArrayList;


public class PlanHistoryActivity extends AppCompatActivity {

    ListView plansListView;
    ArrayList<String> planName;
    ArrayList<String> planDateTime;

    public static PlanDataStub planDataStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_plan_plan_history);

        this.getSupportActionBar().hide();

        planDataStub = new PlanDataStub("user");
        plansListView = (ListView) findViewById(R.id.planListView);

        planName = planDataStub.getAllPlanName();
        planDateTime =  planDataStub.getAllPlanDateTime();

        PlanHistoryAdapter planHistoryAdapter = new PlanHistoryAdapter(this, planName,planDateTime);
        plansListView.setAdapter(planHistoryAdapter);

        plansListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(),"This is a past plan",Toast.LENGTH_SHORT).show();
                Intent showPlanDetailActivity
                        = new Intent(getApplicationContext(),PlanDetailActivity.class);
                showPlanDetailActivity.putExtra("PlanName",planName.get(position)); //todo 1 change to position
                startActivity(showPlanDetailActivity);
            }
        });

    }
}
