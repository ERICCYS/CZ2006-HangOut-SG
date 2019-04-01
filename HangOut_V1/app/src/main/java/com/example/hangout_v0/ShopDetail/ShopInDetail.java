package com.example.hangout_v0.ShopDetail;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.R;
import com.example.hangout_v0.Recommendation.RecShop;


import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.jar.JarException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.hangout_v0.ApiCall.HangOutApi.JSON;
import static com.example.hangout_v0.ApiCall.HangOutData.accessToken;

public class ShopInDetail extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {


    AppCompatButton addPlanButton;
    AppCompatButton reserveButton;
    String shopDateString;
    String shopTimeString;
    String shopDateTimeString;
    int hour, minute, hour_x, minute_x;
    JSONObject jsonRecShop;
    RecShop shopDetail;
    TextView shopNameTv, shopLocation, phoneNo, email;
    RatingBar ratingBar;
    ImageView img1, img2, img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_in_detail_user);

//        Intent intent = getIntent();
//        Long shopId = Long.parseLong(intent.getStringExtra("chosenShopId"));
        final Long shopId = Long.valueOf(2);

        this.getSupportActionBar().hide();

        // find views
        shopNameTv = findViewById(R.id.shopNameTextView);
        shopLocation = findViewById(R.id.shopLocationTextView);
        phoneNo = findViewById(R.id.shopPhoneNumberTextView);
        email = findViewById(R.id.shopWebTextView);
        ratingBar = findViewById(R.id.shopRatingBar);
        img1 = findViewById(R.id.shopPhoto1);
        img2 = findViewById(R.id.shopPhoto2);
        img3 = findViewById(R.id.shopPhoto3);

        if(shopId == 2){
            img1.setImageDrawable(getResources().getDrawable(R.drawable.huoguo));
            img2.setImageDrawable(getResources().getDrawable(R.drawable.huoguo2));
            img3.setImageDrawable(getResources().getDrawable(R.drawable.huoguo3));
        }
        else{
            img1.setImageDrawable(getResources().getDrawable(R.drawable.macd1));
            img2.setImageDrawable(getResources().getDrawable(R.drawable.macd2));
            img3.setImageDrawable(getResources().getDrawable(R.drawable.macd3));
        }

        ////
        OkHttpClient client = new OkHttpClient();
        String url = HangOutApi.baseUrl + "shop";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        httpBuilder.addQueryParameter("shopId", shopId.toString());
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
                    System.out.println(myResponse);
                    try {
                        JSONObject jsonShop = new JSONObject(myResponse);
                        HangOutData.setShop(jsonShop);
                        jsonRecShop = HangOutData.getShop();
                        try {
                            System.out.print("Get success");

                             shopDetail = new RecShop(

                                    jsonRecShop.get("id").toString(),
                                    jsonRecShop.get("name").toString(),
                                    jsonRecShop.get("contactNumber").toString(),
                                    jsonRecShop.get("contactEmail").toString(),
                                    jsonRecShop.get("category").toString(),
                                    jsonRecShop.get("location").toString(),
                                    jsonRecShop.get("carParkNumbers").toString()
                            );


                            shopNameTv.setText(shopDetail.getName());
                            shopLocation.setText(shopDetail.getLocation());
                            phoneNo.setText(shopDetail.getContactNumber());
                            ratingBar.setNumStars(shopDetail.getRating().intValue());
                            email.setText(shopDetail.getContactEmail());


                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        //textView.setText("Error");
                        // set xml view
                    }

                }
                else{
                    System.out.println("Response is not successful");
                }
            }

        });

        //print data
//        System.out.println(shopDetail.getName());

        ////
//        OkHttpClient client = new OkHttpClient();
//        String url = HangOutApi.baseUrl + "shop";
//        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
//        httpBuilder.addQueryParameter("shopId", shopId.toString());
//
//        Request request = new Request.Builder().url(httpBuilder.build()).build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    String myResponse = response.body().string();
//                    System.out.println(myResponse);
//                    try {
//                        JSONObject jsonShopDeatil = new JSONObject(myResponse);
//
//                        try {
//                            System.out.print("Get success");
//                            RecShop shopDetail = new RecShop(
//
//                                        jsonRecShop.getString("name"),
//                                        jsonRecShop.getString("contactNumber"),
//                                        jsonRecShop.getString("contactEmail"),
//                                        jsonRecShop.getString("category"),
//                                        jsonRecShop.getString("location"),
//                                        jsonRecShop.getString("carParkNumbers"),
//                                        jsonRecShop.getString("imageId")
//                                );
//
//                    } catch (JSONException e) {
//                    }
//                }
//            }
//        });


