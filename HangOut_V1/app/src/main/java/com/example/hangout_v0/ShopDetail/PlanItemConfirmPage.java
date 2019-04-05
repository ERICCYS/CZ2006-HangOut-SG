package com.example.hangout_v0.ShopDetail;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.hangout_v0.ApiCall.HangOutApi.JSON;

public class PlanItemConfirmPage extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    Button submit;
    Button setTime;
    public int day, month, year, hour, minute, hour_x, minute_x;
    public String planId;
    public String shopId;
    public String shopTimeString, shopDateTimeString = "null", shopDateString;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_item_confirm);
        // get shopId from add plan (further from shop detail)
        shopId = getIntent().getStringExtra("shopId");
        planId = getIntent().getStringExtra("planId");
        this.getSupportActionBar().hide();
        setTime = findViewById(R.id.planItemSetTime);
        submit = findViewById(R.id.confirmPlanItemSubmit);


        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(PlanItemConfirmPage.this, PlanItemConfirmPage.this, year, month, day);
                datePickerDialog.show();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // put to backend
                if (shopDateTimeString.equals("null")) {
                    Toast.makeText(PlanItemConfirmPage.this, "Set Date and time first!", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject newPlanItem = new JSONObject();
                    try {
                        newPlanItem.put("scheduledVisitTime", shopDateTimeString);
                        newPlanItem.put("shopId", shopId);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body = RequestBody.create(JSON, newPlanItem.toString());
                    OkHttpClient client = new OkHttpClient();
                    String url = HangOutApi.baseUrl + "/customer/plan/addPlanItem";
                    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
                    httpBuilder.addQueryParameter("planId", planId);

                    Request request = new Request.Builder()
                            .url(httpBuilder.build())
                            .post(body)
                            .addHeader("Authorization", HangOutApi.accessToken)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            PlanItemConfirmPage.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(PlanItemConfirmPage.this, "Something goes wrong", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                System.out.println("success add");
                                PlanItemConfirmPage.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(PlanItemConfirmPage.this, "Plan item successfully added to this plan", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                System.out.println("failed add 2");
                                PlanItemConfirmPage.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(PlanItemConfirmPage.this, "Something goes wrong", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                    finish();
                }
            }
        });


    }

    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        shopDateString = dateFormat.format(c.getTime());
        TimePickerDialog timePickerDialog = new TimePickerDialog(PlanItemConfirmPage.this, PlanItemConfirmPage.this,
                hour, minute, true);
        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        hour_x = hourOfDay;
        minute_x = minute;

        String hours = String.format("%02d", hour_x);
        String minutes = String.format("%02d", minute_x);

        shopTimeString = hours + ":" + minutes + ":00";
        shopDateTimeString = shopDateString + " " + shopTimeString;
        Toast.makeText(PlanItemConfirmPage.this, "Set time successfully " + shopDateTimeString, Toast.LENGTH_SHORT).show();
    }
}
