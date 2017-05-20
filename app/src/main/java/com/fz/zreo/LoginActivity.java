package com.fz.zreo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Zero on 2017/5/17.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbSaveUser, cbAutoLogin;
    private Button btnLogin, btnRegister;
    private OkHttpClient mOkHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        event();
    }

    private void event() {
        btnLogin.setOnClickListener(this);
    }

    private void initView() {
        etUsername = (EditText) findViewById(R.id.et_user);
        etPassword = (EditText) findViewById(R.id.et_password);
        cbSaveUser = (CheckBox) findViewById(R.id.cb_save_user);
        cbAutoLogin = (CheckBox) findViewById(R.id.cb_auto_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        switch (v.getId()) {
            case R.id.btn_login:
                String username = sp.getString("username", null);
                String password = sp.getString("password", null);
                if (username != null && password != null) {
                    if (username.equals(etUsername.getText().toString()) &&
                            password.equals(etPassword.getText().toString())) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {

                }

                break;
            case R.id.btn_register:
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("username", etUsername.getText().toString());
                editor.putString("password", etPassword.getText().toString());
                editor.commit();
                break;
        }
    }

    public void postAsynHttp() {
        mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("size", "10")
                .build();
        Request request = new Request.Builder()
                .url("http://api.1-blog.com/biz/bizserver/article/list.do")
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.i("wangshu", str);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
