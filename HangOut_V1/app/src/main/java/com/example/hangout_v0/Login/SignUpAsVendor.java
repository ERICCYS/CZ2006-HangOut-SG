package com.example.hangout_v0.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Request;
import okhttp3.Response;

public class SignUpAsVendor extends AppCompatActivity {

    EditText firstNameEt, lastNameEt, emailEt, passwordEt;
    RadioGroup radioSexGroup;
    RadioButton selectedSexBtn;
    Button signUpBtn;

    String firstName, lastName, email, password, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_as_vendor);

        this.getSupportActionBar().hide();

        firstNameEt = findViewById(R.id.signUpAsVendorFirstName);
        lastNameEt = findViewById(R.id.signUpAsVendorLastName);
        emailEt = findViewById(R.id.signUpAsVendorEmail);
        passwordEt = findViewById(R.id.signUpAsVendorPassword);

        radioSexGroup = findViewById(R.id.signUpAsVendorSelectGender);
        signUpBtn = findViewById(R.id.signUpAsVendorSignUpBtn);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameEt.getText().toString();
                lastName = lastNameEt.getText().toString();
                email = emailEt.getText().toString();
                password = passwordEt.getText().toString();

                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                selectedSexBtn = findViewById(selectedId);
                gender = selectedSexBtn.getText().toString();

                JSONObject newVendor = new JSONObject();
                try {
                    newVendor.put("firstName", firstName);
                    newVendor.put("lastName", lastName);
                    newVendor.put("gender", gender);
                    newVendor.put("email", email);
                    newVendor.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestBody body = RequestBody.create(HangOutApi.JSON, newVendor.toString());
                OkHttpClient client = new OkHttpClient();
                String url = HangOutApi.baseUrl + "vendor";
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
                            SignUpAsVendor.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(SignUpAsVendor.this, "sign up successfully", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            SignUpAsVendor.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(SignUpAsVendor.this, "sign up failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                finish();
            }
        });

    }
}
