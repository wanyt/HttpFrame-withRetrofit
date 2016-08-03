package com.okhttppractices.wanyt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.okhttppractices.wanyt.framelib.NetworkError;
import com.okhttppractices.wanyt.network.CallBackWrapper;
import com.okhttppractices.wanyt.network.ObserverWrapper;
import com.okhttppractices.wanyt.network.netrequester.RxRequester;
import com.okhttppractices.wanyt.network.responsemodel.User;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_login_rx)
    Button btLoginRx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_login)
    public void getHotThing(){
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", "aabc");
        map.put("pwd", "aabc11");
        map.put("sid", "21000000000");

        Call<User> call = com.okhttppractices.wanyt.network.netrequester.Requester.getInstance().requestMenu(map);

        call.enqueue(new CallBackWrapper<User>() {
            @Override
            protected void success(Call<User> call, User body) {
                Logger.d(call.request());
                Logger.d(body.systime);
            }

            @Override
            protected void error(Call<User> call, NetworkError t) {
                Logger.d(t.toString());
            }
        });



    }

    @OnClick(R.id.bt_login_rx)
    public void login(){
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", "aabc");
        map.put("pwd", "aabc11");
        map.put("sid", "21000000000");

        Observable<User> userObservable = RxRequester.getInstance().requestMenu(map);

        userObservable
                .subscribe(new ObserverWrapper<User>() {
                    @Override
                    protected void next(User user) {
                        Logger.d( "success:"+user.systime);
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
