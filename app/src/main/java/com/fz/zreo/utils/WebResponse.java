package com.fz.zreo.utils;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by Zero on 2017/5/18.
 */

public interface WebResponse {
    /**
     * 成功结果返回
     *
     * @param call
     * @param resultBean  结果Bean
     * @param requestCode 请求鉴别唯一的自定义CODE
     * @throws IOException
     */
    public void onSuccessResponse(Call call, String resultBean, int requestCode) throws IOException;

    /**
     * 失败结果返回
     *
     * @param call
     * @param e
     */
    public void onFailResponse(Call call, IOException e, int requestCode);
}
