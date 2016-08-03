package com.okhttppractices.wanyt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.okhttppractices.wanyt.framelib.NetworkError;
import com.okhttppractices.wanyt.network.CallBackWrapper;
import com.okhttppractices.wanyt.network.ObserverWrapper;
import com.okhttppractices.wanyt.network.netrequester.Requester;
import com.okhttppractices.wanyt.network.netrequester.RxRequester;
import com.okhttppractices.wanyt.network.responsemodel.Menu;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_login_rx)
    Button btLoginRx;
    @BindView(R.id.rl_cook)
    RecyclerView rlCook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerViewConfig();
    }

    private void recyclerViewConfig() {
        rlCook.setLayoutManager(new LinearLayoutManager(this));
//        MenuAdapter adapter = new MenuAdapter();

    }

    @OnClick(R.id.bt_login)
    public void getHotThing(){
        HashMap<String, String> map = new HashMap<>();
        map.put("menu", "红烧肉");
        map.put("dtype", "json");
        map.put("pn", "0");
        map.put("rn", "5");
        map.put("albums", "");
        map.put("key", "259b339f1d15119eb310d72228bccd67");

        Requester.getInstance()
                .requestMenu(map)
                .enqueue(new CallBackWrapper<Menu>() {
                    @Override
                    protected void success(Call<Menu> call, Menu cook) {
                        Logger.d(call.request());
                        Logger.d(cook.toString());
                    }

                    @Override
                    protected void error(Call<Menu> call, NetworkError t) {
                        Logger.d(t.toString());
                    }
                });

    }

    @OnClick(R.id.bt_login_rx)
    public void login(){
        HashMap<String, String> map = new HashMap<>();
        map.put("menu", "红烧肉");
        map.put("dtype", "json");
        map.put("pn", "0");
        map.put("rn", "5");
        map.put("albums", "");
        map.put("key", "259b339f1d15119eb310d72228bccd67");

        RxRequester.getInstance()
                .requestMenu(map)
                .subscribe(new ObserverWrapper<Menu>() {
                    @Override
                    protected void next(Menu cook) {
                        Logger.d( "success:"+cook.toString());
                    }

                    @Override
                    protected void completed() {
                        Logger.d("completed");
                    }

                    @Override
                    protected void error(NetworkError networkError) {
                        Logger.d(networkError.toString());
                    }
                });
    }

}
