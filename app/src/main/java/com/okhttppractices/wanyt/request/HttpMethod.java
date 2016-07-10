package com.okhttppractices.wanyt.request;

import com.okhttppractices.wanyt.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created on 2016/6/30 14:10
 * <p>
 * author wanyt
 * <p>
 * Description:具体的请求方法
 */
public interface HttpMethod {

    @GET("/center/login")
    Call<User> login(
            @QueryMap Map<String, String> map);

    @GET("/center/login")
    Observable<User> loginRx(
            @QueryMap Map<String, String> map);

}
