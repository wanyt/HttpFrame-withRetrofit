package com.okhttppractices.wanyt.http;

import com.okhttppractices.wanyt.request.HttpApi;
import com.okhttppractices.wanyt.request.HttpMethod;

import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 2016/7/4 11:05
 * <p>
 * author wanyt
 * <p>
 * Description:获取Retrofit，在这里可以对请求的url进行操作，比如添加请求头
 */
public class RetrofitBuilder {

    private RetrofitBuilder() {}

    private static RetrofitBuilder retrofitBuilder;

    public static RetrofitBuilder getIntance(){
        if(retrofitBuilder == null){
            retrofitBuilder = new RetrofitBuilder();
        }
        return retrofitBuilder;
    }

    public HttpMethod getHttpMethods(){

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        builder.addInterceptor(httpLoggingInterceptor);

        OkHttpClient client = builder.build();

        Retrofit build = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HttpApi.domain)
                .build();

        return build.create(HttpMethod.class);
    }

    public HttpMethod getPhpMethods(){

        //拦截请求和响应,并打印log
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        builder.addInterceptor(httpLoggingInterceptor);

        OkHttpClient client = builder.build();

        Retrofit build = new Retrofit.Builder()
                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HttpApi.domain_php)
                .build();

        Retrofit.Builder builder1 = new Retrofit.Builder();

        return build.create(HttpMethod.class);
    }

}
