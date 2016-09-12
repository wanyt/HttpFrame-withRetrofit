package com.okhttppractices.wanyt.framelib;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created on 2016/8/31 17:54
 * <p>
 * author 我才不跟你扯淡
 * <p>
 * Description: 最近的请求和响应
 */
public class HttpRecentRecorder implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        ArrayList list = new ArrayList();
        return null;
    }
}
