package com.okhttppractices.wanyt.network.netrequester;

import com.okhttppractices.wanyt.framelib.RequestEngine;
import com.okhttppractices.wanyt.network.netservice.RxRequestService;
import com.okhttppractices.wanyt.network.responsemodel.Menu;

import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 2016/8/3 16:30
 * <p>
 * author wanyt
 * <p>
 * Description:使用RxJava的请求发起
 */
public class RxRequester {
    private RxRequester() {}
    public static RxRequester getInstance(){
        return Single.rxRequester;
    }
    private static class Single{
        private static RxRequester rxRequester = new RxRequester();
    }

    public Observable<Menu> requestMenu(Map<String, String> map){
        return getService().menu(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取请求服务
     * @return
     */
    private RxRequestService getService(){
        return RequestEngine.instance().createRxRequest(RxRequestService.class);
    }
}
