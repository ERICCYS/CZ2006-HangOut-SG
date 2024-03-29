package com.example.hangout_v0.ShopDetail;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.R;

import org.json.JSONArray;
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


public class SetPlanDetail extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText planName;
    Button submit;
    Button setTime;
    public int day, month, year, hour, minute, hour_x, minute_x;

    public String shopDateString = "null";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_new_plan);
        this.getSupportActionBar().hide();
        setTime = findViewById(R.id.newPlanSetTime);
        submit = findViewById(R.id.newPlanSubmit);
        planName = findViewById(R.id.newPlanName);

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SetPlanDetail.this, SetPlanDetail.this, year, month, day);
                datePickerDialog.show();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shopDateString.equals("null") || planName.getText().toString().equals("")) {
                    Toast.makeText(SetPlanDetail.this, "Set Date and plan name first!", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject newPlan = new JSONObject();
                    try {
                        newPlan.put("name", planName.getText());
                        newPlan.put("date", shopDateString);
                        JSONArray planItems = new JSONArray();
                        newPlan.put("planItems", planItems);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body = RequestBody.create(JSON, newPlan.toString());
                    OkHttpClient client = new OkHttpClient();
                    String url = HangOutApi.baseUrl + "customer/plan";
                    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
                    httpBuilder.addQueryParameter("customerId", HangOutApi.userId.toString());
                    Request request = new Request.Builder()
                            .url(httpBuilder.build())
                            .post(body)
                            .addHeader("Authorization", HangOutApi.accessToken)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            SetPlanDetail.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(SetPlanDetail.this, "Something goes wrong", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String myResponse = response.body().string();
                                SetPlanDetail.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(SetPlanDetail.this, "Plan successfully created", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                SetPlanDetail.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(SetPlanDetail.this, "Something goes wrong", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(SetPlanDetail.this, "Set date successfully " + shopDateString, Toast.LENGTH_SHORT).show();

    }
}
