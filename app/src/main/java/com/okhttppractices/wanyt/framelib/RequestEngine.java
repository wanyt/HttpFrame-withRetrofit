package com.okhttppractices.wanyt.framelib;

import com.okhttppractices.wanyt.AppConfig;
import com.okhttppractices.wanyt.request.HttpApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 2016/7/9 13:27
 * <p>
 * author wanyt
 * <p>
 * Description:创建Retrofit，本类应该放在应用的框架层
 */
public class RequestEngine {

    private RequestEngine() {}
    public static RequestEngine instance(){
        return SingleInsance.requester;
    }
    private static class SingleInsance{
        private static final RequestEngine requester = new RequestEngine();
    }

    private static Retrofit retrofit;
    private static Retrofit retrofitWithRx;

    /**
     * 创建不带有RxJava的Retrofit
     */
    public <T> T createRequest(Class<T> clazz){
        if(retrofit == null){
            OkHttpClient client = setHttpConfig();
            retrofit = new Retrofit.Builder()
                    .baseUrl(HttpApi.domain)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit.create(clazz);
    }

    /**
     * 创建带有RxJava的Retrofit
     */
    public <T>T createRxRequest(Class<T> clazz){
        if(retrofitWithRx == null){
            OkHttpClient client = setHttpConfig();
            retrofitWithRx = new Retrofit.Builder()
                    .baseUrl(HttpApi.domain)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofitWithRx.create(clazz);
    }

    /**
     * 配置请求的公共参数，请求头，请求和响应的log打印，缓存最近的请求和响应
     * @return
     */
    private OkHttpClient setHttpConfig() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //这里可以设置公共参数
//        Interceptor genericParams = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//
//                HttpUrl httpUrl = request.url().newBuilder()
//                        .addQueryParameter("userName", "aabc")
//                        .addQueryParameter("pwd", "aabc11")
//                        .addQueryParameter("pwd", "aabc11")
//                        .build();
//                Request requestWithParam = request.newBuilder().url(httpUrl).build();
//
//                return chain.proceed(requestWithParam);
//            }
//        };
//        builder.addInterceptor(genericParams);

        //设置请求头
//        Interceptor headers = new Interceptor() {
//
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                Request.Builder header = request.newBuilder()
//                        .header("platform", "android")
//                        .header("platform", "android")
//                        .header("platform", "android")
//                        .header("platform", "android")
//                        .header("platform", "android")
//                        .header("platform", "android")
//                        .header("platform", "android");
//
//                Request build = header.build();
//                return chain.proceed(build);
//            }
//        };
//        builder.addInterceptor(headers);

        //设置超时
//        builder.connectTimeout(15, TimeUnit.SECONDS);
//        builder.readTimeout(20, TimeUnit.SECONDS);
//        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
//        builder.retryOnConnectionFailure(true);

        //Log拦截,以及缓存最近的请求和响应
        if(AppConfig.DEBUG){
            HttpLogInterceptor httpLoggingInterceptor = new HttpLogInterceptor();
            builder.addInterceptor(httpLoggingInterceptor);
        }

        return builder.build();

    }

}
