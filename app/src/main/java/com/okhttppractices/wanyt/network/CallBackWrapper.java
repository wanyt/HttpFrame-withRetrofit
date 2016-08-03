package com.okhttppractices.wanyt.network;

import com.okhttppractices.wanyt.ErrorMsg;
import com.okhttppractices.wanyt.framelib.NetworkError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 2016/8/3 14:51
 * <p/>
 * author wanyt
 * <p/>
 * Description:网络响应回调的包装类
 */
public abstract class CallBackWrapper<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.code() == 200){
            success(call, response.body());
        }else{
            error(call, new NetworkError(response.code(), ErrorMsg.error_service_msg));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        error(call,
                new NetworkError(ErrorMsg.error_network_unknown, ErrorMsg.error_network_unknown_msg));
    }

    protected abstract void success(Call<T> call, T body);
    protected abstract void error(Call<T> call, NetworkError t);

}
