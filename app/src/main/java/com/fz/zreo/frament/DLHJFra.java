package com.fz.zreo.frament;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.fz.zreo.HjzbActivity;
import com.fz.zreo.R;
import com.fz.zreo.bean.Hjzb;
import com.fz.zreo.utils.MyApplication;
import com.fz.zreo.utils.MyVolley;
import com.fz.zreo.utils.VolleyRespons;
import com.fz.zreo.utils.WebResponse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by Zero on 2017/5/23.
 */

public class DLHJFra extends Fragment implements WebResponse {
    private TextView tvXd;
    private TextView tvWd;
    private TextView tvCo2;
    private TextView tvGz;
    private TextView tvPm;
    private TextView tvDlzt;
    private MyVolley volley;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hjzb, container, false);
        initView(view);
        setData();
        return view;
    }

    private void initView(View view) {
        volley = new MyVolley();
        tvCo2 = (TextView) view.findViewById(R.id.tv_co2);
        tvDlzt = (TextView) view.findViewById(R.id.tv_dlzt);
        tvGz = (TextView) view.findViewById(R.id.tv_gz);
        tvPm = (TextView) view.findViewById(R.id.tv_pm);
        tvXd = (TextView) view.findViewById(R.id.tv_xd);
        tvWd = (TextView) view.findViewById(R.id.tv_wd);
    }

    private void setData() {
        final String url = "http://192.168.5.25:8080/transportservice/action/GetAllSense.do";
        final String requestJson = "{'UserName':'Z0004'}";
        try {
            final JSONObject jsonObject = new JSONObject(requestJson);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    while (true) {
                        MyApplication.getRequestQueue().cancelAll("postJson");
                        volley.postJsonByJson(new VolleyRespons() {
                            @Override
                            public void onSuccessResponseJson(JSONObject jsonObject, int type) {
                                setViewData(jsonObject.toString());
                            }

                            @Override
                            public void onFailResponseJson(VolleyError error) {

                            }
                        }, url, jsonObject, 100);
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*//异步
        //OkManager.getInstance().postAsynHttp(HjzbActivity.this, url, requestJson);
        //同步
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    String str = OkManager.getInstance().postSyncHttp(url, requestJson);
                    Message message = handler.obtainMessage();
                    message.obj = str;
                    handler.sendMessage(message);
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();*/
    }

    private void setViewData(String result) {
        Gson gson = new Gson();
        Hjzb hjzb = gson.fromJson(result, Hjzb.class);
        tvWd.setText(hjzb.getTemperature() + "");
        tvXd.setText(hjzb.getHumidity() + "");
        tvPm.setText(hjzb.get_$Pm25184() + "");
        tvGz.setText(hjzb.getLightIntensity() + "");
        tvCo2.setText(hjzb.getCo2() + "");
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = (String) msg.obj;
            setViewData(str);
        }
    };

    @Override
    public void onSuccessResponse(Call call, String result, int requestCode) throws IOException {
        setViewData(result);

    }

    @Override
    public void onFailResponse(Call call, IOException e, int requestCode) {

    }

}
