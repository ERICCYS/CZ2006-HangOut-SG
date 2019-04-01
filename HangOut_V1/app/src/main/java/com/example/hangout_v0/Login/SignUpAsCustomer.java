package com.example.hangout_v0.Login;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpAsCustomer extends AppCompatActivity {

    EditText firstNameEt, lastNameEt, emailEt, passwordEt, regionEt;
    RadioGroup radioSexGroup;
    RadioButton selectedSexBtn;
    Button signUpBtn;
    CheckBox halalCb, vegCb;
    private TextView DOBtv;
    private DatePickerDialog.OnDateSetListener signupDateSetListener;

    String firstName, lastName, email, password, region, dob, gender;
    Boolean halal = false, veg = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_as_customer);

        this.getSupportActionBar().hide();

        // set all buttons
        firstNameEt = findViewById(R.id.signUpAsCustomerFirstName);
        lastNameEt = findViewById(R.id.signUpAsCustomerLastName);
        emailEt = findViewById(R.id.signUpAsCustomerEmail);
        passwordEt = findViewById(R.id.signUpAsCustomerPassword);
        regionEt = findViewById(R.id.signUpAsCustomerRegion);

        radioSexGroup = findViewById(R.id.signUpAsCustomerSelectGender);
        signUpBtn = findViewById(R.id.signUpAsCustomerSignUpBtn);

        halalCb = findViewById(R.id.signUpAsCustomerHalalOption);
        vegCb = findViewById(R.id.signUpAsCustomerVegOption);

        DOBtv = findViewById(R.id.signUpAsCustomerSelectDOB);

        //

        DOBtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        SignUpAsCustomer.this,
                        android.R.style.Theme_DeviceDefault,
                        signupDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                dialog.show();
            }
        });

        signupDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                dob = Integer.toString(year) + '-' + String.format("%02d", month)  + '-' + String.format("%02d", dayOfMonth);
                DOBtv.setText(dob);
            }
        };

        // check for basic error handling
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameEt.getText().toString();
                lastName = lastNameEt.getText().toString();
                email = emailEt.getText().toString();
                password = passwordEt.getText().toString();
                region = regionEt.getText().toString();

                // sex
                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                selectedSexBtn = findViewById(selectedId);
                gender = selectedSexBtn.getText().toString().toUpperCase();

                // store data
                JSONObject newCustomer = new JSONObject();
                try {
                    newCustomer.put("firstName", firstName);
                    newCustomer.put("lastName", lastName);
                    newCustomer.put("gender", gender);
                    newCustomer.put("email", email);
                    newCustomer.put("password", password);
                    newCustomer.put("avatar", "https://help.salesforce.com/resource/1552721177000/HelpStaticResource/assets/images/tdxDefaultUserLogo.png");
                    newCustomer.put("dob", dob);
                    newCustomer.put("halaPreference", halal);
                    newCustomer.put("vegPreference", veg);
                    newCustomer.put("regionalPreference", region);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Call API to sign up
                System.out.println(newCustomer);
                RequestBody body = RequestBody.create(HangOutApi.JSON, newCustomer.toString());
                OkHttpClient client = new OkHttpClient();
                String url = HangOutApi.baseUrl + "customer";
                Request request = new Request.Builder()
                        .url(url)
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
                            HangOutData.setAccessToken(myResponse);
                            SignUpAsCustomer.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(SignUpAsCustomer.this, "sign up successfully", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            SignUpAsCustomer.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(SignUpAsCustomer.this, "sign up failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

//                Toast.makeText(SignUpAsCustomer.this,"sign up successfully", Toast.LENGTH_SHORT).show();

                finish();
            }
        });


        halalCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    halal = true;
                } else {
                    halal = false;
                }
            }
        });

        vegCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    veg = true;
                } else {
                    veg = false;
                }
            }
        });
    }
}
