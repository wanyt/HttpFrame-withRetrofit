package com.okhttppractices.wanyt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.okhttppractices.wanyt.Request.Requester;
import com.okhttppractices.wanyt.http.HttpMethod;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button btRequest;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_hot)
    Button btHot;
//    @BindView(R.id.edit_url)
//    EditText etUrl;

//    private HttpMethod requestMethod;
//    private HttpMethod phpMethods;

    private HttpMethod newRequester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        RetrofitBuilder request = RetrofitBuilder.getIntance();

//        requestMethod =  request.getHttpMethods();
//        phpMethods = request.getPhpMethods();

        Requester requester = Requester.instance();
        newRequester = requester.createRxRequest(HttpMethod.class);
    }

    @OnClick(R.id.bt_hot)
    public void getHotThing(){

    }

    @OnClick(R.id.bt_login)
    public void login(){
        HashMap<String, String> map = new HashMap<>();

        map.put("userName", "aabc");
        map.put("pwd", "aabc11");
        map.put("sid", "21000000000");

//        Observable<ResponseBody> login = newRequester.login(map);

        newRequester.login(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String response = responseBody.string();
                            Logger.d(response);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
        });

    }

    @OnClick(R.id.button)
    public void sendARequest(){

    }

}
