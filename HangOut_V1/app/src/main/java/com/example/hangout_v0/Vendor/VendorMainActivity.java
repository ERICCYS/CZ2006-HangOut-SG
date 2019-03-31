package com.example.hangout_v0.Vendor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hangout_v0.Me.avator.EditCustomerProfile;
import com.example.hangout_v0.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.skyforce.SkyForceWebService.model.Shop;

import com.example.hangout_v0.Vendor.Utils.ShopAdapterVendor;

import java.util.ArrayList;
import java.util.UUID;

public class VendorMainActivity extends AppCompatActivity {

    ListView listView;
    ImageView avator;
    String url = "https://avatarfiles.alphacoders.com/873/thumb-87368.jpg";
    Button addShop, editAvatar;
    ShopAdapterVendor adapter;
    private String avatarUrl;
    private Uri avatarUri;
    private StorageReference storageRef;
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vendor);

        addShop = (Button) findViewById(R.id.addShopButton);
        addShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(VendorMainActivity.this,"hi",Toast.LENGTH_SHORT).show();
                Intent addyourshop = new Intent(VendorMainActivity.this, com.example.hangout_v0.Vendor.AddShop.class);
                startActivity(addyourshop);
            }
        });

        avator = (ImageView)findViewById(R.id.vendorAvatar);
        avator.setImageResource(R.drawable.image1);

        // edit avatar
        editAvatar = findViewById(R.id.editVendorAvatar);
        storageRef = FirebaseStorage.getInstance().getReference();
        editAvatar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });


        //listview
        listView = (ListView) findViewById(R.id.shopList);
        final ArrayList<Shop> arrayList = new ArrayList<>(); //you should change 'string' here to 'shop' later when connect with back end

        arrayList.add(new Shop("PUTIEN Jurong", "12345678"));
        arrayList.add(new Shop("PUTIEN Changi", "91234567"));
        adapter = new ShopAdapterVendor(this, arrayList);
        listView.setAdapter(adapter);
        //listview end
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){

                String tempListView = arrayList.get(position).getName();
                Intent intent = new Intent(VendorMainActivity.this, com.example.hangout_v0.Vendor.VendorShop.class);
                intent.putExtra("ClickedValue", tempListView);
                startActivity(intent);

            }
        });

    }
    public void populateListView(){

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    // upload firebase and return url
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null ) {
            // set image
            avatarUri = data.getData();
            avator.setImageURI(avatarUri);

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
                        Log.d("upload vendor avatar",avatarUrl);
                        progressDialog.dismiss();
                        Toast.makeText(VendorMainActivity.this,"Image uploaded",Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle failures
                        progressDialog.dismiss();
                        Toast.makeText(VendorMainActivity.this,"Upload failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
