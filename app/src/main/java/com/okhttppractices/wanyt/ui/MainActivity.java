package com.okhttppractices.wanyt.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.okhttppractices.wanyt.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_main_request_normal)
    Button btNormal;
//    @BindView(R.id.bt_main_request_rxjava)
//    Button btRx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_main_request_normal)
    public void click(){
        Intent intent = new Intent(this, ActivityNormal.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_main_request_rxjava)
    public void rxClik(){
        Intent intent = new Intent(this, ActivityRx.class);
        startActivity(intent);
    }

//    public void login() {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("menu", "红烧肉");
//        map.put("dtype", "json");
//        map.put("pn", "0");
//        map.put("rn", "5");
//        map.put("albums", "");
//        map.put("key", "259b339f1d15119eb310d72228bccd67");
//
//        RxRequester.getInstance()
//                .requestMenu(map)
//                .subscribe(new ObserverWrapper<Menu>() {
//                    @Override
//                    protected void next(Menu cook) {
//                        Logger.d("success:" + cook.toString());
//                    }
//
//                    @Override
//                    protected void completed() {
//                        Logger.d("completed");
//                    }
//
//                    @Override
//                    protected void error(NetworkError networkError) {
//                        Logger.d(networkError.toString());
//                    }
//                });
//    }

}
