package com.tidy.tidyegg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.bean.LogisticsBean;
import com.tidy.tidyegg.utils.DensityUtils;
import com.tidy.tidyegg.utils.SpUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DOU on 2016/10/14.
 * 物流里面的商品
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyViewHolder> {
    private static int imageWidth;
    private Context mContext;
    private List<LogisticsBean.Goods> dataList;
    public GoodsAdapter(Context mContext,List<LogisticsBean.Goods> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;
        int width = SpUtils.getSharePreInt(mContext, "mobileInfo", "width");
        imageWidth = (width - DensityUtils.dip2px(mContext, 20)) / 3;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_detailgoods, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LogisticsBean.Goods goods = dataList.get(position);
        if (goods!=null){
            holder.tv_name.setText(goods.getName());
            holder.tv_price.setText(goods.getPrice());
            holder.tv_num.setText(goods.getNum());

            String picId = goods.getPicid();
            if (picId == null){
                holder.image_detailgoods.setImageResource(R.mipmap.app_icon);
            }else{
                if (goods.getPicid().equals("xjbb")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xianju_bb);
                }else if(goods.getPicid().equals("xjsn")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xianju_sn);
                }else if (goods.getPicid().equals("xjyf")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xianju_yf);
                }else if (goods.getPicid().equals("xjlr")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xianju_lr);
                }else if (goods.getPicid().equals("xzbb")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xingza_bb);
                }else if (goods.getPicid().equals("xzsn")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xingza_sn);
                }else if (goods.getPicid().equals("xzyf")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xingza_yf);
                }else if (goods.getPicid().equals("xzlr")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xingza_lr);
                }else if (goods.getPicid().equals("hlhbb")){
                    holder.image_detailgoods.setImageResource(R.mipmap.hailanhe_bb);
                }else if (goods.getPicid().equals("hlhsn")){
                    holder.image_detailgoods.setImageResource(R.mipmap.hailanhe_sn);
                }else if (goods.getPicid().equals("hlhlr")){
                    holder.image_detailgoods.setImageResource(R.mipmap.hailanhe_lr);
                }else if (goods.getPicid().equals("hlhlr2")){
                    holder.image_detailgoods.setImageResource(R.mipmap.hailanhe_lr2);
                }else if (goods.getPicid().equals("ncbb")){
                    holder.image_detailgoods.setImageResource(R.mipmap.nongchang2_bb);
                }else if (goods.getPicid().equals("pdsn")){
                    holder.image_detailgoods.setImageResource(R.mipmap.pudong_sn);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_detailgoods_name)
        TextView tv_name;
        @BindView(R.id.tv_detailgoods_price)
        TextView tv_price;
        @BindView(R.id.tv_detailgoods_num)
        TextView tv_num;
        @BindView(R.id.image_detailgoods)
        ImageView image_detailgoods;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            ViewGroup.LayoutParams params = image_detailgoods.getLayoutParams();
            params.width = imageWidth;
            params.height = params.width;
            image_detailgoods.setLayoutParams(params);
        }
    }
}
