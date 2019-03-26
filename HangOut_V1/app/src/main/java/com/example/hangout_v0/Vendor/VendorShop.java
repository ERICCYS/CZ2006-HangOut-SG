package com.example.hangout_v0.Vendor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hangout_v0.R;

public class VendorShop extends AppCompatActivity {
    TextView shopName;
    Button editButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_in_detail_vendor);

        String tempHolder = getIntent().getStringExtra("ClickedValue");
        shopName = (TextView) findViewById(R.id.shopNameTextView);
        shopName.setText(tempHolder);

        editButton = (Button) findViewById(R.id.editbutton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editshop = new Intent(VendorShop.this, com.example.hangout_v0.Vendor.EditShop.class);
                startActivity(editshop);
            }
        });


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


//        ListView shopDishListView = (ListView) findViewById(R.id.shopDishListView);
//        com.example.vendor.Utils.ShopInListAdapter shopDishAdapter = new com.example.vendor.Utils.ShopInListAdapter(this,name,description,imgid,rating,distance,carParkCapacity);
//        shopDishListView.setAdapter(shopDishAdapter);

    }

}
