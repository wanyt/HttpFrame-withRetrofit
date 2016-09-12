package com.okhttppractices.wanyt.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.okhttppractices.wanyt.MenuListAdapter;
import com.okhttppractices.wanyt.R;
import com.okhttppractices.wanyt.framelib.NetworkError;
import com.okhttppractices.wanyt.network.ObserverWrapper;
import com.okhttppractices.wanyt.network.netrequester.RxRequester;
import com.okhttppractices.wanyt.network.responsemodel.Menu;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2016/8/10 10:37
 * <p>
 * author wanyt
 * <p>
 * Description:使用RxJava请求的Activity
 */
public class ActivityRx extends AppCompatActivity {

    @BindView(R.id.toolbar_normal)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar_normal)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.rl_cook)
    RecyclerView rlCook;
    private MenuListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        collapsingToolbarLayout.setTitle("RX_REQUEST");

        initList();
        requestCook("红烧肉");
    }

    private void initList() {
        rlCook.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MenuListAdapter(this);
        rlCook.setAdapter(adapter);
    }

    private void requestCook(String cook) {
        HashMap<String, String> map = new HashMap<>();
        map.put("menu", cook);
        map.put("dtype", "json");
        map.put("pn", "0");
        map.put("rn", "20");
        map.put("albums", "");
        map.put("key", "259b339f1d15119eb310d72228bccd67");

        RxRequester.getInstance()
                .requestMenu(map)
                .subscribe(new ObserverWrapper<Menu>() {
                    @Override
                    protected void next(Menu cook) {
                        ArrayList<Menu.ResultBean.Cook> data = cook.result.data;
                        adapter.setData(data);
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
            case R.id.normal_menu_hongshaorou:
                requestCook("红烧肉");
                break;
            case R.id.normal_menu_gobaojiding:
                requestCook("宫保鸡丁");
                break;
            case R.id.normal_menu_maoxuewang:
                requestCook("毛血旺");
                break;
            case R.id.normal_menu_soup:
                requestCook("西湖牛肉羹");
                break;
            case R.id.normal_menu_roujiamo:
                requestCook("肉夹馍");
                break;
            case R.id.normal_menu_fotiaoqiang:
                requestCook("佛跳墙");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
