package com.okhttppractices.wanyt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.okhttppractices.wanyt.request.Requester;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

        Call<User> login = Requester.requestLogin(map);
        login.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User body = response.body();
                Logger.d(body.systime);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.bt_login_rx)
    public void login(){
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", "aabc");
        map.put("pwd", "aabc11");
        map.put("sid", "21000000000");

        Requester.requestLoginRx(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("request completed");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {
                        Logger.d( "success:"+user.systime);
                    }
                });
    }

}
