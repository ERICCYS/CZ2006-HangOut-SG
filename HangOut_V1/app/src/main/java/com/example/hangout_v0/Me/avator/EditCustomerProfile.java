package com.example.hangout_v0.Me.avator;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.ApiCall.HangOutData;
import com.example.hangout_v0.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.UUID;

public class EditCustomerProfile extends AppCompatActivity {

    public static final int PICK_IMAGE = 8;
    // all buttons
    Button submit;
    private TextView profileDisplayDate;
    private DatePickerDialog.OnDateSetListener profileDateSetListener;
    Switch halalOp, vegOp;
    ImageView custAvatar;
    Button editPhoto;
    private Uri avatarUri;
    private StorageReference storageRef;
    EditText firstNameEt, lastNameEt, passwordEt, regionalEt;
    RadioGroup genderSelection;
    // all customer info
    private String firstname, lastname, gender, email, password, avatarUrl, dob, hahalpref, vegpref, regionalpref;
    static Long customerId = Long.valueOf(3);
    JSONObject customer;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_profile_edit);

        // get all buttons
        editPhoto = findViewById(R.id.editAvatarButton);
        submit = (Button) findViewById(R.id.submitProfileButton);
        custAvatar = findViewById(R.id.editCustomerAvatar);
        storageRef = FirebaseStorage.getInstance().getReference();
        firstNameEt = findViewById(R.id.editCustomerFirstName);
        lastNameEt = findViewById(R.id.editCustomerLastName);
        passwordEt = findViewById(R.id.editCustomerPassword);
        regionalEt = findViewById(R.id.editCustomerRegion);
        genderSelection = findViewById(R.id.editCustomerGender);
        halalOp = findViewById(R.id.editHalalOption);
        vegOp = findViewById(R.id.editVegOption);

        // set all customer info to original ones on the edit page
        String accessToken = HangOutData.getAccessToken();
        customerId = Long.parseLong(HangOutApi.getUserId(accessToken));
        HangOutApi.getCustomer(customerId);
        customer = HangOutData.getCustomer();
        try {
            firstname = customer.get("firstName").toString();
            lastname = customer.get("lastName").toString();
            gender = customer.get("gender").toString();
            email = customer.get("email").toString();
            password = customer.get("password").toString();
            avatarUrl= customer.get("avatar").toString();
            dob = customer.get("dob").toString();
            hahalpref = customer.get("halaPreference").toString();
            vegpref = customer.get("vegPreference").toString();
            regionalpref = customer.get("regionalPreference").toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Log.d("nameInProfile",firstname+lastname);

        editPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });


        // date selection
        profileDisplayDate = findViewById(R.id.editDOB);

        profileDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal  = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        EditCustomerProfile.this,
                android.R.style.Theme_DeviceDefault,
                        profileDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                dialog.show();
            }
        });

        profileDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                dob = Integer.toString(year) + '-' + Integer.toString(month) + '-' + Integer.toString(dayOfMonth);
                profileDisplayDate.setText(dob);
            }
        };


        //submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // submit: pass all customer info to backend
                firstname = firstNameEt.getText().toString();
                lastname = lastNameEt.getText().toString();
                switch (genderSelection.getCheckedRadioButtonId()){
                    case R.id.editRadioMale:
                        gender = "MALE";
                        break;
                    case R.id.editRadioFemale:
                        gender = "FEMALE";
                        break;
                        default:
                            gender = "OTHER";
                            break;
                }

                password = passwordEt.getText().toString();
                if(halalOp.isChecked())
                    hahalpref = "true";
                else hahalpref = "false";
                if(vegOp.isChecked())
                    vegpref = "true";
                else vegpref = "false";
                regionalpref = regionalEt.getText().toString();
                if(avatarUrl ==  null)
                    avatarUrl = "https://iupac.org/wp-content/uploads/2018/05/default-avatar-768x768.png";
                JSONObject updatedCustomer = new JSONObject();
                try {
                    updatedCustomer.put("firstName", firstname);
                    updatedCustomer.put("lastName", lastname);
                    updatedCustomer.put("gender", gender);
                    updatedCustomer.put("email", email);
                    updatedCustomer.put("password", password);
                    updatedCustomer.put("avatar", avatarUrl);
                    updatedCustomer.put("dob", dob);
                    updatedCustomer.put("halaPreference", hahalpref);
                    updatedCustomer.put("vegPreference", vegpref);
                    updatedCustomer.put("regionalPreference", regionalpref);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //System.out.println("**************************************");
                HangOutApi.updateCustomerInfo(customerId,updatedCustomer.toString());
                Toast toast = Toast.makeText(getApplicationContext(), "Successfully submitted!", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
            if(requestCode == PICK_IMAGE && resultCode == RESULT_OK
                    && data != null && data.getData() != null ) {
                // get image
                avatarUri = data.getData();
                custAvatar.setImageURI(avatarUri);
                //upload image
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading image...");
                progressDialog.show();
                final StorageReference ref = storageRef.child("images/"+ UUID.randomUUID().toString());

                //upload image
                UploadTask uploadTask = ref.putFile(avatarUri);

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            //get url
                            avatarUrl = task.getResult().toString();
                            Log.d("upload customer avatar",avatarUrl);
                            progressDialog.dismiss();
                            Toast.makeText(EditCustomerProfile.this,"Image uploaded",Toast.LENGTH_SHORT).show();
                        } else {
                            // Handle failures
                            progressDialog.dismiss();
                           Toast.makeText(EditCustomerProfile.this,"Upload failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
    }
}
