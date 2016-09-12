package com.okhttppractices.wanyt.ui.mvp.normal;

import com.okhttppractices.wanyt.framelib.NetworkError;
import com.okhttppractices.wanyt.network.CallBackWrapper;
import com.okhttppractices.wanyt.network.netrequester.Requester;
import com.okhttppractices.wanyt.network.responsemodel.Menu;

import java.util.HashMap;

import retrofit2.Call;

/**
 * Created on 2016/8/30 15:12
 * <p>
 * author 我才不跟你扯淡
 * <p>
 * Description:
 */
public class ActivityNormalPresenter implements ActivityNormalContract.Presenter {

    private ActivityNormalContract.View view;

    public ActivityNormalPresenter(ActivityNormalContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadData(String cook) {
        HashMap<String, String> map = new HashMap<>();
        map.put("menu", cook);
        map.put("dtype", "json");
        map.put("pn", "0");
        map.put("rn", "20");
        map.put("albums", "");
        map.put("key", "259b339f1d15119eb310d72228bccd67");

        Requester.getInstance()
                .requestMenu(map)
                .enqueue(new CallBackWrapper<Menu>() {
                    @Override
                    protected void success(Call<Menu> call, Menu cook) {
                        view.onLoadDataResult(cook);
                    }

                    @Override
                    protected void error(Call<Menu> call, NetworkError t) {
                        view.onLoadDataError(t);
                    }
                });
    }
}
