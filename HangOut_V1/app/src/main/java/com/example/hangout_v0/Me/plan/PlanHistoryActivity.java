package com.example.hangout_v0.Me.plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hangout_v0.Me.plan.PlanHistoryAdapter;
import com.example.hangout_v0.R;


public class PlanHistoryActivity extends AppCompatActivity {

    ListView plansListView;
    String[] planName;
    String[] planDescription;
    String[] planDateTime;

    public static PlanDataStub planDataStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_plan_plan_history);

        this.getSupportActionBar().hide();

        planDataStub = new PlanDataStub("user");
        plansListView = (ListView) findViewById(R.id.planListView);

        planName = planDataStub.getAllPlanName();
        planDescription = planDataStub.getAllPlanDescription();
        planDateTime =  planDataStub.getAllPlanDateTime();

        PlanHistoryAdapter planHistoryAdapter = new PlanHistoryAdapter(this, planName, planDescription,planDateTime);
        plansListView.setAdapter(planHistoryAdapter);

        plansListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),"This is a past plan",Toast.LENGTH_SHORT);
            }
        });

    }
}
