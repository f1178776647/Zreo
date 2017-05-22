package com.fz.zreo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fz.zreo.frament.CGQFra;
import com.fz.zreo.frament.DLZTFra;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.Date;


public class MainActivity extends Activity implements View.OnClickListener {
    private long mExitTime;
    private SlidingMenu menu;
    private TextView tvLeftMenuCXczhcz;
    private LinearLayout llMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLeftMenu();
        initView();
        setFrament();
    }

    private void setFrament() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.add(R.id.ll_main, new CGQFra());
        trans.commit();
    }

    private void initView() {
        llMain = (LinearLayout) findViewById(R.id.ll_main);
        tvLeftMenuCXczhcz = (TextView) findViewById(R.id.tv_leftmenu_xczhcz);
        tvLeftMenuCXczhcz.setOnClickListener(this);
    }

    private void setLeftMenu() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.left_menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();

            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_leftmenu_xczhcz:
                //Toast.makeText(this,tvLeftMenuCXczhcz.getText().toString(),Toast.LENGTH_LONG).show();

                break;
        }
    }
}
