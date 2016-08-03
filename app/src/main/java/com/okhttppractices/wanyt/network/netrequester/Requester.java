package com.okhttppractices.wanyt.network.netrequester;

import com.okhttppractices.wanyt.network.responsemodel.User;
import com.okhttppractices.wanyt.framelib.RequestEngine;
import com.okhttppractices.wanyt.network.netservice.RequestService;

import java.util.Map;

import retrofit2.Call;

/**
 * Created on 2016/8/3 16:00
 * <p>
 * author wanyt
 * <p>
 * Description:没有使用RxJava的请求发起类
 */
public class Requester {

    private Requester() {}
    public static Requester getInstance(){return Single.requester;}
    private static class Single{
        private static Requester requester = new Requester();
    }

    /**
     * 请求方法
     * @param param
     * @return
     */
    public Call<User> requestMenu(Map<String, String> param){
        return getRequestEngine().login(param);
    }

    /**
     * 获取Retrofit
     * @return
     */
    public RequestService getRequestEngine(){
        return RequestEngine.instance().createRequest(RequestService.class);
    }
}
