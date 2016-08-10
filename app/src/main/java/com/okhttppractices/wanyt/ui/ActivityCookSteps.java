package com.okhttppractices.wanyt.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.okhttppractices.wanyt.App;
import com.okhttppractices.wanyt.CookStepAdapter;
import com.okhttppractices.wanyt.R;
import com.okhttppractices.wanyt.network.responsemodel.Menu;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2016/8/8 14:34
 * <p/>
 * author wanyt
 * <p/>
 * Description:
 */
public class ActivityCookSteps extends AppCompatActivity {

    @BindView(R.id.rl_cook_steps)
    RecyclerView rlCookSteps;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_steps);
        ButterKnife.bind(this);

        rlCookSteps.setLayoutManager(new LinearLayoutManager(this));
        List<Menu.ResultBean.Cook.StepsBean> steps = App.getInstance().getSteps();
        CookStepAdapter adapter = new CookStepAdapter(this, steps);
        rlCookSteps.setAdapter(adapter);
    }

}
