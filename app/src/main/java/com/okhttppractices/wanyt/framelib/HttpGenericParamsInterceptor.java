package com.okhttppractices.wanyt.framelib;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created on 2016/8/31 17:44
 * <p>
 * author 我才不跟你扯淡
 * <p>
 * Description: 添加公共参数的拦截器
 */
public class HttpGenericParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        HttpUrl httpUrl = request.url().newBuilder()
                .addQueryParameter("userName", "aabc")
                .addQueryParameter("pwd", "aabc11")
                .addQueryParameter("pwd", "aabc11")
                .build();
        Request requestWithParam = request.newBuilder().url(httpUrl).build();

        return chain.proceed(requestWithParam);

    }
}