//
//        ListView shopDishListView = (ListView) findViewById(R.id.shopDishListView);
//        com.example.hangout_v0.Recommendation.ShopInListAdapter shopDishAdapter = new com.example.hangout_v0.Recommendation.ShopInListAdapter(this,name,description,imgid,rating,distance,carParkCapacity);
//        shopDishListView.setAdapter(shopDishAdapter);

        final FloatingActionButton addPlanFloatingActionButton = (FloatingActionButton) findViewById(R.id.addPlanFloatingActionButton);
        addPlanFloatingActionButton.setImageResource(R.drawable.ic_add_plan_not_sel);
        final FloatingActionButton addCollectionFloatingActionButton = (FloatingActionButton) findViewById(R.id.addCollectionFloatingActionButton);
        addCollectionFloatingActionButton.setImageResource(R.drawable.ic_add_collection_not_sel);


        addCollectionFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCollectionFloatingActionButton.setImageResource(R.drawable.ic_add_collection_sel);
            }
        });

    }

    public void addToPlan(View view) {
        setDateTime();
        //add to plan backend;

        Long planId = 1l;
        Long shopId = 2l;

        JSONObject newPlanItem = new JSONObject();
        try {
            newPlanItem.put("scheduledVisitTime", "2019-04-05 10:00:00");
            newPlanItem.put("shopId", shopId.toString());
        } catch(JSONException e){
            e.printStackTrace();
        }


        RequestBody body = RequestBody.create(JSON, newPlanItem.toString());

        OkHttpClient client = new OkHttpClient();
        String url = HangOutApi.baseUrl + "customer/plan/addPlanItem";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        httpBuilder.addQueryParameter("planId", planId.toString());
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader("Authorization", "kldMaIf99i6G+0JvLQGwfw==")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
                    System.out.println(myResponse);
                    // get full plan
                } else {
                    System.out.println("*************************");
                }
            }
        });



    }

    public void reserve(View view) {
        setDateTime();
        //add reservation backend;

        JSONObject newReservation = new JSONObject();
        try {
            newReservation.put("shopId", "2");
            newReservation.put("arrivalTime", "2019-04-05 10:00:00");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Long shopId = 2l;
        Long customerId = 3l;

        RequestBody body = RequestBody.create(JSON, newReservation.toString());
        OkHttpClient client = new OkHttpClient();
        String url = HangOutApi.baseUrl + "reservation";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        httpBuilder.addQueryParameter("customerId", customerId.toString());
        httpBuilder.addQueryParameter("shopId", shopId.toString());
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .post(body)
                .addHeader("Authorization", "kldMaIf99i6G+0JvLQGwfw==")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
                  //  textView.setText("Customer add reservation Successfully, here is the new reservation " + myResponse);
                }
            }
        });
    }


    public void setDateTime() {
        DialogFragment datePicker = new com.example.hangout_v0.ShopDetail.DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        shopDateString = dateFormat.format(c.getTime());

        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(ShopInDetail.this, ShopInDetail.this,
                hour, minute, true);
        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour_x = hourOfDay;
        minute_x = minute;

        String hours = String.format("%02d",hour_x);
        String minutes = String.format("%02d",minute_x);

        shopTimeString = hours + ":" + minutes + ":00";
        shopDateTimeString = shopDateString + " "+ shopTimeString;
        Toast.makeText(ShopInDetail.this, "Add shop successully " + shopDateTimeString, Toast.LENGTH_SHORT).show();
    }
}

//        ImageView shopPhoto1 = (ImageView) findViewById(R.id.shopPhoto1);
//        ImageView shopPhoto2 = (ImageView) findViewById(R.id.shopPhoto2);
//        ImageView shopPhoto3 = (ImageView) findViewById(R.id.shopPhoto3);
//
//        shopPhoto1.setImageResource(R.drawable.image1);
//        shopPhoto2.setImageResource(R.drawable.image2);
//        shopPhoto3.setImageResource(R.drawable.image3);

// later call API to get data
//        String[] name = {"peach","mango","apple","pear","watermelon","cherry"};
//        String[] description = {"She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
//                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
//                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
//                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
//                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
//                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. "};
//        Integer[] imgid = {R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6};
//        Float[] rating = {4.0f,3.0f,4.3f,4.0f,4.2f,4.1f};
//        String[] distance = {" ","","" ,"","",""};
//        String[] carParkCapacity = {"","","","","",""};