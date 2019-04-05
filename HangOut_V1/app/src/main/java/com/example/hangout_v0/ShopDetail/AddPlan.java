package com.example.hangout_v0.ShopDetail;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.Me.plan.PlanHistoryAdapter;
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


public class AddPlan extends AppCompatActivity {

    ListView plansListView;
    ArrayList<Long> planIds = new ArrayList<>();
    ArrayList<String> planNames = new ArrayList<>();
    ArrayList<String> planDateTimes = new ArrayList<>();
    Button addToNewPlan;
    String shopId;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);

        shopId = getIntent().getStringExtra("shopId");

        this.getSupportActionBar().hide();
        plansListView = (ListView) findViewById(R.id.plansInPlanListView);

        Long customerId = Long.parseLong(HangOutApi.getUserId(HangOutData.getAccessToken()));

        plansListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent planItemConfirmPage = new Intent(getApplicationContext(), PlanItemConfirmPage.class);
                planItemConfirmPage.putExtra("shopId", shopId);
                planItemConfirmPage.putExtra("planId", planIds.get(position).toString());
                startActivity(planItemConfirmPage);
            }
        });

        addToNewPlan = findViewById(R.id.addToNewPlanButton);
        addToNewPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setPlanDetail = new Intent(AddPlan.this, SetPlanDetail.class);
                startActivity(setPlanDetail);
            }
        });


        OkHttpClient client = new OkHttpClient();
        String url = HangOutApi.baseUrl + "customer-plan-formatted";
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
                        JSONArray plans = new JSONArray(myResponse);
                        for (int i = 0; i < plans.length(); i++) {
                            JSONObject plan = (JSONObject) plans.get(i);
                            planIds.add(Long.parseLong(plan.get("planId").toString()));
                            planNames.add((String) plan.get("planName"));
                            planDateTimes.add((String) plan.get("planDateTime"));
                        }
                        AddPlan.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                PlanHistoryAdapter planHistoryAdapter = new PlanHistoryAdapter(AddPlan.this, planNames, planDateTimes);
                                plansListView.setAdapter(planHistoryAdapter);
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
