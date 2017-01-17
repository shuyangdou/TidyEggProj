package com.tidy.tidyegg.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.BaseRecyclerAdapter;
import com.tidy.tidyegg.bean.CategoriesList;
import com.tidy.tidyegg.utils.DensityUtils;
import com.tidy.tidyegg.utils.SpUtils;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiang on 16/10/12.
 * 商品分类某一具体分类的列表适配器
 */
public class CategoriesListAdapter extends BaseRecyclerAdapter<CategoriesList, CategoriesListAdapter.MyViewHolder> {
    private DecimalFormat decimalFormat = new DecimalFormat("0.##");
    private static int imageWidth;

    public CategoriesListAdapter(List<CategoriesList> mDatas, Context mContext) {
        super(mDatas, mContext);
        int width = SpUtils.getSharePreInt(mContext, "mobileInfo", "width");
        imageWidth = (width - DensityUtils.dip2px(mContext, 14)) / 2;
    }

    @Override
    protected void bindItemData(MyViewHolder viewHolder, CategoriesList data, int position) {
        viewHolder.goodsSrc.setBackgroundResource(data.getImageID());
        viewHolder.goodsName.setText(data.getGoodsName());
        viewHolder.goodsPrice.setText(mContext.getString(R.string.RMB) + decimalFormat.format(data.getGoodsPrice()) + "箱");
        viewHolder.sales.setText("销量" + data.getSalesNum());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_categorieslist, parent, false));
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_iv_goodsSrc)
        RoundedImageView goodsSrc;
        @BindView(R.id.item_tv_goodsName)
        TextView goodsName;
        @BindView(R.id.item_tv_goodsPrice)
        TextView goodsPrice;
        @BindView(R.id.item_tv_sales)
        TextView sales;
        @BindView(R.id.item_çardView)
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setPreventCornerOverlap(false);

            ViewGroup.LayoutParams params = goodsSrc.getLayoutParams();
//            params.width = imageWidth;
            params.height = imageWidth;
            goodsSrc.setLayoutParams(params);
        }
    }

}
