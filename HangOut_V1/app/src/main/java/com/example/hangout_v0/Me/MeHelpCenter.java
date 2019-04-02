package com.example.hangout_v0.Me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.hangout_v0.R;

public class MeHelpCenter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_help_center);
        this.getSupportActionBar().hide();
    }
}
