package com.fz.zreo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.fz.zreo.utils.DaoLu;
import com.fz.zreo.utils.OkManager;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by Zero on 2017/5/19.
 */

public class DaoLuZhanTaiActivity extends Activity {
    private Spinner mSpinner;
    private String[] spinners;
    private TextView tvDaoluNo1;
    private TextView tvDaoluNo2;
    private TextView tvDaoluNo3;
    private TextView tvDaoluNo4;
    private TextView tvDaoluNo5;
    private TextView tvDaoluzhantai1;
    private TextView tvDaoluzhantai2;
    private TextView tvDaoluzhantai3;
    private TextView tvDaoluzhantai4;
    private TextView tvDaoluzhantai5;
    private TextView tvDaoluyanse1;
    private TextView tvDaoluyanse2;
    private TextView tvDaoluyanse3;
    private TextView tvDaoluyanse4;
    private TextView tvDaoluyanse5;
    private Button btnCaxun;
    private int[] yanses = new int[]{
            Color.BLUE,
            Color.RED,
            Color.CYAN,
            Color.GRAY,
            Color.DKGRAY
    };
    private int[][] maps;
    private boolean isDes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daoluzhangtai);
        initDatas();
        initView();
        event();
    }

    private void event() {
        btnCaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net();
                maps = new int[2][5];
            }
        });
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    isDes = true;
                } else {
                    isDes = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initDatas() {
        spinners = new String[]{
                "道路状态升序",
                "道路状态降序"
        };
    }

    private void net() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String url = "http://192.168.5.25:8080/transportservice/action/GetRoadStatus.do";
                for (int i = 1; i < 6; i++) {
                    String requestJson = "{'RoadId':" + i + ",'UserName':'Z0004'}";
                    String str = OkManager.getInstance().postSyncHttp(url, requestJson);
                    Message message = handler.obtainMessage();
                    message.obj = str;
                    message.what = i;
                    handler.sendMessage(message);
                }
            }
        }.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = (String) msg.obj;

            Gson gson = new Gson();
            DaoLu daolu = gson.fromJson(result, DaoLu.class);
            int position = msg.what;
            int index = position - 1;
            Log.e("json", result + position + ":" + index);
            maps[1][index] = position;
            maps[0][index] = daolu.getBalance();
            for (int i = 0; i < maps[0].length - 1; i++) {
                for (int j = 0; j < maps[0].length - i - 1; j++) {
                    if (maps[0][j] < maps[0][j + 1]) {
                        int num = maps[0][j];
                        maps[0][j] = maps[0][j + 1];
                        maps[0][j + 1] = num;

                        int num2 = maps[1][j];
                        maps[1][j] = maps[1][j + 1];
                        maps[1][j + 1] = num2;
                    }
                }
            }

            if (position == 5) {
                for (int i = 0; i < 5; i++) {
                    setDaoLuNoText(i);
                }
            }
        }
    };

    private void setViewData() {
        for (int i = 0; i < 5; i++) {
            setDaoLuNoText(i);
        }
    }

    private void setDaoLuNoText(int position) {
        switch (position) {
            case 0:
                tvDaoluNo1.setText(maps[1][position] + "号道路");
                tvDaoluzhantai1.setText(maps[0][position] + "");
                tvDaoluyanse1.setBackgroundColor(yanses[maps[0][position]]);
                break;
            case 1:
                tvDaoluNo2.setText(maps[1][position] + "号道路");
                tvDaoluzhantai2.setText(maps[0][position] + "");
                tvDaoluyanse2.setBackgroundColor(yanses[maps[0][position]]);
                break;
            case 2:
                tvDaoluNo3.setText(maps[1][position] + "号道路");
                tvDaoluzhantai3.setText(maps[0][position] + "");
                tvDaoluyanse3.setBackgroundColor(yanses[maps[0][position]]);
                break;
            case 3:
                tvDaoluNo4.setText(maps[1][position] + "号道路");
                tvDaoluzhantai4.setText(maps[0][position] + "");
                tvDaoluyanse4.setBackgroundColor(yanses[maps[0][position]]);
                break;
            case 4:
                tvDaoluNo5.setText(maps[1][position] + "号道路");
                tvDaoluzhantai5.setText(maps[0][position] + "");
                tvDaoluyanse5.setBackgroundColor(yanses[maps[0][position]]);
                break;
        }
    }

    private void initView() {
        mSpinner = (Spinner) findViewById(R.id.spn_daolu_spinner);
        mSpinner.setGravity(Gravity.CENTER);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinners);
        mSpinner.setAdapter(adapter);
        tvDaoluNo1 = (TextView) findViewById(R.id.tv_daolu_no1);
        tvDaoluNo2 = (TextView) findViewById(R.id.tv_daolu_no2);
        tvDaoluNo3 = (TextView) findViewById(R.id.tv_daolu_no3);
        tvDaoluNo4 = (TextView) findViewById(R.id.tv_daolu_no4);
        tvDaoluNo5 = (TextView) findViewById(R.id.tv_daolu_no5);
        tvDaoluzhantai1 = (TextView) findViewById(R.id.tv_daolu_zhantai1);
        tvDaoluzhantai2 = (TextView) findViewById(R.id.tv_daolu_zhantai2);
        tvDaoluzhantai3 = (TextView) findViewById(R.id.tv_daolu_zhantai3);
        tvDaoluzhantai4 = (TextView) findViewById(R.id.tv_daolu_zhantai4);
        tvDaoluzhantai5 = (TextView) findViewById(R.id.tv_daolu_zhantai5);
        tvDaoluyanse1 = (TextView) findViewById(R.id.tv_daolu_yanse1);
        tvDaoluyanse2 = (TextView) findViewById(R.id.tv_daolu_yanse2);
        tvDaoluyanse3 = (TextView) findViewById(R.id.tv_daolu_yanse3);
        tvDaoluyanse4 = (TextView) findViewById(R.id.tv_daolu_yanse4);
        tvDaoluyanse5 = (TextView) findViewById(R.id.tv_daolu_yanse5);
        btnCaxun = (Button) findViewById(R.id.btn_daolu_caxun);
    }
}
