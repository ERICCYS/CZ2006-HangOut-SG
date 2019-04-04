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
import com.example.hangout_v0.Me.plan.PlanDetailActivity;
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
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.hangout_v0.ApiCall.HangOutApi.JSON;


public class AddPlan extends AppCompatActivity {

    ListView plansListView;
    ArrayList<Long> planIds = new ArrayList<>();
    ArrayList<String> planNames = new ArrayList<>();
    ArrayList<String> planDateTimes = new ArrayList<>();
    Button addToNewPlan;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        this.getSupportActionBar().hide();
        plansListView = (ListView) findViewById(R.id.plansInPlanListView);

        Long customerId = Long.parseLong(HangOutApi.getUserId(HangOutData.getAccessToken()));

        plansListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showPlanDetailActivity
                        = new Intent(getApplicationContext(), PlanDetailActivity.class);
                showPlanDetailActivity.putExtra("PlanName",planNames.get(position));
                showPlanDetailActivity.putExtra("PlanId",planIds.get(position).toString());




//-------------------------------------------------------------------------------------
                //need to add codes here to PUT planItem into existing plan, then go into detail
                //something like this:


//                setDateTime();
//                Long planId = 1l;
//                JSONObject newPlanItem = new JSONObject();
//                try {
//                    newPlanItem.put("scheduledVisitTime", "2019-04-05 10:00:00");
//                    newPlanItem.put("shopId", shopId.toString());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//                RequestBody body = RequestBody.create(JSON, newPlanItem.toString());
//
//                OkHttpClient client = new OkHttpClient();
//                String url = HangOutApi.baseUrl + "customer/plan/addPlanItem";
//                HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
//                httpBuilder.addQueryParameter("planId", planId.toString());
//                Request request = new Request.Builder()
//                        .url(httpBuilder.build())
//                        .addHeader("Authorization", HangOutApi.accessToken)
//                        .post(body)
//                        .build();
//
//                client.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        if (response.isSuccessful()) {
//                            String myResponse = response.body().string();
//                            System.out.println(myResponse);
//                            // get full plan
//                        } else {
//                            System.out.println("");
//                        }
//                    }
//                });
//
//
// -------------------------------------------------------------------------------------

                startActivity(showPlanDetailActivity);
            }
        });

        //Add this item to a new plan
        addToNewPlan = findViewById(R.id.addToNewPlanButton);
        addToNewPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a new plan and add this item to it, not implemented yet
            }
        });




        //display existing plan list, no need change
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
                        AddPlan.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                PlanHistoryAdapter planHistoryAdapter = new PlanHistoryAdapter(AddPlan.this, planNames,planDateTimes);
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
