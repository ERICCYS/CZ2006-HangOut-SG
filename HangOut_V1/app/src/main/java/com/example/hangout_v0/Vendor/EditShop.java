package com.example.hangout_v0.Vendor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hangout_v0.R;

public class EditShop extends AppCompatActivity{
    public Long shopId;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_edit_shop);
        //shopId = getIntent().getLongExtra("shopId", 1);
        //TO DO: link front end and
        submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: call api to store information
                Toast toast = Toast.makeText(getApplicationContext(), "Successfully submitted!", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });

    }
}