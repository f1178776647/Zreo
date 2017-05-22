package com.fz.zreo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fz.zreo.bean.XcyeCx;
import com.fz.zreo.utils.MyApplication;
import com.fz.zreo.utils.MyVolley;
import com.fz.zreo.utils.VolleyRespons;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Zero on 2017/5/22.
 */

public class XcczActivity extends Activity implements View.OnClickListener, VolleyRespons {
    public static int CHONGZHI = 100;
    public static int CAXUN = 200;

    private Button btnCaxun;
    private Button btnChonzhi;
    private Spinner spnCxzh;
    private Spinner spnCzzh;
    private EditText etCzye;
    private TextView tvCxye;
    private String[] adapters;
    private MyVolley volley;
    private String cxCarId;
    private String czCarId;
    private String czMoney;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xccz);
        initData();
        initView();
        setData();
        event();
    }

    private void setData() {
        ArrayAdapter cxAdapter = new ArrayAdapter(XcczActivity.this, android.R.layout.simple_spinner_item, adapters);
        spnCxzh.setAdapter(cxAdapter);
        ArrayAdapter czAdapter = new ArrayAdapter(XcczActivity.this, android.R.layout.simple_spinner_item, adapters);
        spnCzzh.setAdapter(czAdapter);
    }

    private void initData() {
        cxCarId = "1";
        czCarId = "1";
        adapters = new String[]{"1", "2", "3", "4", "5"};
    }

    private void event() {
        btnChonzhi.setOnClickListener(this);
        btnCaxun.setOnClickListener(this);
        spnCxzh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cxCarId = adapters[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnCzzh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                czCarId = adapters[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {
        btnCaxun = (Button) findViewById(R.id.btn_xccz_caxun);
        btnChonzhi = (Button) findViewById(R.id.btn_xccz_cz);
        spnCxzh = (Spinner) findViewById(R.id.spn_xccz_cxzh);
        spnCzzh = (Spinner) findViewById(R.id.spn_xccz_czzh);
        etCzye = (EditText) findViewById(R.id.et_xccz_ye);
        tvCxye = (TextView) findViewById(R.id.tv_xccz_cxye);
        volley = new MyVolley();
    }

    @Override
    public void onClick(View v) {
        String url;
        Map<String, String> map;
        JSONObject json = null;
        switch (v.getId()) {
            case R.id.btn_xccz_caxun:
                url = "http://192.168.5.25:8080/transportservice/action/GetCarAccountBalance.do";
                try {
                    json = new JSONObject("{'CarId':" + cxCarId + ",'UserName':'Z0004'}");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volley.postJsonByJson(XcczActivity.this, url, json, CAXUN);
                break;
            case R.id.btn_xccz_cz:
                url = "http://192.168.5.25:8080/transportservice/action/SetCarAccountRecharge.do";
                map = new TreeMap<>();
                czMoney = etCzye.getText().toString();
                map.put("CarId", czCarId);
                map.put("Money", czMoney);
                map.put("UserName", "Z0004");
                json = new JSONObject(map);
                volley.postJsonByJson(XcczActivity.this, url, json, CHONGZHI);
                break;
        }
    }

    @Override
    public void onSuccessResponseJson(JSONObject jsonObject, int type) {
        Gson gson = new Gson();
        XcyeCx cx = gson.fromJson(jsonObject.toString(), XcyeCx.class);
        if (type == CHONGZHI) {
            Toast.makeText(XcczActivity.this, "充值" + cx.getERRMSG().toString(), Toast.LENGTH_LONG).show();
        } else {
            tvCxye.setText("账户余额：" + cx.getBalance() + "元");
        }
    }

    @Override
    public void onFailResponseJson(VolleyError error) {
        Toast.makeText(XcczActivity.this, error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getRequestQueue().cancelAll("postJson");
    }
}
