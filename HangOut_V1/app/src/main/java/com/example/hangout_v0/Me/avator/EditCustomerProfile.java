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
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hangout_v0.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.UUID;

public class EditCustomerProfile extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    Button submit;
    private TextView profileDisplayDate;
    private DatePickerDialog.OnDateSetListener profileDateSetListener;
    Switch halalOp, vegOp;
    ImageView custAvatar;
    Button editPhoto;
    private Uri avatarUri;
    private StorageReference storageRef;
    private String avatarUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_profile_edit);

        this.getSupportActionBar().hide();

        final Intent retriveAvatorURLIntent = getIntent();

        editPhoto = findViewById(R.id.editAvatarButton);
        submit = (Button) findViewById(R.id.submitProfileButton);
        custAvatar = findViewById(R.id.customerAvatar);
        storageRef = FirebaseStorage.getInstance().getReference();


        editPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        //submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //switch status check
                halalOp = findViewById(R.id.editHalalOption);
                vegOp = findViewById(R.id.editVegOption);
                Log.d("switchCheck", "halal option: " + halalOp.isChecked() + "\nveg option: " + vegOp.isChecked());
                //TODO: call api to store information
                Toast toast = Toast.makeText(getApplicationContext(), "Successfully submitted!", Toast.LENGTH_SHORT);
                toast.show();
                retriveAvatorURLIntent.putExtra("key", avatarUrl);
                setResult(RESULT_OK, retriveAvatorURLIntent);

                // pass the avatar url back
                Intent intent = new Intent();
                intent.putExtra("custAvatarUrl", avatarUrl);
                setResult(Activity.RESULT_OK, intent);
                finish();
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
                String date = Integer.toString(month) +'/' + Integer.toString(dayOfMonth) + '/'+ Integer.toString(year);
                profileDisplayDate.setText(date);
            }
        };
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
            if(requestCode == PICK_IMAGE && resultCode == RESULT_OK
                    && data != null && data.getData() != null ) {
                // set image
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
