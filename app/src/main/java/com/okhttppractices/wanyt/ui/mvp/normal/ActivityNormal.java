package com.okhttppractices.wanyt.ui.mvp.normal;

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
import com.okhttppractices.wanyt.network.responsemodel.Menu;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2016/8/10 10:37
 * <p>
 * author wanyt
 * <p>
 * Description:不使用RxJava请求的Activity
 */
public class ActivityNormal extends AppCompatActivity implements ActivityNormalContract.View {

    @BindView(R.id.toolbar_normal)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar_normal)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.rl_cook)
    RecyclerView rlCook;
    private MenuListAdapter adapter;
    private ActivityNormalPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        collapsingToolbarLayout.setTitle("NORMAL");

        initList();

        presenter = new ActivityNormalPresenter(this);
        presenter.loadData("红烧肉");
    }

    private void initList() {
        rlCook.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MenuListAdapter(this);
        rlCook.setAdapter(adapter);
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void dismissLodingView() {

    }

    @Override
    public void setPresenter(ActivityNormalContract.Presenter presenter) {

    }

    @Override
    public void onLoadDataResult(Menu result) {
        ArrayList<Menu.ResultBean.Cook> data = result.result.data;
        adapter.setData(data);
    }

    @Override
    public void onLoadDataError(NetworkError error) {
        Logger.d(error.toString());
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
                presenter.loadData("红烧肉");
                break;
            case R.id.normal_menu_gobaojiding:
                presenter.loadData("宫保鸡丁");
                break;
            case R.id.normal_menu_maoxuewang:
                presenter.loadData("毛血旺");
                break;
            case R.id.normal_menu_soup:
                presenter.loadData("西湖牛肉羹");
                break;
            case R.id.normal_menu_roujiamo:
                presenter.loadData("肉夹馍");
                break;
            case R.id.normal_menu_fotiaoqiang:
                presenter.loadData("佛跳墙");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
