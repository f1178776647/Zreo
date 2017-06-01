package com.fz.zreo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zero on 2017/5/24.
 */

public class TubiaoActivity extends Activity {
    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tubiao);
        initView();
        initData();
        initLine();
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true)
                    try {
                        sleep(2000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                addEntry();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }.start();
    }

    private void initLine() {
        mLineChart.setDescription("");
    }

    private void initData() {
        List<String> xValues = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            xValues.add("" + i);
        }
        final ArrayList<Entry> yValue = new ArrayList<>();
        ArrayList<Entry> yValue2 = new ArrayList<>();
        yValue2.add(new Entry(8, 1));
        yValue2.add(new Entry(9, 2));
        yValue2.add(new Entry(10, 3));
        yValue2.add(new Entry(7, 4));
        yValue2.add(new Entry(-3, 5));
        yValue2.add(new Entry(7, 6));
        yValue2.add(new Entry(12, 7));
        yValue2.add(new Entry(1, 8));
        LineDataSet dataSet1 = new LineDataSet(yValue, null);
        dataSet1.setColor(Color.RED);
        dataSet1.setCircleColor(Color.RED);
        dataSet1.setDrawValues(false);

        LineDataSet dataSet2 = new LineDataSet(yValue2, null);
        dataSet2.setColor(Color.YELLOW);
        dataSet2.setCircleColor(Color.YELLOW);
        dataSet2.setDrawValues(false);

        ArrayList<LineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet1);
        dataSets.add(dataSet2);
        LineData lineData = new LineData(xValues, dataSets);
        mLineChart.setData(lineData);
        mLineChart.animateY(2000, Easing.EasingOption.Linear);
        mLineChart.animateX(2000, Easing.EasingOption.Linear);
        mLineChart.invalidate();

    }

    private void addEntry() {
        LineData data = mLineChart.getData();
        LineDataSet set = data.getDataSetByIndex(0);
        float f = (float) ((Math.random()) * 20);
        Entry entry = new Entry(f, set.getEntryCount()+1);
        data.addEntry(entry, 0);
        mLineChart.notifyDataSetChanged();
        mLineChart.setVisibleXRangeMaximum(16);
        if(set.getEntryCount()>12){
            data.addXValue((data.getXValCount()) + "");
        }
        mLineChart.moveViewToX(data.getXValCount() - 1);
    }

    private void initView() {
        mLineChart = (LineChart) findViewById(R.id.lc_line);
        mLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mLineChart.getAxisRight().setEnabled(false);
        mLineChart.getAxisLeft().setDrawGridLines(false);
        mLineChart.getXAxis().setDrawGridLines(false);
    }
}
