package com.fz.zreo.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fz.zreo.R;
import com.fz.zreo.bean.XcyeCx;
import com.fz.zreo.utils.MyApplication;
import com.fz.zreo.utils.MyVolley;
import com.fz.zreo.utils.VolleyResponse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Zero on 2017/5/23.
 */

public class XCCZFra extends Fragment implements View.OnClickListener, VolleyResponse {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xccz, container, false);
        initData();
        initView(view);
        setData();
        event();
        return view;
    }

    private void initView(View view) {
        btnCaxun = (Button) view.findViewById(R.id.btn_xccz_caxun);
        btnChonzhi = (Button) view.findViewById(R.id.btn_xccz_cz);
        spnCxzh = (Spinner) view.findViewById(R.id.spn_xccz_cxzh);
        spnCzzh = (Spinner) view.findViewById(R.id.spn_xccz_czzh);
        etCzye = (EditText) view.findViewById(R.id.et_xccz_ye);
        tvCxye = (TextView) view.findViewById(R.id.tv_xccz_cxye);
        volley = new MyVolley();
    }

    private void setData() {
        ArrayAdapter cxAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, adapters);
        spnCxzh.setAdapter(cxAdapter);
        ArrayAdapter czAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, adapters);
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

    @Override
    public void onClick(View v) {
        String url;
        Map<String, String> map;
        JSONObject json = null;
        switch (v.getId()) {
            case R.id.btn_xccz_caxun:
                url = MyVolley.URL + "GetCarAccountBalance.do";
                try {
                    json = new JSONObject("{'CarId':" + cxCarId + ",'UserName':'Z0004'}");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volley.postHttpByJson(XCCZFra.this, url, json, CAXUN);
                break;
            case R.id.btn_xccz_cz:
                url = MyVolley.URL + "SetCarAccountRecharge.do";
                map = new TreeMap<>();
                czMoney = etCzye.getText().toString();
                map.put("CarId", czCarId);
                map.put("Money", czMoney);
                map.put("UserName", "Z0004");
                json = new JSONObject(map);
                volley.postHttpByJson(XCCZFra.this, url, json, CHONGZHI);
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        MyApplication.getRequestQueue().cancelAll("postJson");
    }

    @Override
    public void onSuccessResponseJson(JSONObject jsonObject, int type) {
        Gson gson = new Gson();
        XcyeCx cx = gson.fromJson(jsonObject.toString(), XcyeCx.class);
        if (type == CHONGZHI) {
            Toast.makeText(getActivity(), "充值" + cx.getERRMSG().toString(), Toast.LENGTH_LONG).show();
        } else {
            tvCxye.setText("账户余额：" + cx.getBalance() + "元");
        }
    }

    @Override
    public void onFailResponseJson(VolleyError error) {

    }
}
