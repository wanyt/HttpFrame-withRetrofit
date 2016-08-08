package com.okhttppractices.wanyt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.okhttppractices.wanyt.framelib.NetworkError;
import com.okhttppractices.wanyt.network.CallBackWrapper;
import com.okhttppractices.wanyt.network.ObserverWrapper;
import com.okhttppractices.wanyt.network.netrequester.Requester;
import com.okhttppractices.wanyt.network.netrequester.RxRequester;
import com.okhttppractices.wanyt.network.responsemodel.Menu;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
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
    @BindView(R.id.rl_menu_header)
    RelativeLayout rlHeader;
    private MenuListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerViewConfig();
    }

    ArrayList<Menu.ResultBean.Cook> listMenu = new ArrayList<>();
    private int scrollFlag = 0;

    private void recyclerViewConfig() {
        rlCook.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MenuListAdapter(this, listMenu);
        rlCook.setAdapter(adapter);

        rlCook.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                boolean shown = rlHeader.isShown();
                if(scrollFlag == 1 && newState == 0){
                    if(shown){
                        animHide();
                        rlHeader.setVisibility(View.GONE);
                    }
                }else if(scrollFlag == -1 && newState == 0){
                    if(!shown){
                        animShow();
                        rlHeader.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {//上滑
                    scrollFlag = 1;
                } else {//下滑
                    scrollFlag = -1;
                }
            }
        });
    }

    private void animHide() {
        int height = rlHeader.getHeight();
        TranslateAnimation anima = new TranslateAnimation(0, 0, 0, -height);
        anima.setDuration(500);
        anima.setFillAfter(true);
        rlHeader.startAnimation(anima);
        rlHeader.setVisibility(View.GONE);
    }

    private void animShow() {
        int height = rlHeader.getHeight();
        TranslateAnimation anima = new TranslateAnimation(0, 0, -height, 0);
        anima.setDuration(500);
        anima.setFillAfter(true);
        rlHeader.startAnimation(anima);
        rlHeader.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.bt_login)
    public void getHotThing() {
        HashMap<String, String> map = new HashMap<>();
        map.put("menu", "红烧肉");
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
                        ArrayList<Menu.ResultBean.Cook> data = cook.result.data;
                        adapter.setData(data);
                    }

                    @Override
                    protected void error(Call<Menu> call, NetworkError t) {
                        Logger.d(t.toString());
                    }
                });
    }

    @OnClick(R.id.bt_login_rx)
    public void login() {
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
                        Logger.d("success:" + cook.toString());
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

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.main_menu_normal:
                Logger.d("normal");
                break;
            case R.id.main_menu_rx:
                Logger.d("rx");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
