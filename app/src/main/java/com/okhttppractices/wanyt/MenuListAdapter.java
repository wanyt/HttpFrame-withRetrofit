package com.okhttppractices.wanyt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.okhttppractices.wanyt.network.responsemodel.Menu;
import com.okhttppractices.wanyt.ui.ActivityCookSteps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2016/8/3 18:14
 * <p/>
 * author wanyt
 * <p/>
 * Description:
 */
public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Menu.ResultBean.Cook> list;
    private LayoutInflater from;

    public MenuListAdapter(Context context, ArrayList<Menu.ResultBean.Cook> list) {
        this.context = context;
        this.list = list;
        from = LayoutInflater.from(context);
    }

    public void setData(ArrayList<Menu.ResultBean.Cook> data) {
        this.list = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = from.inflate(R.layout.item_cook, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        inflate.setOnClickListener(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (list != null) {
            Menu.ResultBean.Cook cook = list.get(position);
            holder.setView(cook);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_item_pic)
        ImageView ivPic;
        @BindView(R.id.tv_item_cookname)
        TextView tvCookname;
        @BindView(R.id.tv_item_cooktags)
        TextView tvCooktag;
        @BindView(R.id.tv_item_cookburden)
        TextView tvCookburden;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setView(Menu.ResultBean.Cook cook) {
            tvCookname.setText(getAdapterPosition() + "，" + cook.title);
            tvCooktag.setText(cook.tags);
            tvCookburden.setText(cook.burden);
            List<String> albums = cook.albums;
            if (albums != null && albums.size() > 0) {
                Glide.with(context)
                        .load(albums.get(0))
                        .into(ivPic);
            }
        }

        @Override
        public void onClick(View view) {
            if (list != null) {
                Menu.ResultBean.Cook cook = list.get(getAdapterPosition());
                List<Menu.ResultBean.Cook.StepsBean> steps = cook.steps;
                App.getInstance().setSteps(steps);

                Intent intent = new Intent(context, ActivityCookSteps.class);
                context.startActivity(intent);
            }
        }
    }
}
