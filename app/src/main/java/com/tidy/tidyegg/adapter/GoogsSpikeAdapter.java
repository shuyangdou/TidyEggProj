package com.tidy.tidyegg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.app.AppConfig;
import com.tidy.tidyegg.base.BaseRecyclerAdapter;
import com.tidy.tidyegg.bean.GoogsSpike;
import com.tidy.tidyegg.utils.DensityUtils;
import com.tidy.tidyegg.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiang on 000011/10/11.
 * 商品秒杀列表适配器
 */
public class GoogsSpikeAdapter extends BaseRecyclerAdapter<GoogsSpike, GoogsSpikeAdapter.MyViewHolder> {
    private static int imageWidth;

    public GoogsSpikeAdapter(List<GoogsSpike> mDatas, Context mContext) {
        super(mDatas, mContext);
        imageWidth = (int) ((width - DensityUtils.dip2px(mContext, 10)) / 2.8);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_goodsspike, parent, false));
    }

    @Override
    protected void bindItemData(MyViewHolder viewHolder, GoogsSpike data, int position) {
        viewHolder.image.setBackgroundResource(data.getImage());
        viewHolder.goodsNum.setText(data.getGoodsNum());
        viewHolder.goodsName.setText(data.getGoodsName());
        viewHolder.deposit.setText("订金:" + mContext.getString(R.string.RMB) + data.getDeposit());
        viewHolder.price.setText(mContext.getString(R.string.RMB) + data.getPrice());
        if (data.getStatus() == 0) {
            viewHolder.countdown.setText("");
            viewHolder.status.setText(" 已结束 ");
        } else if (data.getStatus() == 1) {
            viewHolder.countdown.setText(Utils.countdown(data.getEndTime()) + "后结束");
            viewHolder.status.setText("正在夺蛋");
            viewHolder.countdown.setBackgroundResource(R.color.black);
            viewHolder.status.setBackgroundResource(R.color.red);
        } else if (data.getStatus() == 2) {
            viewHolder.countdown.setText(Utils.countdown(data.getStartTime()) + "后开始");
            viewHolder.status.setText("等待夺蛋");
            viewHolder.countdown.setBackgroundResource(R.color.gray);
            viewHolder.status.setBackgroundResource(R.color.gray);
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_spike_GoodsNum)
        TextView goodsNum;
        @BindView(R.id.item_iv_goodsImage)
        ImageView image;
        @BindView(R.id.tv_spike_GoodsName)
        TextView goodsName;
        @BindView(R.id.tv_spike_price)
        TextView price;
        @BindView(R.id.tv_spike_status)
        TextView status;
        @BindView(R.id.tv_spike_deposit)
        TextView deposit;
        @BindView(R.id.tv_spike_countdown)
        public TextView countdown;
        @BindView(R.id.item_rl_spikeInfo)
        RelativeLayout rl_spikeInfo;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            ViewGroup.LayoutParams params = image.getLayoutParams();
            params.width = imageWidth;
            params.height = imageWidth;
            image.setLayoutParams(params);

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl_spikeInfo.getLayoutParams();
            layoutParams.height = imageWidth;
            rl_spikeInfo.setLayoutParams(layoutParams);
            Log.i(AppConfig.TAG, imageWidth + "");
        }
    }

}
