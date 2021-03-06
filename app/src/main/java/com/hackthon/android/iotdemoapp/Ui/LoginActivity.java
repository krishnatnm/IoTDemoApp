package com.hackthon.android.iotdemoapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hackthon.android.iotdemoapp.R;
import com.hackthon.android.iotdemoapp.model.User;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    private Button btnLogin;
    private View signUp;
    private CheckBox keepMeIn;
    private EditText userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intializeAllView();
    }

    private void intializeAllView() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        signUp = findViewById(R.id.signup);
        keepMeIn = (CheckBox) findViewById(R.id.keepMeIn);
        userName = (EditText) findViewById(R.id.userId);
        password = (EditText) findViewById(R.id.password);
        keepMeIn.setOnCheckedChangeListener(this);
        btnLogin.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin :
                performLogin(userName.getEditableText().toString(), password.getEditableText().toString());
                break;

            case R.id.signup :
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void performLogin(String userName, String password) {
        HashMap<String, String> body = new HashMap<>();
        body.put("phone", userName);
        body.put("password", password);
        com.android.volley.Response.Listener<User> listener = new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
                Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, IOTAppIntroActivity.class);
                startActivity(intent);
                finish();
            }
        };

        com.android.volley.Response.ErrorListener onError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        BaseRequestClass.userLoginRequest(LoginActivity.this, body, listener, onError);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
