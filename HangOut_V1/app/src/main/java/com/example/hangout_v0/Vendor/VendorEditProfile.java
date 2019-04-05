package com.example.hangout_v0.Vendor;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.R;

import org.json.JSONException;
import org.json.JSONObject;


public class VendorEditProfile extends AppCompatActivity {
    Button submit;

    EditText firstNameEt, lastNameEt;
    RadioGroup genderSelection;
    private String firstname, lastname, gender;
    static Long vendorId;
    JSONObject vendor;
    public Long id = new Long(1);
    public String accessToken;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_edit_profile);
        submit = (Button) findViewById(R.id.submitVendorProfileButton);
        firstNameEt = findViewById(R.id.editVendorFirstName);
        lastNameEt = findViewById(R.id.editVendorLastName);
        genderSelection = findViewById(R.id.editVendorGender);

        String accessToken = HangOutApi.accessToken;
        vendorId = Long.parseLong(HangOutApi.getUserId(accessToken));
        HangOutApi.getVendor(vendorId);
        vendor = HangOutData.getVendor();
        try {
            firstname = vendor.get("firstName").toString();
            lastname = vendor.get("lastName").toString();
            gender = vendor.get("gender").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = firstNameEt.getText().toString();
                lastname = lastNameEt.getText().toString();
                switch (genderSelection.getCheckedRadioButtonId()) {
                    case R.id.editVendorRadioMale:
                        gender = "MALE";
                        break;
                    case R.id.editVendorRadioFemale:
                        gender = "FEMALE";
                        break;
                    default:
                        gender = "OTHER";
                        break;
                }

                JSONObject updatedVendor = new JSONObject();
                try {
                    updatedVendor.put("firstName", firstname);
                    updatedVendor.put("lastName", lastname);
                    updatedVendor.put("gender", gender);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                HangOutApi.updateVendorInfo(vendorId, updatedVendor.toString());
                Toast toast = Toast.makeText(getApplicationContext(), "Successfully submitted!", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });

    }

}
