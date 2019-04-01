package com.example.hangout_v0.Me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hangout_v0.R;

public class MeMoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_more_layout);
        this.getSupportActionBar().hide();
    }
}
