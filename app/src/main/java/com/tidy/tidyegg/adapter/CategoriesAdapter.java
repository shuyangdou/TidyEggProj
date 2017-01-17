package com.tidy.tidyegg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.app.AppConfig;
import com.tidy.tidyegg.base.BaseRecyclerAdapter;
import com.tidy.tidyegg.bean.Categories;
import com.tidy.tidyegg.utils.DensityUtils;
import com.tidy.tidyegg.utils.SpUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiang on 16/10/12.
 * 商品分类列表适配器
 */
public class CategoriesAdapter extends BaseRecyclerAdapter<Categories, CategoriesAdapter.MyViewHolder> {
    private static int imageWidth;

    public CategoriesAdapter(List<Categories> mDatas, Context mContext) {
        super(mDatas, mContext);
        int width = SpUtils.getSharePreInt(mContext, "mobileInfo", "width");
        imageWidth = (width - DensityUtils.dip2px(mContext, 20)) / 2;
    }

    @Override
    protected void bindItemData(MyViewHolder viewHolder, Categories data, int position) {
        viewHolder.goodsImage.setBackgroundResource(data.getImageID());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_categories, parent, false));
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_goodsImage)
        ImageView goodsImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            Log.i(AppConfig.TAG, imageWidth + "");
            ViewGroup.LayoutParams params = goodsImage.getLayoutParams();
            params.width = imageWidth;
            params.height = (imageWidth * 460) / 348;
            goodsImage.setLayoutParams(params);
        }
    }
}
