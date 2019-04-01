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

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShopInDetail extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    AppCompatButton addPlanButton;
    AppCompatButton reserveButton;
    String shopDateString;
    String shopTimeString;
    int hour, minute, hour_x, minute_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_in_detail_user);

        Intent intent = getIntent();
        Long shopId = Long.parseLong(intent.getStringExtra("chosenShopId"));

        this.getSupportActionBar().hide();


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
                        JSONObject shopDetail = new JSONObject(myResponse);

                        try {
                            System.out.print("Get success");
                            Recshop shopDetail = new RecShop(
                                        jsonRecShop.getLong("Id"),
                                        jsonRecShop.getString("name"),
                                        jsonRecShop.getString("contactNumber"),
                                        jsonRecShop.getString("contactEmail"),
                                        jsonRecShop.getString("category"),
                                        jsonRecShop.getString("location"),
                                        jsonRecShop.getString("carParkNumbers"),
                                        jsonRecShop.getString("imageId")
                                );
                            }
                    } catch (JSONException e) {
                    }
                }
            }
        });


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
    }

    public void reserve(View view){
        setDateTime();
        //add reservation backend;
    }

    public void setDateTime(){
        DialogFragment datePicker = new com.example.hangout_v0.ShopDetail.DatePickerFragment();
        datePicker.show(getSupportFragmentManager(),"date picker");
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c= Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        shopDateString = dateFormat.format(c.getTime());

        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(ShopInDetail.this, ShopInDetail.this,
                hour,minute,true);
        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour_x =hourOfDay;
        minute_x = minute;
        shopTimeString = hour_x+":"+minute_x+":00";
        Toast.makeText(ShopInDetail.this,shopDateString+"\n"+ shopTimeString,Toast.LENGTH_SHORT).show();
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