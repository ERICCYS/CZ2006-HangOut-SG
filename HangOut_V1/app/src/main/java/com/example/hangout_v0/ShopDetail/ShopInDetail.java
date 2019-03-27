package com.example.hangout_v0.ShopDetail;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import com.example.hangout_v0.R;

import java.text.DateFormat;
import java.util.Calendar;

public class ShopInDetail extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    AppCompatButton addPlanButton;
    AppCompatButton reserveButton;
    String planDateString;
    int hour, minute, hour_x, minute_x;
    String ampm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_in_detail_user);

        this.getSupportActionBar().hide();

        ImageView shopPhoto1 = (ImageView) findViewById(R.id.shopPhoto1);
        ImageView shopPhoto2 = (ImageView) findViewById(R.id.shopPhoto2);
        ImageView shopPhoto3 = (ImageView) findViewById(R.id.shopPhoto3);

        shopPhoto1.setImageResource(R.drawable.image1);
        shopPhoto2.setImageResource(R.drawable.image2);
        shopPhoto3.setImageResource(R.drawable.image3);

        // later call API to get data
        String[] name = {"peach","mango","apple","pear","watermelon","cherry"};
        String[] description = {"She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. "};
        Integer[] imgid = {R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6};
        Float[] rating = {4.0f,3.0f,4.3f,4.0f,4.2f,4.1f};
        String[] distance = {"4.0","3.0","4.3","4.0","4.2","4.1"};
        String[] carParkCapacity = {"90%","80%","80%","80%","80%","80%"};


        ListView shopDishListView = (ListView) findViewById(R.id.shopDishListView);
        com.example.hangout_v0.Recommendation.ShopInListAdapter shopDishAdapter = new com.example.hangout_v0.Recommendation.ShopInListAdapter(this,name,description,imgid,rating,distance,carParkCapacity);
        shopDishListView.setAdapter(shopDishAdapter);

        final FloatingActionButton addPlanFloatingActionButton = (FloatingActionButton) findViewById(R.id.addPlanFloatingActionButton);
        addPlanFloatingActionButton.setImageResource(R.drawable.ic_add_plan_not_sel);
        final FloatingActionButton addCollectionFloatingActionButton = (FloatingActionButton) findViewById(R.id.addCollectionFloatingActionButton);
        addCollectionFloatingActionButton.setImageResource(R.drawable.ic_add_collection_not_sel);

//        addPlanFloatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (addPlanFloatingActionButton.getIm==R.drawable.ic_add_plan_sel)
//                    addPlanFloatingActionButton.setImageResource(R.drawable.ic_add_plan_not_sel);
//                else
//                    addPlanFloatingActionButton.setImageResource(R.drawable.ic_add_plan_sel);
//            }
//        });


        addCollectionFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCollectionFloatingActionButton.setImageResource(R.drawable.ic_add_collection_sel);
            }
        });
    }

    public void addToPlan(View view) {
        DialogFragment datePicker = new com.example.hangout_v0.ShopDetail.DatePickerFragment();
        datePicker.show(getSupportFragmentManager(),"date picker");
    }

    public void reserve(View view){
        Toast.makeText(ShopInDetail.this,"This is reserve button",Toast.LENGTH_SHORT).show();
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c= Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        planDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(ShopInDetail.this, ShopInDetail.this,
                hour,minute,false);
        timePickerDialog.show();

    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour_x =hourOfDay;
        minute_x = minute;
        ampm= hourOfDay<12? "AM":"PM";
        Toast.makeText(ShopInDetail.this,planDateString+"\n"+"hour: "+hour_x+"  minute"+minute_x+ "  "+ ampm,Toast.LENGTH_SHORT).show();
    }
}
