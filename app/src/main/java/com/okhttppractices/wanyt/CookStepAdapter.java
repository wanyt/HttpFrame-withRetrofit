package com.okhttppractices.wanyt;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.okhttppractices.wanyt.network.responsemodel.Menu;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2016/8/8 14:51
 * <p/>
 * author wanyt
 * <p/>
 * Description:
 */
public class CookStepAdapter extends RecyclerView.Adapter<CookStepAdapter.ViewHolder> {

    private final ActivityCookSteps activityCookSteps;
    private final List<Menu.ResultBean.Cook.StepsBean> steps;
    private final LayoutInflater from;

    public CookStepAdapter(ActivityCookSteps activityCookSteps, List<Menu.ResultBean.Cook.StepsBean> steps) {
        this.activityCookSteps = activityCookSteps;
        from = LayoutInflater.from(activityCookSteps);
        this.steps = steps;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = from.inflate(R.layout.item_cook_steps, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(steps != null){
            holder.setView(steps.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return steps == null ? 0 : steps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_cook_step)
        ImageView ivStep;
        @BindView(R.id.tv_cook_step)
        TextView tvStep;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setView(Menu.ResultBean.Cook.StepsBean stepsBean) {
            Glide.with(activityCookSteps)
                    .load(stepsBean.img)
                    .into(ivStep);
            tvStep.setText(stepsBean.step);
        }
    }
}
