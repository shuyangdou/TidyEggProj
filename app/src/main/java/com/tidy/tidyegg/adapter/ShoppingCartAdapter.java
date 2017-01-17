package com.tidy.tidyegg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.COBaseAdapter;
import com.tidy.tidyegg.bean.ShoppingCartGoods;
import com.tidy.tidyegg.callback.OnItemViewClickListener;
import com.tidy.tidyegg.utils.DensityUtils;
import com.tidy.tidyegg.utils.SpUtils;
import com.tidy.tidyegg.utils.Utils;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiang on 16/10/13.
 * 购物车列表适配器
 */
public class ShoppingCartAdapter extends COBaseAdapter<ShoppingCartGoods> {
    private Context mContext;
    /**
     * 保留double类型输出时保留两位小数
     */
    private DecimalFormat decimalFormat;
    /**
     * 是否编辑购物车商品，true为正在编辑
     */
    private boolean isEdit = false;
    private OnItemViewClickListener listener;

    private static int imageWidth;

    public void setEdit(boolean edit) {
        isEdit = edit;
        notifyDataSetChanged();
    }

    public ShoppingCartAdapter(Context mContext, List<ShoppingCartGoods> dataList, DecimalFormat decimalFormat, boolean isEdit, OnItemViewClickListener listener) {
        super(dataList);
        this.mContext = mContext;
        this.decimalFormat = decimalFormat;
        this.isEdit = isEdit;
        this.listener = listener;
        int width = SpUtils.getSharePreInt(mContext, "mobileInfo", "width");
        imageWidth = (width - DensityUtils.dip2px(mContext, 15)) / 3;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_shoppingcart, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ShoppingCartGoods data = getData(position);
        if (data != null) {
            if (!isEdit) {
                Utils.show(holder.goodsLayout);
                Utils.gone(holder.editLayout);
            } else {
                if (!data.isSpike())
                    Utils.show(holder.editLayout);
                else
                    Utils.gone(holder.editLayout);

                Utils.gone(holder.goodsLayout);
            }

            holder.image.setBackgroundResource(data.getImageID());
            holder.goodsName.setText(data.getGoodsName());

            if (!data.isSpike())
                holder.unitPrice.setText(decimalFormat.format(data.getGoodsPrice()));
            else
                holder.unitPrice.setText(decimalFormat.format(data.getGoodsPrice()) + "（秒杀价）");

            holder.num.setText("x" + data.getGoodsNum());
            holder.goodsCount.setText(data.getGoodsNum() + "");

            final View view = convertView;
            final int p = position;
            final int subtract = holder.subtract.getId();
            holder.subtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onClick(view, parent, p, subtract);
                }
            });

            final int add = holder.add.getId();
            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onClick(view, parent, p, add);
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_iv_shoppingCart)
        ImageView image;
        @BindView(R.id.item_tv_name)
        TextView goodsName;
        @BindView(R.id.item_tv_unitPrice)
        TextView unitPrice;
        @BindView(R.id.item_tv_num)
        TextView num;
        @BindView(R.id.item_rl_goodsLayout)
        RelativeLayout goodsLayout;
        @BindView(R.id.item_tv_subtract)
        TextView subtract;
        @BindView(R.id.item_tv_goodsCount)
        TextView goodsCount;
        @BindView(R.id.item_tv_add)
        TextView add;
        @BindView(R.id.item_rl_editLayout)
        LinearLayout editLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);

            ViewGroup.LayoutParams params = image.getLayoutParams();
            params.width = imageWidth;
            params.height = imageWidth;
            image.setLayoutParams(params);
        }
    }

}
