package com.okhttppractices.wanyt.http;

import android.util.Log;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 2016/7/4 15:05
 * <p/>
 * author wanyt
 * <p/>
 * Description:自定义的响应回调类
 */
public abstract class HttpCallback<T extends CommonModel> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        Request request = call.request();

        Log.i("okhttp: ", "1,,," + request.url());

        onSuccess(call.request(), response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(call.request(), t);
    }

    protected abstract void onSuccess(Request request, Response<T> response);

    protected abstract void onError(Request request, Throwable t);

}
