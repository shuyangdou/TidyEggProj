package com.tidy.tidyegg.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.activity.CategoriesListAty;
import com.tidy.tidyegg.adapter.CategoriesAdapter;
import com.tidy.tidyegg.base.BaseFragment;
import com.tidy.tidyegg.bean.Categories;
import com.tidy.tidyegg.callback.OnItemClickListener;
import com.tidy.tidyegg.widget.recyclerview.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by qiang on 2016/10/9.
 * 导航商品分类页面
 */
public class CategoriesFrag extends BaseFragment {
    @BindView(R.id.title_categories)
    RelativeLayout title;
    @BindView(R.id.recyclerView_categories)
    RecyclerView mRecyclerView;

    private List<Categories> list = new ArrayList<Categories>();

    @Override
    protected int getLayoutId() {
        return R.layout.frag_categories;
    }

    @Override
    protected void initData() {
        setTitle(title, "商品分类");

        initRecyclerView();
    }

    private void initRecyclerView() {
        initGoods();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(
                getResources().getDimensionPixelSize(R.dimen.space_3),
                getResources().getDimensionPixelSize(R.dimen.space_3),
                getResources().getDimensionPixelSize(R.dimen.space_3),
                getResources().getDimensionPixelSize(R.dimen.space_3)
        ));
        CategoriesAdapter adapter = new CategoriesAdapter(list, getActivity());
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("goodsName", list.get(position).getGoodsName());
                startActivity(CategoriesListAty.class, bundle);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    private void initGoods() {
        Categories entity = new Categories("仙居鸡", R.mipmap.p_xianjuji);
        list.add(entity);
        Categories entity2 = new Categories("星杂288", R.mipmap.p_xingza288);
        list.add(entity2);
        Categories entity3 = new Categories("浦东鸡", R.mipmap.p_pudongji);
        list.add(entity3);
        Categories entity4 = new Categories("北京油鸡", R.mipmap.p_beijingyouji);
        list.add(entity4);
        Categories entity5 = new Categories("狼山鸡", R.mipmap.p_langshanji);
        list.add(entity5);
        Categories entity6 = new Categories("龙凤鸡", R.mipmap.p_longfengji);
        list.add(entity6);
        Categories entity7 = new Categories("农大3号", R.mipmap.p_nongda3);
        list.add(entity7);
        Categories entity8 = new Categories("农昌2号", R.mipmap.p_nongchang2);
        list.add(entity8);
        Categories entity9 = new Categories("海兰褐", R.mipmap.p_hailanhe);
        list.add(entity9);
        Categories entity10 = new Categories("滨白42", R.mipmap.p_binbai);
        list.add(entity10);
    }

}
