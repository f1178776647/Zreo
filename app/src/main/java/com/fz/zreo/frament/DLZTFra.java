package com.fz.zreo.frament;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fz.zreo.R;

/**
 * Created by Zero on 2017/5/22.
 */

public class DLZTFra extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.daoluzhangtai,container,false);
        return view;
    }
}
