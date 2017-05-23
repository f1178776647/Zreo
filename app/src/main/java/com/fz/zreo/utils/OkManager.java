package com.fz.zreo.utils;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.fz.zreo.bean.Hjzb;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Zero on 2017/5/18.
 */

public class OkManager {
    private OkHttpClient mOkHttpClient;
    private static Handler handler;
    private static OkManager okManager;
    private Context context;

    public static OkManager getInstance() {
        if (okManager == null) {
            okManager = new OkManager();
            handler = new Handler(Looper.getMainLooper());
        }
        return okManager;
    }

    public String postSyncHttp(String url, String json) {
        mOkHttpClient = new OkHttpClient();
        Response response = null;
        String str = null;
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody formBody = RequestBody.create(mediaType, json);
        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            str = String.valueOf(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public void postAsynHttp(final WebResponse webResponse, String url, String json) {
        mOkHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody formBody = RequestBody.create(mediaType, json);
        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String str = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            webResponse.onSuccessResponse(call, str, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }


}
