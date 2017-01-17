package com.tidy.tidyegg.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.tidy.tidyegg.R;
import com.tidy.tidyegg.bean.TodayNewGoods;
import com.tidy.tidyegg.callback.OnItemClickListener;
import com.tidy.tidyegg.utils.DensityUtils;
import com.tidy.tidyegg.utils.SpUtils;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiang on 000009/10/9.
 * 首页“今日上新”列表适配器
 */
public class HomeTodayNewGoodsAdapter extends RecyclerView.Adapter<HomeTodayNewGoodsAdapter.MyViewHolder> {
    private Context mContext;
    private List<TodayNewGoods> list;

    private DecimalFormat decimalFormat = new DecimalFormat("0.##");

    private static int imageWidth;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public HomeTodayNewGoodsAdapter(Context mContext, List<TodayNewGoods> list) {
        this.mContext = mContext;
        this.list = list;
        int width = SpUtils.getSharePreInt(mContext, "mobileInfo", "width");
        imageWidth = (width - DensityUtils.dip2px(mContext, 20)) / 2;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_todaynewgoods, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        TodayNewGoods bean = list.get(position);
        if (bean != null) {
            holder.image.setBackgroundResource(bean.getImage());
            holder.goodsName.setText(bean.getGoodsName());
            holder.goodsPrice.setText(mContext.getString(R.string.RMB) + decimalFormat.format(bean.getGoodsPrice()) + "/箱");
            holder.saleNum.setText("销量:" + bean.getSalesNum() + "箱");
        }

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_goodsImage)
        RoundedImageView image;
        @BindView(R.id.tv_goodsName)
        TextView goodsName;
        @BindView(R.id.tv_saleNum)
        TextView saleNum;
        @BindView(R.id.tv_goodsPrice)
        TextView goodsPrice;
        @BindView(R.id.cardView)
        CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            cardView.setPreventCornerOverlap(false);

            ViewGroup.LayoutParams params = image.getLayoutParams();
//            params.width = imageWidth;
            params.height = imageWidth;
            image.setLayoutParams(params);
        }
    }

}
