package com.okhttppractices.wanyt.request;

import com.okhttppractices.wanyt.framelib.RequestEngine;

import java.util.HashMap;

import okhttp3.ResponseBody;
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
     * 登陆
     * @param queryMap
     * @return
     */
    public static Observable<ResponseBody> requestLogin(HashMap<String, String> queryMap){
        return getRequestEngineWithRx(HttpMethod.class).login(queryMap);
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
