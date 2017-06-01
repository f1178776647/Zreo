package com.fz.zreo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * Created by Zero on 2017/6/1.
 */

public class BarChartActivity extends Activity {
    private BarChart mBarChart;
    public ArrayList<BarEntry> entries = new ArrayList<>();
    public ArrayList<BarEntry> entries1 = new ArrayList<>();
    public BarDataSet dataSet, dataSet1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barchart_activity);
        initEntriesData();
        initView();
        show();
    }

    private void initView() {
        mBarChart = (BarChart) findViewById(R.id.bc_bar_main);
        mBarChart.getAxisRight().setEnabled(false);
        XAxis xAxis=mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mBarChart.getAxisLeft().setDrawGridLines(false);
        mBarChart.getXAxis().setDrawGridLines(false);
    }

    public void initEntriesData() {
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));
        entries.add(new BarEntry(4f, 6));
        entries.add(new BarEntry(8f, 7));
        entries.add(new BarEntry(6f, 8));
        entries.add(new BarEntry(12f, 9));
        entries.add(new BarEntry(18f, 10));
        entries.add(new BarEntry(9f, 11));

        entries1.add(new BarEntry(3f, 0));
        entries1.add(new BarEntry(6f, 1));
        entries1.add(new BarEntry(7f, 2));
        entries1.add(new BarEntry(8f, 3));
        entries1.add(new BarEntry(20f, 4));
        entries1.add(new BarEntry(12f, 5));
        entries1.add(new BarEntry(6f, 6));
        entries1.add(new BarEntry(10f, 7));
        entries1.add(new BarEntry(4f, 8));
        entries1.add(new BarEntry(10f, 9));
        entries1.add(new BarEntry(13f, 10));
        entries1.add(new BarEntry(13f, 11));
    }

    public void show() {
        dataSet = new BarDataSet(entries, "一季度");
        for(int i=0;i<entries.size();i++){
            if (entries.get(i).getVal()>10f){
                dataSet.setValueTextColor(Color.RED);
            }else {
                dataSet.setValueTextColor(Color.BLACK);
            }
        }
        dataSet.setColor(Color.rgb(0, 255, 125));
        dataSet1 = new BarDataSet(entries1, "二季度");
        dataSet1.setColor(Color.rgb(255, 0, 125));
        ArrayList<BarDataSet> dataSets = new ArrayList<>(); //坐标线的集合。
        dataSets.add(dataSet);
        //dataSets.add(dataSet1);
        ArrayList<String> xValues = new ArrayList<>(); //x轴坐标
        for (int i = 1; i < 13; i++) {
            xValues.add("第" + i + "次");
        }
        BarData data = new BarData(xValues, dataSets);
        mBarChart.setData(data);
        mBarChart.animateY(2000);//动画效果 y轴方向，2秒
        mBarChart.setDescription("");
        BarData barData = mBarChart.getBarData();
        BarDataSet barDataSet=barData.getDataSetByIndex(0);
        barDataSet.setValueTextColor(Color.RED);
    }
}