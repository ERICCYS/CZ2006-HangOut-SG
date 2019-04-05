package com.example.hangout_v0.Vendor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VendorShop extends AppCompatActivity {
    public static final String baseUrl = HangOutApi.baseUrl;
    TextView shopName, shopLocation, shopNumber, shopEmail, shopCategory;
    Button editButton;
    Button vendorReservationButton;
    public Long shopId = new Long(1);
    public Long vendorId = new Long(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_in_detail_vendor);

        Intent intent0 = getIntent();
        Bundle extras = intent0.getExtras();
        shopId = extras.getLong("shopId", 2);
        vendorId = extras.getLong("vendorId", 1);

        OkHttpClient client = new OkHttpClient();
        String url = baseUrl + "shop";
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

                    try {
                        JSONObject shop = new JSONObject(myResponse);
                        HangOutData.setShop(shop);
                        shopName = (TextView) findViewById(R.id.vendorShopNameTextView);
                        shopName.setText(shop.getString("name"));
                        shopLocation = (TextView) findViewById(R.id.vendorShopLocationTextView);
                        shopLocation.setText(shop.getString("location"));
                        shopNumber = (TextView) findViewById(R.id.vendorShopPhoneNumberTextView);
                        shopNumber.setText(shop.getString("contactNumber"));
                        shopEmail = (TextView) findViewById(R.id.vendorShopWebTextView);
                        shopEmail.setText(shop.getString("contactEmail"));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        editButton = (Button) findViewById(R.id.vendorEditbutton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editshop = new Intent(VendorShop.this, com.example.hangout_v0.Vendor.EditShop.class);
                editshop.putExtra("shopId", shopId);
                startActivity(editshop);
            }
        });

        vendorReservationButton = (Button) findViewById(R.id.vendorReserveButton);
        vendorReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewReservation = new Intent(VendorShop.this, ReservationPage.class);
                Bundle extras = new Bundle();
                extras.putLong("vendorId", vendorId);
                extras.putLong("shopId", shopId);
                viewReservation.putExtras(extras);
                startActivity(viewReservation);
            }
        });


        ImageView shopPhoto1 = (ImageView) findViewById(R.id.shopPhoto1);
        ImageView shopPhoto2 = (ImageView) findViewById(R.id.shopPhoto2);
        ImageView shopPhoto3 = (ImageView) findViewById(R.id.shopPhoto3);

        shopPhoto1.setImageResource(R.drawable.image1);
        shopPhoto2.setImageResource(R.drawable.image2);
        shopPhoto3.setImageResource(R.drawable.image3);

        String[] name = {"peach", "mango", "apple", "pear", "watermelon", "cherry"};
        String[] description = {"She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. ",
                "She suspicion dejection saw instantly. Well deny may real one told yet saw hard dear. Bed chief house rapid right the. Set noisy one state tears which. No girl oh part must fact high my he. Simplicity in excellence melancholy as remarkably discovered. Own partiality motionless was old excellence she inquietude contrasted. Sister giving so wicket cousin of an he rather marked. Of on game part body rich. Adapted mr savings venture it or comfort affixed friends. "};
        Integer[] imgid = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6};

        Float[] rating = {4.0f, 3.0f, 4.3f, 4.0f, 4.2f, 4.1f};
        String[] distance = {"4.0", "3.0", "4.3", "4.0", "4.2", "4.1"};
        String[] carParkCapacity = {"90%", "80%", "80%", "80%", "80%", "80%"};

    }

}
