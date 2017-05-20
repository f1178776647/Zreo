package com.fz.zreo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * Created by Zero on 2017/5/19.
 */

public class DaoLuZhanTaiActivity extends Activity {
    private Spinner mSpinner;
    private String[] spinners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daoluzhangtai);
        initDatas();
        initView();
    }

    private void initDatas() {
        spinners = new String[]{
                "道路状态升序",
                "道路状态降序"
        };
    }

    private void initView() {
        mSpinner = (Spinner) findViewById(R.id.spn_daolu_spinner);
        mSpinner.setGravity(Gravity.CENTER);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinners);
        mSpinner.setAdapter(adapter);
    }
}
