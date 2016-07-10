package com.okhttppractices.wanyt.request;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("/act/actList")
    Call<ResponseBody> huodong(
            @QueryMap Map<String, String> map);

    @GET("/center/login")
    Observable<ResponseBody> login(
            @QueryMap Map<String, String> map);


    @POST("/app/api/getChannelTitleList")
    Call<ResponseBody> hot(
            @QueryMap Map<String, String> map);

}
