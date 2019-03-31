package com.example.hangout_v0.Login;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hangout_v0.R;

import java.util.Calendar;

public class SignUpAsVendor extends AppCompatActivity {

    EditText firstNameEt, lastNameEt, emailEt, passwordEt;
    RadioGroup radioSexGroup;
    RadioButton selectedSexBtn;
    Button signUpBtn;

    String firstName, lastName, email, password, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_as_vendor);

        this.getSupportActionBar().hide();

        // set all buttons
        firstNameEt = findViewById(R.id.signUpAsVendorFirstName);
        lastNameEt = findViewById(R.id.signUpAsVendorLastName);
        emailEt = findViewById(R.id.signUpAsVendorEmail);
        passwordEt = findViewById(R.id.signUpAsVendorPassword);

        radioSexGroup = findViewById(R.id.signUpAsVendorSelectGender);
        signUpBtn = findViewById(R.id.signUpAsVendorSignUpBtn);


        // check for basic error handling
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameEt.getText().toString();
                lastName = lastNameEt.getText().toString();
                email = emailEt.getText().toString();
                password = passwordEt.getText().toString();

                // sex
                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                selectedSexBtn = findViewById(selectedId);
                gender = selectedSexBtn.getText().toString();

//                TextView test = findViewById(R.id.vendorSignUpTest);
//                test.setText("vendor info: "+firstName+lastName+email+password+gender);

                Toast.makeText(SignUpAsVendor.this,"sign up successfully", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

    }
}
