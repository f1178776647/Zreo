package com.fz.zreo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.fz.zreo.bean.Hjzb;
import com.fz.zreo.utils.OkManager;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Zero on 2017/5/18.
 */

public class CgqsjlsActivity extends Activity {
    private ListView mListView;
    private List<Hjzb> hjzbs;
    private MyAdapter adapter;
    private int positionCgq;
    private int positionCgq2;
    private Boolean isNet=true;
    private String CgqType;
    private String CgqType2;
    private String Cgq;
    private String Cgq2;
    private int zhouqi;
    private int zhouqi2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = (String) msg.obj;
            Gson gson = new Gson();
            Hjzb hjzb = gson.fromJson(str, Hjzb.class);
            SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date curDate  = new Date(System.currentTimeMillis());//获取当前时间
            String time = formatter.format(curDate);
            hjzb.setTiem(time);
            hjzbs.add(hjzb);
            adapter.notifyDataSetChanged();
        }
    };
    private Spinner mSpinnerCgq;
    private Spinner mSpinnerZq;
    private String[][] cgqs;
    private String[] zqs;
    private Button btnCuxun;
    private Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cgqsjls);
        initDatas();
        initView();
        event();
    }

    private void event() {
        mSpinnerCgq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CgqType2 = cgqs[0][position];
                Cgq2 = cgqs[1][position];
                positionCgq2 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        mSpinnerZq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    zhouqi2=2000;
                }else {
                    zhouqi2=5000;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnCuxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(positionCgq!=positionCgq2){
                    hjzbs = new ArrayList<Hjzb>();
                    CgqType = CgqType2;
                    Cgq = Cgq2;
                    positionCgq = positionCgq2;
                    zhouqi=zhouqi2;
                }else if (zhouqi!=zhouqi2){
                    zhouqi=zhouqi2;
                }

            }
        });
    }

    private void net() {
        mThread = new Thread() {
            @Override
            public void run() {
                super.run();
                while (isNet) {
                    try {
                        String url = "http://192.168.5.25:8080/transportservice/action/GetSenseByName.do";
                        String requestJson = "{'SenseName':'" + CgqType + "', 'UserName':'Z0004'}";
                        Log.d("dsad",requestJson);
                        String str = OkManager.getInstance().postSyncHttp(url, requestJson);
                        Message message = handler.obtainMessage();
                        message.obj = str;
                        handler.sendMessage(message);
                        sleep(zhouqi);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mThread.start();
    }

    private void initDatas() {
        zhouqi=2000;
        mThread = new Thread();
        Cgq = "空气温度";
        CgqType = "temperature";
        positionCgq = 0;
        cgqs = new String[][]{
                {
                        "temperature",
                        "humidity",
                        "LightIntensity",
                        "co2",
                        "pm2.5",
                },
                {
                        "空气温度",
                        "空气湿度",
                        "光照强度",
                        "二氧化碳",
                        "Pm2.5",
                }
        };
        zqs=new String[]{
          "2/s",
                "5/s"
        };
        ArrayAdapter cgqAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cgqs[1]);
        ArrayAdapter zqAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,zqs);
        mSpinnerZq= (Spinner) findViewById(R.id.spn_cgq_zhouqi);
        mSpinnerZq.setAdapter(zqAdapter);
        mSpinnerCgq = (Spinner) findViewById(R.id.spn_cgq_type);
        mSpinnerCgq.setAdapter(cgqAdapter);
        hjzbs = new ArrayList<>();
        net();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.lv_cgqsjls);
        adapter = new MyAdapter();
        mListView.setAdapter(adapter);
        btnCuxun = (Button) findViewById(R.id.btn_cgq_cuxun);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return hjzbs.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(CgqsjlsActivity.this).inflate(R.layout.cgqsjls_item, null);
                viewHolder.tvCgq = (TextView) view.findViewById(R.id.tv_cgq_cgq);
                viewHolder.tvSj = (TextView) view.findViewById(R.id.tv_cgq_sj);
                viewHolder.tvSfzc = (TextView) view.findViewById(R.id.tv_cgq_sfzc);
                viewHolder.tvTime = (TextView) view.findViewById(R.id.tv_cgq_time);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Hjzb hjzb = hjzbs.get(hjzbs.size() - position - 1);
            if (positionCgq == 0) {
                viewHolder.tvSj.setText(hjzb.getTemperature() + "");
            } else if (positionCgq == 1) {
                viewHolder.tvSj.setText(hjzb.getHumidity() + "");
            } else if (positionCgq == 2) {
                viewHolder.tvSj.setText(hjzb.getLightIntensity() + "");
            } else if (positionCgq == 3) {
                viewHolder.tvSj.setText(hjzb.getCo2() + "");
            } else if (positionCgq == 4) {
                viewHolder.tvSj.setText(hjzb.get_$Pm25184() + "");
            }
            viewHolder.tvTime.setText(hjzb.getTiem());
            viewHolder.tvCgq.setText(Cgq);
            viewHolder.tvSfzc.setText("正常");
            return view;
        }

        class ViewHolder {
            public TextView tvCgq;
            public TextView tvSj;
            public TextView tvSfzc;
            public TextView tvTime;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isNet=false;
    }
}
