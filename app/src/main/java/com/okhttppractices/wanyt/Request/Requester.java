package com.okhttppractices.wanyt.Request;

import com.okhttppractices.wanyt.AppConfig;
import com.okhttppractices.wanyt.http.HttpApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created on 2016/7/9 13:27
 * <p>
 * author wanyt
 * <p>
 * Description:创建Retrofit
 */
public class Requester {

    private Requester() {}
    public static Requester instance(){
        return SingleInsance.requester;
    }
    private static class SingleInsance{
        private static final Requester requester = new Requester();
    }

    private static Retrofit retrofit;
    private static Retrofit retrofitWithRx;

    /**
     * 不带RxJava的请求
     */
    public void createRequest(){

    }

    /**
     * 带RxJava的请求
     */
    public <T>T createRxRequest(Class<T> clazz){
        return createServiceWithRx().create(clazz);
    }

    private Retrofit createServiceWithRx() {
        if(retrofitWithRx == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            //设置公共参数
//            Interceptor interceptor = new Interceptor() {
//
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request request = chain.request();
////                    Response response = chain.proceed(request);
//
//                    Logger.d(request.body());
////                    Logger.d(response.body());
//
//                    return chain.proceed(request);
//                }
//            };
//            builder.addInterceptor(interceptor);
            //设置请求头

            //Log拦截
            if(AppConfig.DEBUG){
                RequestLoggingInterceptor httpLoggingInterceptor = new RequestLoggingInterceptor();
//                httpLoggingInterceptor.setLevel(RequestLoggingInterceptor.Level.BODY);
                builder.addInterceptor(httpLoggingInterceptor);
            }

            OkHttpClient client = builder.build();
            retrofitWithRx = new Retrofit.Builder()
                    .baseUrl(HttpApi.domain)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofitWithRx;
    }


}
