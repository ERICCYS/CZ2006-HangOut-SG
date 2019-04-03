package com.example.hangout_v0.Me.plan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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


public class PlanHistoryActivity extends AppCompatActivity {

    ListView plansListView;
    ArrayList<Long> planIds = new ArrayList<>();
    ArrayList<String> planNames = new ArrayList<>();
    ArrayList<String> planDateTimes = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_plan_plan_history);
        this.getSupportActionBar().hide();
        plansListView = (ListView) findViewById(R.id.planListView);

        Long customerId = Long.parseLong(HangOutApi.getUserId(HangOutData.getAccessToken()));

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
                            JSONObject plan = (JSONObject)plans.get(i);
                            planIds.add(Long.parseLong(plan.get("planId").toString()));
                            planNames.add((String) plan.get("planName"));
                            planDateTimes.add((String)plan.get("planDateTime"));
                        }
                        PlanHistoryActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                PlanHistoryAdapter planHistoryAdapter = new PlanHistoryAdapter(PlanHistoryActivity.this, planNames,planDateTimes);
                                plansListView.setAdapter(planHistoryAdapter);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        plansListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showPlanDetailActivity
                        = new Intent(getApplicationContext(),PlanDetailActivity.class);
                showPlanDetailActivity.putExtra("PlanName",planNames.get(position));
                showPlanDetailActivity.putExtra("PlanId",planIds.get(position).toString());
                startActivity(showPlanDetailActivity);
            }
        });

    }
}
