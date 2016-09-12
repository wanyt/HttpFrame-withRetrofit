package com.okhttppractices.wanyt.framelib;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created on 2016/8/31 17:48
 * <p>
 * author 我才不跟你扯淡
 * <p>
 * Description: 添加请求头的拦截器
 */
public class HttpHeadersInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder header = request.newBuilder()
                .header("platform", "android")
                .header("platform", "android")
                .header("platform", "android")
                .header("platform", "android")
                .header("platform", "android")
                .header("platform", "android")
                .header("platform", "android");

        Request build = header.build();
        return chain.proceed(build);
    }
}
