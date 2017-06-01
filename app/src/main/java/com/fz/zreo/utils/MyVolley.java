package com.fz.zreo.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Zero on 2017/5/22.
 */

public class MyVolley {
    public static final String URL="http://192.168.5.24:8080/transportservice/action/";
    private JsonObjectRequest request;
    private StringRequest stringRequest;

    public void postHttpByJson(final VolleyResponse response, String url,
                               JSONObject object, final int type) {
        request = new JsonObjectRequest(Request.Method.POST,url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        response.onSuccessResponseJson(object, type);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        response.onFailResponseJson(volleyError);
                    }
                });
        request.setTag("postJson");
        MyApplication.getRequestQueue().add(request);
    }


    public void postHttpByMap(final VolleyResponse response, String url,
                              final Map<String,String> map, final int type){
        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    response.onSuccessResponseJson(new JSONObject(s),type);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        request.setTag("postJson");
        MyApplication.getRequestQueue().add(request);
    }

}
