package com.okhttppractices.wanyt;

import android.app.Application;

import com.okhttppractices.wanyt.network.responsemodel.Menu;

import java.util.List;

/**
 * Created on 2016/8/8 15:09
 * <p/>
 * author wanyt
 * <p/>
 * Description:
 */
public class App extends Application {
    private App() {}
    private static class Single{
        private static final App app = new App();
    }
    public static App getInstance(){
        return Single.app;
    }

    public List<Menu.ResultBean.Cook.StepsBean> getSteps() {
        return steps;
    }

    public void setSteps(List<Menu.ResultBean.Cook.StepsBean> steps) {
        this.steps = steps;
    }

    public List<Menu.ResultBean.Cook.StepsBean> steps;

}
