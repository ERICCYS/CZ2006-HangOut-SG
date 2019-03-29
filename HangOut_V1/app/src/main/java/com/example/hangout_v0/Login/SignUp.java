package com.example.hangout_v0.Login;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.hangout_v0.Me.avator.EditCustomerProfile;
import com.example.hangout_v0.R;

import java.util.Calendar;

public class SignUp extends AppCompatActivity {
    EditText firstNameEt, lastNameEt, emailEt, password1, password2, regionEt;
    Button signUpCustBtn, signUpVendorBtn;
    Switch halalSw, vegSw;
    String firstName, lastName, email, dob, password, gender, region;
    Boolean halal, veg;
    private TextView signUpDOB;
    private DatePickerDialog.OnDateSetListener signupDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // set all buttons
        firstNameEt = findViewById(R.id.enterSignUpFirstName);
        lastNameEt = findViewById(R.id.enterSignUpLastName);
        emailEt = findViewById(R.id.enterSignUpEmail);
        password1 = findViewById(R.id.enterPassword);
        password2 = findViewById(R.id.enterPasswordAgain);
        regionEt = findViewById(R.id.enterSignUpRegion);
        halalSw = findViewById(R.id.enterSignUpHalalOption);
        vegSw =  findViewById(R.id.enterSignUpVegOption);
        signUpCustBtn = findViewById(R.id.signUpAsCustomerButton);
        signUpVendorBtn = findViewById(R.id.signUpAsVendorButton);

        // date selection
        signUpDOB = findViewById(R.id.enterSignUpDOB);

        signUpDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal  = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        SignUp.this,
                        android.R.style.Theme_DeviceDefault,
                        signupDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                dialog.show();
            }
        });

        signupDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                dob = Integer.toString(month) +'/' + Integer.toString(dayOfMonth) + '/'+ Integer.toString(year);
                signUpDOB.setText(dob);
            }
        };

        // check for basic error handling
        signUpCustBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handle values
            }
        });

        signUpVendorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
