package com.okhttppractices.wanyt.ui.mvp;

/**
 * Created on 2016/8/30 15:00
 * <p>
 * author 我才不跟你扯淡
 * <p>
 * Description: 所有MVP view的基类
 */
public interface BaseMvpView<T> {

    void showLoadingView();

    void dismissLodingView();

    void setPresenter(T presenter);

}
