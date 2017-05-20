package com.fz.zreo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fz.zreo.bean.ZhangHu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zero on 2017/5/17.
 */

public class ZdglActivity extends Activity {
    private ListView mListView;
    private List<ZhangHu> zhangHuList;
    private ZdglAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zdgl);
        initDatas();
        initView();

    }

    private void initDatas() {
        zhangHuList=new ArrayList<>();
        ZhangHu zh;
        for(int i=0;i<4;i++){
            zh=new ZhangHu();
            zh.setCh(i+1+"");
            zh.setCzr("admin");
            zh.setJe("100"+i);
            zh.setCzsj("2017.03.18 13:23");
            zhangHuList.add(zh);
        }
    }

    private void initView() {
        mListView= (ListView) findViewById(R.id.lv_zdgl);
        adapter=new ZdglAdapter();
        mListView.setAdapter(adapter);
    }

    class ZdglAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return zhangHuList.size();
        }

        @Override
        public Object getItem(int position) {
            return zhangHuList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
           ViewHolder holder;
            if (view==null){
                holder=new ViewHolder();
                view= LayoutInflater.from(ZdglActivity.this)
                        .inflate(R.layout.zdgl_lv_item,null);
                holder.tvXh= (TextView) view.findViewById(R.id.tv_zdgl_xh);
                holder.tvCh= (TextView) view.findViewById(R.id.tv_zdgl_ch);
                holder.tvCzr= (TextView) view.findViewById(R.id.tv_zdgl_czr);
                holder.tvJe= (TextView) view.findViewById(R.id.tv_zdgl_czje);
                holder.tvCzsj= (TextView) view.findViewById(R.id.tv_zdgl_czsj);
                view.setTag(holder);
            }else {
                holder= (ViewHolder) view.getTag();
            }
            holder.tvXh.setText(position+1+"");
            holder.tvCh.setText(zhangHuList.get(position).getCh());
            holder.tvCzr.setText(zhangHuList.get(position).getCzr());
            holder.tvJe.setText(zhangHuList.get(position).getJe());
            holder.tvCzsj.setText(zhangHuList.get(position).getCzsj());
            return view;
        }

        class ViewHolder{
            private TextView tvXh;
            private TextView tvCh;
            private TextView tvCzr;
            private TextView tvJe;
            private TextView tvCzsj;
        }
    }
}
