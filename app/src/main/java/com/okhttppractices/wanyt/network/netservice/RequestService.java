package com.okhttppractices.wanyt.network.netservice;

import com.okhttppractices.wanyt.network.responsemodel.Menu;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created on 2016/8/3 14:38
 * <p>
 * author wanyt
 * <p>
 * Description:没有使用RxJava的请求方法
 */
public interface RequestService {

    @GET("/cook/query.php")
    Call<Menu> menu(@QueryMap Map<String, String> param);

}
