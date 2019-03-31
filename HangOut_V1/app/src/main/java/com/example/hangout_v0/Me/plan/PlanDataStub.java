package com.example.hangout_v0.Me.plan;

import android.app.DownloadManager;
import android.widget.Toast;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.Me.plan.PlanHistoryAdapter;
import com.example.hangout_v0.Vendor.AddShop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.hangout_v0.ApiCall.HangOutApi.baseUrl;

public class PlanDataStub {
//    Long id = new Long(1);
//            HangOutApi.getCustomer(1l);
//    JSONObject customer = HangOutData.getCustomer();
//            customer.getJSONArray("plan");

    ArrayList<String> planName = new ArrayList<String>();
    ArrayList<String> planDateTime = new ArrayList<String>();
    ArrayList<String> shopName = new ArrayList<String>();
    ArrayList<String> shopAddress = new ArrayList<String>();
    ArrayList<String> shopDateTime = new ArrayList<String>();
    JSONArray plans;
    JSONArray planItems;
    JSONArray customerReservations;
    Long customerId = new Long(1);

    public PlanDataStub(String user) {

        //get user

        OkHttpClient client = new OkHttpClient();
        String url = baseUrl + "customer";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        httpBuilder.addQueryParameter("customerId", customerId.toString());
        Request request = new Request.Builder().url(httpBuilder.build()).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
                    System.out.println(myResponse);
                    try {
                        JSONObject customer = new JSONObject(myResponse);
                        plans = (JSONArray) customer.get("plans");
                        HangOutData.setPlanList(plans);

                        // Deal with this object, below is an example
                        //textView.setText(plans.toString());

                        for (int i = 0; i < plans.length(); i++) {
                            JSONObject obj = plans.getJSONObject(i);
                            String name = (String) obj.get("name");
                            String date = (String) obj.get("date");
                            planName.add(name);
                            planDateTime.add(date);
                        }
                    }
                    catch (JSONException e) {
                        //textView.setText("Error");
                    }
                }
            }
        });

}
            //JSONArray allPlan = HangOutData.getPlanList();


    public ArrayList<String> getAllPlanName(){
        return planName;
    }

    public ArrayList<String> getAllPlanDateTime(){
        return planDateTime;
    }

    public void setPlanItems(String planName) {
        try {
            for (int i = 0; i < plans.length(); i++) {
                JSONObject obj = plans.getJSONObject(i);
                String tempName = (String) obj.get("name");
                if (planName.equals(tempName)) {
                    planItems = (JSONArray) obj.get("planItems");
                }
            }
            shopName.clear();
            shopAddress.clear();
            shopDateTime.clear();
            for (int i = 0; i < planItems.length(); i++) {
                JSONObject obj = planItems.getJSONObject(i);
                Long shopId = (Long) obj.get("shopId");
                String shopVisitTime = (String) obj.get("scheduledVisitTime");
                shopDateTime.add(shopVisitTime);

                //getshop by shopId
                OkHttpClient client = new OkHttpClient();
                String url = baseUrl + "shop";
                HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
                httpBuilder.addQueryParameter("shopId", shopId.toString());

                Request request = new Request.Builder().url(httpBuilder.build()).build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String myResponse = response.body().string();
                            System.out.println(myResponse);
                            try {
                                JSONObject object = new JSONObject(myResponse);
                                HangOutData.setShop(object);
                                //textView.setText("Shop with id = " + shopId + " is " + object);
                                shopName.add(object.getString("name"));
                                shopAddress.add(object.getString("location"));

                            } catch (JSONException e) {
                                //textView.setText("Error");
                            }
                        }
                    }
                });
            }
        } catch (JSONException e){
            //todo
        }
    }


    public ArrayList<String> getPlanShopName(String planName)  {
        setPlanItems(planName);
        return shopName;
    }

    public ArrayList<String> getPlanShopAddress(String planName)  {
        setPlanItems(planName);
        return shopAddress;
    }

    public ArrayList<String> getPlanShopDateTime(String planName) {
        setPlanItems(planName);
        return shopDateTime;
    }

    public ArrayList<String> getReservationShopName()  {
        setReservation();
        return shopName;
    }

    public ArrayList<String> getReservationShopAddress()  {
        setReservation();
        return shopAddress;
    }

    public ArrayList<String> getReservationShopDateTime() {
        setReservation();
        return shopDateTime;
    }

    public void setReservation() {

        OkHttpClient client = new OkHttpClient();
        String url = baseUrl + "reservation-customer";
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
                        customerReservations = new JSONArray(myResponse);
                        HangOutData.setReservationList(customerReservations);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //textView.setText("Customer now have reservation " + myResponse);
                    // Able to get the access token.

                }
            }
        });
        setReservationDetail();
    }


    public void setReservationDetail() {
        try {
            shopName.clear();
            shopAddress.clear();
            shopDateTime.clear();
            for (int i = 0; i < customerReservations.length(); i++) {
                JSONObject obj = customerReservations.getJSONObject(i);
                Long shopId = (Long) obj.get("shopId");
                String shopReserveTime = (String) obj.get("arrivalTime");
                shopDateTime.add(shopReserveTime);

                //getshop by shopId
                OkHttpClient client = new OkHttpClient();
                String url = baseUrl + "shop";
                HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
                httpBuilder.addQueryParameter("shopId", shopId.toString());

                Request request = new Request.Builder().url(httpBuilder.build()).build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String myResponse = response.body().string();
                            System.out.println(myResponse);
                            try {
                                JSONObject object = new JSONObject(myResponse);
                                HangOutData.setShop(object);
                                //textView.setText("Shop with id = " + shopId + " is " + object);
                                shopName.add(object.getString("name"));
                                shopAddress.add(object.getString("location"));

                            } catch (JSONException e) {
                                //textView.setText("Error");
                            }
                        }
                    }
                });
            }
        } catch (JSONException e){
            //todo
        }
    }



}

