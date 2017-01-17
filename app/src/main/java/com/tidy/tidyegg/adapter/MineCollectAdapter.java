package com.tidy.tidyegg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.COBaseAdapter;
import com.tidy.tidyegg.bean.MineCollectBean;
import com.tidy.tidyegg.utils.DensityUtils;
import com.tidy.tidyegg.utils.SpUtils;

import java.util.List;

/**
 * Created by dou on 2016/10/12.
 * 我的收藏item Adapter
 */
public class MineCollectAdapter extends COBaseAdapter<MineCollectBean>{
    private static int imageWidth;
    private Context mContext;
    private List<MineCollectBean> dataList;
    public MineCollectAdapter(List<MineCollectBean> dataList,Context mContext) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_minecollect, parent, false);
            holder.image_collectpic = (ImageView) convertView.findViewById(R.id.image_collectpic);
            holder.tv_collectname = (TextView) convertView.findViewById(R.id.tv_collectname);
            holder.tv_collectdeposit = (TextView) convertView.findViewById(R.id.tv_collectdeposit);
            holder.tv_collectpay = (TextView) convertView.findViewById(R.id.tv_collectpay);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ViewGroup.LayoutParams params = holder.image_collectpic.getLayoutParams();
        params.width = imageWidth;
        params.height = params.width;
        holder.image_collectpic.setLayoutParams(params);

        MineCollectBean mineCollectBean = dataList.get(position);
        if (mineCollectBean != null){
            holder.tv_collectname.setText(mineCollectBean.getName());
            holder.tv_collectdeposit.setText(mineCollectBean.getDeposit());
            holder.tv_collectpay.setText(mineCollectBean.getPay());
            String picid = mineCollectBean.getPicid();
            if (picid == null){
                holder.image_collectpic.setImageResource(R.mipmap.app_icon);
            }else{
                if (mineCollectBean.getPicid().equals("xjbb")){
                    holder.image_collectpic.setImageResource(R.mipmap.xianju_bb);
                }else if(mineCollectBean.getPicid().equals("xjsn")){
                    holder.image_collectpic.setImageResource(R.mipmap.xianju_sn);
                }else if (mineCollectBean.getPicid().equals("xjyf")){
                    holder.image_collectpic.setImageResource(R.mipmap.xianju_yf);
                }else if (mineCollectBean.getPicid().equals("xjlr")){
                    holder.image_collectpic.setImageResource(R.mipmap.xianju_lr);
                }else if (mineCollectBean.getPicid().equals("xzbb")){
                    holder.image_collectpic.setImageResource(R.mipmap.xingza_bb);
                }else if (mineCollectBean.getPicid().equals("xzsn")){
                    holder.image_collectpic.setImageResource(R.mipmap.xingza_sn);
                }else if (mineCollectBean.getPicid().equals("xzyf")){
                    holder.image_collectpic.setImageResource(R.mipmap.xingza_yf);
                }else if (mineCollectBean.getPicid().equals("xzlr")){
                    holder.image_collectpic.setImageResource(R.mipmap.xingza_lr);
                }else if (mineCollectBean.getPicid().equals("hlhbb")){
                    holder.image_collectpic.setImageResource(R.mipmap.hailanhe_bb);
                }else if (mineCollectBean.getPicid().equals("hlhsn")){
                    holder.image_collectpic.setImageResource(R.mipmap.hailanhe_sn);
                }else if (mineCollectBean.getPicid().equals("hlhlr")){
                    holder.image_collectpic.setImageResource(R.mipmap.hailanhe_lr);
                }else if (mineCollectBean.getPicid().equals("hlhlr2")){
                    holder.image_collectpic.setImageResource(R.mipmap.hailanhe_lr2);
                }else if (mineCollectBean.getPicid().equals("ncbb")){
                    holder.image_collectpic.setImageResource(R.mipmap.nongchang2_bb);
                }else if (mineCollectBean.getPicid().equals("pdsn")){
                    holder.image_collectpic.setImageResource(R.mipmap.pudong_sn);
                }
            }
        }
        return convertView;
    }

    static final class ViewHolder {
        ImageView image_collectpic;//图片
        TextView tv_collectname;//商品名称
        TextView tv_collectdeposit;//定金
        TextView tv_collectpay;//售价
    }
}
