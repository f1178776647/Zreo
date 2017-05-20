package com.fz.zreo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.fz.zreo.bean.Hjzb;
import com.fz.zreo.utils.OkManager;
import com.fz.zreo.utils.WebResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Zero on 2017/5/18.
 */

public class HjzbActivity extends Activity implements WebResponse {
    private TextView tvXd;
    private TextView tvWd;
    private TextView tvCo2;
    private TextView tvGz;
    private TextView tvPm;
    private TextView tvDlzt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hjzb);
        initView();
        setData();
    }

    private void setData() {
        final String url = "http://192.168.5.15:8080/transportservice/action/GetAllSense.do";
        final String requestJson = "{'UserName':'Z0004'}";
        //异步
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
        }.start();
    }

    private void initView() {
        tvCo2 = (TextView) findViewById(R.id.tv_co2);
        tvDlzt = (TextView) findViewById(R.id.tv_dlzt);
        tvGz = (TextView) findViewById(R.id.tv_gz);
        tvPm = (TextView) findViewById(R.id.tv_pm);
        tvXd = (TextView) findViewById(R.id.tv_xd);
        tvWd = (TextView) findViewById(R.id.tv_wd);
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
