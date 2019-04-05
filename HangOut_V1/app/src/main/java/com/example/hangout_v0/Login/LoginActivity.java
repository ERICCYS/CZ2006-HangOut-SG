package com.example.hangout_v0.Login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.R;
import com.example.hangout_v0.UserMainActivity;
import com.example.hangout_v0.Vendor.VendorMainActivity;
import com.example.hangout_v0.welcome_page.IntroActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private Button btn_skip;
    private Button btn_signup;
    private EditText userName;
    private EditText userPassword;
    private CheckBox cb_vendor;
    private boolean is_vendor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intro = new Intent(this, IntroActivity.class);
        startActivity(intro);

        init();

        this.getSupportActionBar().hide();
        btn_login = findViewById(R.id.login_login_button);
        btn_skip = findViewById(R.id.login_skip_button);
        cb_vendor = findViewById(R.id.login_vendorTunnel_checkbox);
        btn_signup = findViewById(R.id.login_signup_button);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!is_vendor) {
                    OkHttpClient client = new OkHttpClient();
                    String url = HangOutApi.baseUrl + "customer/signin";
                    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
                    httpBuilder.addQueryParameter("email", userName.getText().toString());
                    httpBuilder.addQueryParameter("password", userPassword.getText().toString());
                    Request request = new Request.Builder().url(httpBuilder.build()).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            LoginActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, "Incorrect customer account or password", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String myResponse = response.body().string();
                                System.out.println(myResponse);
                                Intent myIntent = new Intent(LoginActivity.this, UserMainActivity.class);
                                HangOutData.setAccessToken(myResponse);
                                Long userId = Long.parseLong(HangOutApi.getUserId(myResponse));

                                Bundle extras = new Bundle();
                                extras.putString("accessToken", myResponse);
                                extras.putLong("customer", userId);
                                HangOutApi.userId = userId;
                                HangOutApi.accessToken = myResponse;

                                myIntent.putExtras(extras);
                                startActivity(myIntent);
                            } else {
                                LoginActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "Incorrect customer account or password", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });

                } else {
                    OkHttpClient client = new OkHttpClient();
                    String url = HangOutApi.baseUrl + "vendor/signin";
                    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
                    httpBuilder.addQueryParameter("email", userName.getText().toString());
                    httpBuilder.addQueryParameter("password", userPassword.getText().toString());
                    Request request = new Request.Builder().url(httpBuilder.build()).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                        }

                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String myResponse = response.body().string();
                                System.out.println(myResponse);
                                HangOutApi.accessToken = myResponse;
                                Intent myIntent = new Intent(LoginActivity.this, VendorMainActivity.class);
                                Long vendorId;
                                vendorId = Long.parseLong(HangOutApi.getUserId(myResponse));
                                HangOutApi.userId = vendorId;
                                Bundle extras = new Bundle();
                                extras.putString("accessToken", myResponse);
                                extras.putLong("vendorId", vendorId);
                                myIntent.putExtras(extras);
                                startActivity(myIntent);
                            } else {
                                LoginActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "Incorrect vendor account or password", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });

                }
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!is_vendor) {
                    switchToUserSignUpPage();
                } else {
                    switchToVendorSignUpPage();
                }
            }
        });


        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToUserPage();
            }
        });

        cb_vendor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                is_vendor = isChecked;
            }
        });

    }

    private void init() {
        userName = (EditText) findViewById(R.id.login_userName_editText);
        userPassword = (EditText) findViewById(R.id.login_userPassword_editText);
        ImageView unameClear = (ImageView) findViewById(R.id.login_userName_clear);
        ImageView pwdClear = (ImageView) findViewById(R.id.login_userPassword_clear);

        com.example.hangout_v0.Login.EditTextClearTools.addClearListener(userName, unameClear);
        com.example.hangout_v0.Login.EditTextClearTools.addClearListener(userPassword, pwdClear);
    }

    private void switchToUserPage() {
        Intent myIntent = new Intent(this, UserMainActivity.class);
        startActivity(myIntent);
    }

    private void switchToUserSignUpPage() {
        Intent myIntent = new Intent(this, SignUpAsCustomer.class);
        startActivity(myIntent);
    }

    private void switchToVendorSignUpPage() {
        Intent myIntent = new Intent(this, SignUpAsVendor.class);
        startActivity(myIntent);
    }

}
