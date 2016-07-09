package com.okhttppractices.wanyt.http;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created on 2016/6/30 14:10
 * <p>
 * author wanyt
 * <p>
 * Description:各个请求各个请求方法
 */
public interface HttpMethod {

    @GET("/act/actList")//这个可以抽出来，然后使用@Path注解获取
    Call<ResponseBody> huodong(
            @QueryMap Map<String, String> map);

    @GET("/center/login")
    Observable<ResponseBody> login(
            @QueryMap Map<String, String> map);

    /**
     * 没有参数的GET请求
     * @param action
     * @return
     */
    @GET("/{action}")
    Call<User> getNoParam(
            @Path(value = "action", encoded = true) String action);

    /**
     * 一个参数的GET请求
     * @param action
     * @param map
     * @return
     */
    @GET("/{action}")
    Call<User> getOneParam(
            @Path(value = "action", encoded = true) String action,
            @QueryMap Map<String, String> map);

    /**
     * 两个参数的GET请求
     * @param action
     * @return
     */
//    @GET("/{action}")
//    Call<User> getTwoParam(
//            @Path(value = "action", encoded = true) String action,
//            @Query String fir);

    /**
     * 多个参数的GET请求
     * @param action
     * @param map
     * @return
     */
    @GET("/{action}")
    Call<User> getMoreParam(
            @Path(value = "action", encoded = true) String action,
            @QueryMap Map<String, String> map);

    @POST("/app/api/getChannelTitleList")
    Call<ResponseBody> hot(
            @QueryMap Map<String, String> map);

//    @Get("/{apath}")
//    Call<ResponseBody>



}
