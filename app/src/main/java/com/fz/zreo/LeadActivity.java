package com.fz.zreo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by admin on 2017/5/13.
 */

public class LeadActivity extends Activity {
    private TextView tvTime;
    private MyHandler handler;
    private Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.lead_activity);
        handler = new MyHandler();
        tvTime = (TextView) findViewById(R.id.tv_lead_time);
        new Thread() {
            boolean flg = true;
            int time = 3;

            @Override
            public void run() {
                super.run();
                while (flg) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    message = new Message();
                    message.obj = time;
                    time--;
                    if (time == 0) {
                        flg = false;
                    }
                    handler.sendMessage(message);
                }
                Intent intent = new Intent(LeadActivity.this, NavActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int time = (int) msg.obj;
            tvTime.setText(time + "");
        }
    }
}
