package com.tidy.tidyegg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.bean.DetailOrderListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dou on 2016/10/12.
 */
public class DetailorderlistAdapter extends RecyclerView.Adapter<DetailorderlistAdapter.MyViewHolder> {

    private Context mContext;
    private List<DetailOrderListBean>dataList;
    public DetailorderlistAdapter(Context mContext, List<DetailOrderListBean>dataList){
        this.dataList = dataList;
        this.mContext = mContext;
    }
    @Override
    public DetailorderlistAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_detailorderlist, parent, false));
    }

    @Override
    public void onBindViewHolder(DetailorderlistAdapter.MyViewHolder holder, int position) {
        DetailOrderListBean orderListBean = dataList.get(position);
        if (orderListBean!=null){
            holder.tv_mess.setText(orderListBean.getName());
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_orderlist_mess)
        TextView tv_mess;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
