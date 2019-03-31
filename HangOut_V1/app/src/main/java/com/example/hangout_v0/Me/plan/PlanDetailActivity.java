package com.example.hangout_v0.Me.plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.Me.plan.PlanHistoryActivity;
import com.example.hangout_v0.Me.reservation.ReservationActivity;
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

public class PlanDetailActivity extends AppCompatActivity {

    ListView shopsInPlansListView;
    private ArrayList<String> shopName = new ArrayList<>();
    private ArrayList<String> shopAddress = new ArrayList<>();
    private ArrayList<String> shopDateTime = new ArrayList<>();
    Button historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_plan_existing_plan);

        this.getSupportActionBar().hide();

        shopsInPlansListView =  findViewById(R.id.shopsInPlanListView);

        Intent in= getIntent();
        String planName= in.getStringExtra("PlanName");

//        PlanDataStub planDataStub = new PlanDataStub("User1");
//
//        shopName= planDataStub.getPlanShopName(planName);
//        shopAddress = planDataStub.getPlanShopAddress(planName);
//        shopDateTime = planDataStub.getPlanShopDateTime(planName);


        // My suggestion is that we should see the plans histories (a list of plans)
        // Then, see the plans detail, (shops etc.)
        // Because after we get all the plans, we know each plan id
        // here we use a dummy planId
        Long planId = 3l;


        OkHttpClient client = new OkHttpClient();
        String url = HangOutApi.baseUrl + "plan-items-formatted";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        httpBuilder.addQueryParameter("planId", planId.toString());
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
                        JSONArray planItems = new JSONArray(myResponse);
                        System.out.println(planItems);
                        for (int i = 0; i < planItems.length(); i++) {
                            JSONObject planItem = (JSONObject)planItems.get(i);
                            shopDateTime.add(planItem.get("shopDateTime").toString());
                            shopName.add(planItem.get("shopName").toString());
                            shopAddress.add(planItem.get("shopAddress").toString());
                        }
                        PlanDetailActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                com.example.hangout_v0.Me.plan.ShopInPlanAdapter shopInPlanAdapter = new com.example.hangout_v0.Me.plan.ShopInPlanAdapter(PlanDetailActivity.this, shopName,shopAddress, shopDateTime);
                                shopsInPlansListView.setAdapter(shopInPlanAdapter);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


//
//        com.example.hangout_v0.Me.plan.ShopInPlanAdapter shopInPlanAdapter = new com.example.hangout_v0.Me.plan.ShopInPlanAdapter(this, shopName,shopAddress, shopDateTime);
//        shopsInPlansListView.setAdapter(shopInPlanAdapter);


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
