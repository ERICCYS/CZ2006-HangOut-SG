package com.example.hangout_v0.Me.avator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.MalformedParametersException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserProfile extends AppCompatActivity {

    // all buttons
    Button editProfile;
    ImageView avatar;
    TextView nameTv, genderTv, dobTv, halalOpTv, vegOpTv, regionalTv, emailTv;

    // all customer info
    private String firstname, lastname, gender, email, avatarUrl, dob, hahalpref, vegpref, regionalpref;
    Long customerId = Long.valueOf(3);
    JSONObject customer;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_profile);

        this.getSupportActionBar().hide();

        // find all views
        nameTv = findViewById(R.id.customerNameText);
        genderTv = findViewById(R.id.genderText);
        dobTv = findViewById(R.id.dobText);
        halalOpTv = findViewById(R.id.halalText);
        vegOpTv = findViewById(R.id.vegText);
        regionalTv = findViewById(R.id.regionText);
        emailTv = findViewById(R.id.emailText);
        editProfile = findViewById(R.id.editProfileButton);
        avatar = findViewById(R.id.customerAvatar);

        // set all customer info to original ones on the edit page
        String accessToken = HangOutData.getAccessToken();
        customerId = Long.parseLong(HangOutApi.getUserId(accessToken));
        HangOutApi.getCustomer(customerId);



        OkHttpClient client = new OkHttpClient();
        String url = HangOutApi.baseUrl + "customer";
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
                        JSONObject customer = new JSONObject(myResponse);

                        HangOutData.setCustomer(customer);

                        customer = HangOutData.getCustomer();
                        try {
                            firstname = customer.get("firstName").toString();
                            lastname = customer.get("lastName").toString();
                            Log.d("nameInTry",firstname+lastname);
                            gender = customer.get("gender").toString();
                            email = customer.get("email").toString();
                            avatarUrl= customer.get("avatar").toString();
                            dob = customer.get("dob").toString();
                            hahalpref = customer.get("halaPreference").toString();
                            vegpref = customer.get("vegPreference").toString();
                            regionalpref = customer.get("regionalPreference").toString();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                        UserProfile.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // set all text to info
                                Log.d("name",firstname+lastname);
                                nameTv.setText(firstname+" "+lastname);
                                genderTv.setText("Gender: "+gender);
                                emailTv.setText("Email: "+email);
                                dobTv.setText("DOB: "+dob);
                                if(hahalpref == "true")
                                    halalOpTv.setText("Hahal Option: Yes");
                                else
                                    halalOpTv.setText("Hahal Option: No");
                                if(vegpref == "true")
                                    vegOpTv.setText("Vegitarian Option: Yes");
                                else
                                    vegOpTv.setText("Vegitarian Option: No");
                                regionalTv.setText("Regional Info: "+regionalpref);
                                if(avatarUrl == null)
                                    avatarUrl = "https://iupac.org/wp-content/uploads/2018/05/default-avatar-768x768.png";
                                // set avatar from url
                                new DownloadImageFromInternet(avatar).execute(avatarUrl);
                            }
                        });

                    } catch (JSONException e) {
                        //textView.setText("Error");
                    }
                }
            }
        });

//        RequestBody body = RequestBody.create(HangOutApi.JSON, updatedCustomer);
//        OkHttpClient client = new OkHttpClient();
//        String url = HangOutApi.baseUrl + "customer";
//        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
//        httpBuilder.addQueryParameter("customerId", customerId.toString());
//        Request request = new Request.Builder()
//                .url(httpBuilder.build())
//                .put(body)
//                .addHeader("Authorization", HangOutData.getAccessToken())
//                .build();
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
//                    //textView.setText("Customer Updated Successfully, here is the new customer " + myResponse);
//                }
//            }
//        });



//        customer = HangOutData.getCustomer();
//        try {
//            firstname = customer.get("firstName").toString();
//            lastname = customer.get("lastName").toString();
//            Log.d("nameInTry",firstname+lastname);
//            gender = customer.get("gender").toString();
//            email = customer.get("email").toString();
//            avatarUrl= customer.get("avatar").toString();
//            dob = customer.get("dob").toString();
//            hahalpref = customer.get("halaPreference").toString();
//            vegpref = customer.get("vegPreference").toString();
//            regionalpref = customer.get("regionalPreference").toString();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        // set all text to info
//        Log.d("name",firstname+lastname);
//        nameTv.setText(firstname+" "+lastname);
//        genderTv.setText("Gender: "+gender);
//        emailTv.setText("Email: "+email);
//        dobTv.setText("DOB: "+dob);
//        if(hahalpref == "true")
//            halalOpTv.setText("Hahal Option: Yes");
//        else
//            halalOpTv.setText("Hahal Option: No");
//        if(vegpref == "true")
//            vegOpTv.setText("Vegitarian Option: Yes");
//        else
//            vegOpTv.setText("Vegitarian Option: No");
//        regionalTv.setText("Regional Info: "+regionalpref);
//        if(avatarUrl == null)
//            avatarUrl = "https://iupac.org/wp-content/uploads/2018/05/default-avatar-768x768.png";
//        // set avatar from url
//        new DownloadImageFromInternet(avatar).execute(avatarUrl);



        editProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent editProfileIntent = new Intent(UserProfile.this, com.example.hangout_v0.Me.avator.EditCustomerProfile.class);
                startActivity(editProfileIntent);
            }
        });

    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
            Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();

        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}




