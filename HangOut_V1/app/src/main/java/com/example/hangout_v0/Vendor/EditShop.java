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
import android.widget.EditText;
import android.widget.Toast;

import com.example.hangout_v0.ApiCall.HangOutApi;
import com.example.hangout_v0.R;
import com.example.hangout_v0.Vendor.AddShop;
import com.example.hangout_v0.Vendor.Shop;
import com.example.hangout_v0.Vendor.VendorMainActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.hangout_v0.ApiCall.HangOutApi.JSON;

public class EditShop extends AppCompatActivity{
    public static final String baseUrl = HangOutApi.baseUrl;
    public Long shopId;
    Button submit, addCertificate;
    EditText shopname, contactnumber, addressEditText, contactemail, categori;
    public static final int PICK_IMAGE = 1;
    private Uri certifUri;
    private StorageReference storageRef;
    private String certifUrl;
    private String shopName;
    private String contactNumber;
    private String contactEmail;
    private String location;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_edit_shop);

        final Long shopId = getIntent().getLongExtra("shopId", 0);
        shopname = (EditText) findViewById(R.id.editShopName);
        contactnumber = (EditText) findViewById(R.id.editContactNumber);
        addressEditText = (EditText) findViewById(R.id.editAddress);
        contactemail = (EditText) findViewById(R.id.editContactEmail);
        categori = (EditText) findViewById(R.id.editCategory);

        submit = (Button) findViewById(R.id.editSubmitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: call api to store information
                shopName = shopname.getText().toString();
                contactNumber = contactnumber.getText().toString();
                location = addressEditText.getText().toString();
                contactEmail = contactemail.getText().toString();
                category = categori.getText().toString();
                JSONObject newShop = new JSONObject();
                try {
                    newShop.put("name", shopName);
                    newShop.put("contactNumber", contactNumber);
                    newShop.put("contactEmail", contactEmail);
                    newShop.put("category", category);
                    newShop.put("location", location);
                    JSONArray carParks = new JSONArray();
                    carParks.put("HLM");
                    newShop.put("carParkNumbers", carParks);
                } catch(JSONException e){
                    e.printStackTrace();
                }

                RequestBody body = RequestBody.create(JSON, newShop.toString());
                OkHttpClient client = new OkHttpClient();
                String url = baseUrl + "shop";
                HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
                httpBuilder.addQueryParameter("shopId", shopId.toString());
                Request request = new Request.Builder()
                        .url(httpBuilder.build())
                        .put(body)
                        .addHeader("Authorization", HangOutApi.vendorAT)
                        .build();

                // Note that here the access Token is the vendor's access token
                // Vendor is the vendor who owned the shop
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String myResponse = response.body().string();
                            try {
                                System.out.println(myResponse);
                                JSONObject shop = new JSONObject(myResponse);
//                                JSONArray shopList = HangOutData.getShopList();
                                EditShop.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast toast = Toast.makeText(getApplicationContext(), "Successfully submitted!", Toast.LENGTH_SHORT);
                                        toast.show();
                                        Intent mainActivity = new Intent(getApplicationContext(), VendorMainActivity.class);
                                        startActivity(mainActivity);

                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            // response is the JSON String of the updated shop
                            // up to u to deal with it
                        }
                    }
                });

                finish();
            }
        });

        // upload certificate;
        addCertificate = findViewById(R.id.editUploadButton);
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
                        Toast.makeText(EditShop.this,"Image uploaded",Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle failures
                        progressDialog.dismiss();
                        Toast.makeText(EditShop.this,"Upload failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}