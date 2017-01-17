package com.tidy.tidyegg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.COBaseAdapter;
import com.tidy.tidyegg.bean.MineOrderListBean;
import com.tidy.tidyegg.utils.DensityUtils;
import com.tidy.tidyegg.utils.SpUtils;

import java.util.List;

/**
 * Created by johnchow on 2016/10/17.
 */
public class OrderListGoodsAdapter extends COBaseAdapter<MineOrderListBean.Goods> {
    private static int imageWidth;
    private Context mContext;
    private List<MineOrderListBean.Goods> dataList;
    public OrderListGoodsAdapter(Context mContext,List<MineOrderListBean.Goods> dataList) {
        super(dataList);
        this.dataList = dataList;
        this.mContext = mContext;
        int width = SpUtils.getSharePreInt(mContext, "mobileInfo", "width");
        imageWidth = (width - DensityUtils.dip2px(mContext, 20)) / 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_detailgoods, parent, false);
            holder.image_detailgoods = (ImageView)convertView.findViewById(R.id.image_detailgoods);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_detailgoods_name);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_detailgoods_price);
            holder.tv_num = (TextView) convertView.findViewById(R.id.tv_detailgoods_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ViewGroup.LayoutParams params = holder.image_detailgoods.getLayoutParams();
        params.width = imageWidth;
        params.height = params.width;
        holder.image_detailgoods.setLayoutParams(params);

        MineOrderListBean.Goods goods = dataList.get(position);
        if (goods!=null){
            holder.tv_name.setText(goods.getName());
            holder.tv_price.setText(goods.getPrice());
            holder.tv_num.setText(goods.getNum());
            String picid = goods.getPicid();
            if (picid == null){
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
        return convertView;
    }

    class ViewHolder{
        ImageView image_detailgoods;
        TextView tv_name;
        TextView tv_price;
        TextView tv_num;
    }
}
