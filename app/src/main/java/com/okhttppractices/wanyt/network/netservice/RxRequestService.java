package com.okhttppractices.wanyt.network.netservice;

import com.okhttppractices.wanyt.network.responsemodel.Menu;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created on 2016/8/3 16:28
 * <p>
 * author wanyt
 * <p>
 * Description:使用RxJava的请求方法
 */
public interface RxRequestService {

    @GET("/cook/query.php")
    Observable<Menu> menu(@QueryMap Map<String, String> map);

}
