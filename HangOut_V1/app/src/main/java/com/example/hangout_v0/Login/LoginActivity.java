package com.example.hangout_v0.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hangout_v0.R;
import com.example.hangout_v0.UserMainActivity;
import com.example.hangout_v0.Vendor.VendorMainActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etUserPassword;
    private Button btn_login;
    private Button btn_skip;
    private String userName;
    private String userPassword;
    private CheckBox cb_vendor;
    private boolean is_vendor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        btn_login = (Button) findViewById(R.id.login_login_button);
        btn_skip = (Button) findViewById(R.id.login_skip_button);
        cb_vendor = (CheckBox) findViewById(R.id.login_vendorTunnel_checkbox);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_vendor == false){
                    switchToUserPage();
                }
                else{
                    switchToVendorPage();

                }
            }
        });

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToUserPage();
            }
        });

        cb_vendor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    is_vendor = true;
                }else{
                    is_vendor = false;
                }
            }
        });

    }

    private void init(){
        EditText userName = (EditText) findViewById(R.id.login_userName_editText);
        EditText password = (EditText) findViewById(R.id.login_userPassword_editText);
        ImageView unameClear = (ImageView) findViewById(R.id.login_userName_clear);
        ImageView pwdClear = (ImageView) findViewById(R.id.login_userPassword_clear);

        com.example.hangout_v0.Login.EditTextClearTools.addClearListener(userName,unameClear);
        com.example.hangout_v0.Login.EditTextClearTools.addClearListener(password,pwdClear);
    }

    private void switchToUserPage(){
        Intent myIntent = new Intent(this, UserMainActivity.class);
        startActivity(myIntent);
    }

    private void switchToVendorPage(){
        Intent myIntent = new Intent(this, VendorMainActivity.class);
        startActivity(myIntent);
    }


}
