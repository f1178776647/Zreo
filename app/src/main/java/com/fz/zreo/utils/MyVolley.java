package com.fz.zreo.utils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by Zero on 2017/5/22.
 */

public class MyVolley {
    private JsonObjectRequest request;
    public void postJsonByJson(final VolleyRespons respons, String url,
                               JSONObject object, final int type) {
        request = new JsonObjectRequest(url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        respons.onSuccessResponseJson(object, type);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        respons.onFailResponseJson(volleyError);
                    }
                });
        request.setTag("postJson");
        request.setRetryPolicy(new DefaultRetryPolicy());
        MyApplication.getRequestQueue().add(request);
    }
    public void postImgByUrl(){

    }
}