package com.fz.zreo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/5/13.
 */

public class NavActivity extends Activity {
    private Button btnNav;
    private ViewPager vpNav;
    private View viewBar1;
    private View viewBar2;
    private List<ImageView> imgs;
    private int[] imgsId = new int[]{R.drawable.bar1, R.drawable.bar2};
    private NavAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity);
        initDatas();
        initView();
        event();
    }

    private void setImgBar(int position) {
        switch (position) {
            case 0:
                viewBar1.setBackgroundColor(Color.RED);
                viewBar2.setBackgroundColor(Color.BLUE);
                btnNav.setVisibility(View.GONE);
                break;
            case 1:
                viewBar1.setBackgroundColor(Color.BLUE);
                viewBar2.setBackgroundColor(Color.RED);
                btnNav.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void initDatas() {
        imgs = new ArrayList<>();
        for (int i = 0; i < imgsId.length; i++) {
            ImageView img = new ImageView(this);
            img.setImageResource(imgsId[i]);
            imgs.add(img);
        }
    }

    private void event() {
        btnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavActivity.this, HjzbActivity.class);
                startActivity(intent);
                finish();
            }
        });

        vpNav.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setImgBar(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        btnNav = (Button) findViewById(R.id.btn_start_app);
        vpNav = (ViewPager) findViewById(R.id.vp_nav);
        viewBar1 = findViewById(R.id.view_bar1);
        viewBar2 = findViewById(R.id.view_bar2);

        adapter = new NavAdapter(imgs);
        vpNav.setAdapter(adapter);
    }


    class NavAdapter extends PagerAdapter {
        private List<ImageView> imgs;

        public NavAdapter(List<ImageView> imgs) {
            this.imgs = imgs;
        }

        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView img = imgs.get(position);
            container.addView(img);
            return img;
        }
    }
}
