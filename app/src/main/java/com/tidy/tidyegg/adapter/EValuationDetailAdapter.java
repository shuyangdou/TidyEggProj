package com.tidy.tidyegg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.BaseRecyclerAdapter;
import com.tidy.tidyegg.bean.ValuationDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiang on 16/10/18.
 * 各类型鸡蛋的评价详情列表适配器
 */
public class EValuationDetailAdapter extends BaseRecyclerAdapter<ValuationDetail, EValuationDetailAdapter.MyViewHolder> {

    public EValuationDetailAdapter(List<ValuationDetail> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    protected void bindItemData(MyViewHolder viewHolder, ValuationDetail data, int position) {
        viewHolder.itemTvUser.setText(data.getUser());
        viewHolder.itemTvTime.setText(data.getTime());
        viewHolder.itemTvContent.setText(data.getContent());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_evluation_detail, parent, false));
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_tv_user)
        TextView itemTvUser;
        @BindView(R.id.item_tv_time)
        TextView itemTvTime;
        @BindView(R.id.item_tv_content)
        TextView itemTvContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
