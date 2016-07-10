package com.okhttppractices.wanyt.request;

import com.okhttppractices.wanyt.User;
import com.okhttppractices.wanyt.framelib.RequestEngine;

import java.util.HashMap;

import retrofit2.Call;
import rx.Observable;

/**
 * Created on 2016/7/10 12:40
 * <p/>
 * author wanyt
 * <p/>
 * Description:发起请求，返回可以获取数据的对象
 */
public class Requester {

    /**
     * 登陆Rx
     * @param queryMap
     * @return
     */
    public static Observable<User> requestLoginRx(HashMap<String, String> queryMap){
        return getRequestEngineWithRx(HttpMethod.class).loginRx(queryMap);
    }

    public static Call<User> requestLogin(HashMap<String, String> queryMap){
        return getRequestEngine(HttpMethod.class).login(queryMap);
    }

    /**
     * 获取不带有RxJava的Retrofit实例
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getRequestEngine(Class<T> clazz){
        return RequestEngine.instance().createRequest(clazz);
    }

    /**
     * 获取带有RxJava的Retrofit实例
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getRequestEngineWithRx(Class<T> clazz){
        return RequestEngine.instance().createRxRequest(clazz);
    }

}
