package com.tidy.tidyegg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.bean.LogisticsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dou on 2016/10/14.
 * 物流信息
 */
public class LogisticsMessageAdapter extends RecyclerView.Adapter<LogisticsMessageAdapter.MyViewHolder>{
    private Context mContext;
    private List<LogisticsBean.LogisticsMessage> dataList;
    public LogisticsMessageAdapter(Context mContext, List<LogisticsBean.LogisticsMessage> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_logisticsmess, parent, false));
    }

    @Override
    public void onBindViewHolder(LogisticsMessageAdapter.MyViewHolder holder, int position) {
        if (position == 0){
            holder.view_up.setVisibility(View.GONE);
            holder.view_down.setVisibility(View.VISIBLE);
            holder.item_location.setTextColor(0xffababab);
            holder.item_time.setTextColor(0xffababab);
        }else if (position == dataList.size()-1){
            holder.view_up.setVisibility(View.VISIBLE);
            holder.view_down.setVisibility(View.GONE);
            holder.item_location.setTextColor(0xff298409);
            holder.item_time.setTextColor(0xff298409);
            holder.tv_point.setBackgroundResource(R.drawable.shape_logistics_cricle);
        }else{
            holder.view_up.setVisibility(View.VISIBLE);
            holder.view_down.setVisibility(View.VISIBLE);
            holder.item_location.setTextColor(0xffababab);
            holder.item_time.setTextColor(0xffababab);
        }

        LogisticsBean.LogisticsMessage message = dataList.get(position);
        if (message != null){
            holder.item_location.setText(message.getLocation());
            holder.item_time.setText(message.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_point)
        TextView tv_point;
        @BindView(R.id.view_up)
        View view_up;
        @BindView(R.id.view_down)
        View view_down;
        @BindView(R.id.item_location)
        TextView item_location;
        @BindView(R.id.item_time)
        TextView item_time;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
