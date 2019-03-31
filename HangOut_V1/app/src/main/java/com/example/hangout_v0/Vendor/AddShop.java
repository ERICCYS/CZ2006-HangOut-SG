package com.example.hangout_v0.Vendor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hangout_v0.Me.avator.EditCustomerProfile;
import com.example.hangout_v0.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class AddShop extends AppCompatActivity{

    Button submit, addCertificate;
    public static final int PICK_IMAGE = 1;
    private Uri certifUri;
    private StorageReference storageRef;
    private String certifUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_add_shop);
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

        // upload certificate;
        addCertificate = findViewById(R.id.uploadButton);
        storageRef = FirebaseStorage.getInstance().getReference();
        addCertificate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }

    // upload certificate to firebase and return url
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null ) {
            certifUri = data.getData();
            //upload image
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading image...");
            progressDialog.show();
            final StorageReference ref = storageRef.child("images/"+ UUID.randomUUID().toString());

            //upload image
            UploadTask uploadTask = ref.putFile(certifUri);

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
                        certifUrl = task.getResult().toString();
                        Log.d("upload certificate",certifUrl);
                        progressDialog.dismiss();
                        Toast.makeText(AddShop.this,"Image uploaded",Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle failures
                        progressDialog.dismiss();
                        Toast.makeText(AddShop.this,"Upload failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}