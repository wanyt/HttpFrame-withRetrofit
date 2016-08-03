package com.okhttppractices.wanyt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2016/8/3 18:14
 * <p>
 * author wanyt
 * <p>
 * Description:
 */
public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {

    private Context context;
    private ArrayList list;
    private LayoutInflater from;

    public MenuListAdapter(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
        from = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = from.inflate(R.layout.item_cook, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(list != null){
            Object o = list.get(position);

        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

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
    }
}
