package com.fz.zreo.utils;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Zero on 2017/5/22.
 */

public interface VolleyRespons {
    public void onSuccessResponseJson(JSONObject jsonObject,int type);

    public void onFailResponseJson(VolleyError error);
}
