package com.example.hangout_v0;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.Home.HomeFragment;
import com.example.hangout_v0.Me.MeFragment;
import com.example.hangout_v0.Recommendation.RecommendationFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserMainActivity extends AppCompatActivity {

    public ArrayList<String> ids = new ArrayList<>();
    public ArrayList<String> names = new ArrayList<>();
    public ArrayList<String> contactNumbers = new ArrayList<>();
    public ArrayList<String> contactEmails = new ArrayList<>();
    public ArrayList<String> categories = new ArrayList<>();
    public ArrayList<String> locations = new ArrayList<>();
    public ArrayList<String> carParkNumbers = new ArrayList<>();

    public Long userId ;
    public String accessToken;
    public Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        Intent intent0 = getIntent();
        try {
            Bundle extras = intent0.getExtras();
            userId = extras.getLong("customerId");
            accessToken = HangOutApi.accessToken;

            accessToken = extras.getString("accessToken");

        } catch (NullPointerException e) {

        }


        this.getSupportActionBar().hide();

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navLister);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                new HomeFragment()).commit();

        if(userId != null) {
            OkHttpClient client = new OkHttpClient();
            String url = HangOutApi.baseUrl + "shops";
            Request request = new Request.Builder().url(url).build();

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
                            JSONArray jsonRecShopList = new JSONArray(myResponse);
                            try {
                                for (int i = 0; i < jsonRecShopList.length(); i++) {
                                    JSONObject jsonRecShop = jsonRecShopList.getJSONObject(i);
                                    ids.add(jsonRecShop.getString("id"));
                                    names.add(jsonRecShop.get("name").toString());
                                    contactNumbers.add(jsonRecShop.get("contactNumber").toString());
                                    contactEmails.add(jsonRecShop.get("contactEmail").toString());
                                    categories.add(jsonRecShop.get("category").toString());
                                    locations.add(jsonRecShop.get("location").toString());

                                    if (((JSONArray) jsonRecShop.get("carParkNumbers")).length() > 0) {
                                        carParkNumbers.add((((JSONArray) jsonRecShop.get("carParkNumbers")).get(0)).toString());
                                    } else {
                                        carParkNumbers.add("");
                                    }

                                }
                                bundle.putStringArrayList("ids", ids);
                                bundle.putStringArrayList("names", names);
                                bundle.putStringArrayList("contactNumbers", contactNumbers);
                                bundle.putStringArrayList("contactEmails", contactEmails);
                                bundle.putStringArrayList("categories", categories);
                                bundle.putStringArrayList("locations", locations);
                                bundle.putStringArrayList("carParkNumbers", carParkNumbers);
                                bundle.putLong("customerId", userId);
                            } catch (JSONException e) {
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("********response not successful************");
                    }
                }
            });
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navLister =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.navigation_recommendation:
                            selectedFragment = new RecommendationFragment();
                            selectedFragment.setArguments(bundle);
                            break;
                        case R.id.navigation_me:
                            selectedFragment = new MeFragment();
                            break;
                    }

                    if(userId == null){
                        Toast toast = Toast.makeText(getApplicationContext(), "Please login in :)", Toast.LENGTH_SHORT);
                        toast.show();
                        return false;
                    }
                    else{
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                                selectedFragment).commit();
                        return true;
                    }
                }
            };
}