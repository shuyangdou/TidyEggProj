package com.tidy.tidyegg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.bean.DetailGoodsBean;
import com.tidy.tidyegg.utils.DensityUtils;
import com.tidy.tidyegg.utils.SpUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dou on 2016/10/12.
 */
public class DetailgoodsAdapter extends RecyclerView.Adapter<DetailgoodsAdapter.MyViewHolder> {
    private static int imageWidth;
    private Context mContext;
    private List<DetailGoodsBean>dataList;
    public DetailgoodsAdapter(Context mContext, List<DetailGoodsBean>dataList){
        this.mContext = mContext;
        this.dataList = dataList;
        int width = SpUtils.getSharePreInt(mContext, "mobileInfo", "width");
        imageWidth = (width - DensityUtils.dip2px(mContext, 20)) / 3;
    }

    @Override
    public DetailgoodsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_detailgoods, parent, false));
    }

    @Override
    public void onBindViewHolder(final DetailgoodsAdapter.MyViewHolder holder, int position) {
        DetailGoodsBean goodsBean = dataList.get(position);
        if (goodsBean!=null){
            holder.tv_name.setText(goodsBean.getName());
            holder.tv_price.setText(goodsBean.getPrice());
            holder.tv_num.setText(goodsBean.getNum());
            String picId = goodsBean.getPicId();
            if (picId == null){
                holder.image_detailgoods.setImageResource(R.mipmap.app_icon);
            }else{
                if (goodsBean.getPicId().equals("xjbb")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xianju_bb);
                }else if(goodsBean.getPicId().equals("xjsn")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xianju_sn);
                }else if (goodsBean.getPicId().equals("xjyf")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xianju_yf);
                }else if (goodsBean.getPicId().equals("xjlr")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xianju_lr);
                }else if (goodsBean.getPicId().equals("xzbb")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xingza_bb);
                }else if (goodsBean.getPicId().equals("xzsn")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xingza_sn);
                }else if (goodsBean.getPicId().equals("xzyf")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xingza_yf);
                }else if (goodsBean.getPicId().equals("xzlr")){
                    holder.image_detailgoods.setImageResource(R.mipmap.xingza_lr);
                }else if (goodsBean.getPicId().equals("hlhbb")){
                    holder.image_detailgoods.setImageResource(R.mipmap.hailanhe_bb);
                }else if (goodsBean.getPicId().equals("hlhsn")){
                    holder.image_detailgoods.setImageResource(R.mipmap.hailanhe_sn);
                }else if (goodsBean.getPicId().equals("hlhlr")){
                    holder.image_detailgoods.setImageResource(R.mipmap.hailanhe_lr);
                }else if (goodsBean.getPicId().equals("hlhlr2")){
                    holder.image_detailgoods.setImageResource(R.mipmap.hailanhe_lr2);
                }else if (goodsBean.getPicId().equals("ncbb")){
                    holder.image_detailgoods.setImageResource(R.mipmap.nongchang2_bb);
                }else if (goodsBean.getPicId().equals("pdsn")){
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
        @BindView(R.id.image_detailgoods)
        ImageView image_detailgoods;
        @BindView(R.id.tv_detailgoods_name)
        TextView tv_name;
        @BindView(R.id.tv_detailgoods_price)
        TextView tv_price;
        @BindView(R.id.tv_detailgoods_num)
        TextView tv_num;

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
