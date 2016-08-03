package com.okhttppractices.wanyt.network;

import com.okhttppractices.wanyt.ErrorMsg;
import com.okhttppractices.wanyt.framelib.NetworkError;

import rx.Observer;

/**
 * Created on 2016/8/3 16:26
 * <p>
 * author wanyt
 * <p>
 * Description:网络响应订阅者的包装类
 */
public abstract class ObserverWrapper<T> implements Observer<T> {

    @Override
    public void onCompleted() {
        completed();
    }

    @Override
    public void onError(Throwable e) {
        error(new NetworkError(ErrorMsg.error_network_unknown, ErrorMsg.error_network_unknown_msg));
    }

    @Override
    public void onNext(T t) {
        next(t);
    }

    protected abstract void next(T t);
    protected abstract void completed();
    protected abstract void error(NetworkError networkError);
}
