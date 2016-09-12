package com.okhttppractices.wanyt.ui.mvp.normal;

import com.okhttppractices.wanyt.framelib.NetworkError;
import com.okhttppractices.wanyt.network.responsemodel.Menu;
import com.okhttppractices.wanyt.ui.mvp.BaseMvpPresenter;
import com.okhttppractices.wanyt.ui.mvp.BaseMvpView;

/**
 * Created on 2016/8/30 15:07
 * <p>
 * author 我才不跟你扯淡
 * <p>
 * Description:
 */
public interface ActivityNormalContract {

    interface Presenter extends BaseMvpPresenter{
        void loadData(String cook);
    }

    interface View extends BaseMvpView<Presenter>{
        void onLoadDataResult(Menu result);

        void onLoadDataError(NetworkError error);
    }

}
