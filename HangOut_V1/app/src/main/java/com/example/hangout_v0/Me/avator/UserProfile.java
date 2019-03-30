package com.example.hangout_v0.Me.avator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hangout_v0.R;

import java.net.URL;

public class UserProfile extends AppCompatActivity {


    Button editProfile;
    String avatorURL;
    int avatorCode=1;
    ImageView avator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_profile);

        this.getSupportActionBar().hide();

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        avator = (ImageView) findViewById(R.id.customerAvatar);

        editProfile = findViewById(R.id.editProfileButton);
        editProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent editProfileIntent = new Intent(UserProfile.this, com.example.hangout_v0.Me.avator.EditCustomerProfile.class);
                startActivityForResult(editProfileIntent , avatorCode);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == avatorCode  && resultCode  == RESULT_OK) {

                avatorURL = data.getStringExtra("custAvatarUrl");
                Toast.makeText(UserProfile.this,avatorURL,Toast.LENGTH_LONG);

            }
        } catch (Exception ex) {
            Toast.makeText(UserProfile.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }

}
