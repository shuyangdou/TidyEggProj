package com.tidy.tidyegg.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.adapter.CategoriesListAdapter;
import com.tidy.tidyegg.app.AppConfig;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.bean.CategoriesList;
import com.tidy.tidyegg.callback.OnItemClickListener;
import com.tidy.tidyegg.widget.RadioButtonDrawableRight;
import com.tidy.tidyegg.widget.recyclerview.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by qiang on 16/10/12.
 * 商品分类某一具体分类的列表页面
 */
public class CategoriesListAty extends BaseActivity {
    @BindView(R.id.title_categoriesList)
    RelativeLayout title;
    @BindView(R.id.rb_integrateSort)
    RadioButton rb_integrateSort;
    @BindView(R.id.rb_salesSort)
    RadioButton rb_salesSort;
    @BindView(R.id.rb_priceSort)
    RadioButtonDrawableRight rb_priceSort;
    @BindView(R.id.recyclerView_categoriesList)
    RecyclerView mRecyclerView;
    @BindView(R.id.rg_categoriesList)
    RadioGroup radioGroup;

    private List<CategoriesList> list = new ArrayList<CategoriesList>();// 默认为综合排序的商品列表，可进行排序发生数据位置变化
    private List<CategoriesList> normalList = new ArrayList<CategoriesList>();// 默认的综合排序商品列表
    private CategoriesListAdapter adapter;

    private String type;// 鸡类

    /**
     * 价格排序的flag，flag为0表示未进行排序，flag为1表示价格从低到高升序，flag为2表示价格从高到低降序
     */
    private int priceSortFlag = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_categorieslist;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLeft(title);
        if (getBundle() != null) {
            type = getBundle().getString("goodsName");
            setTitle(title, type);
        }

        rb_integrateSort.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = radioGroup.getCheckedRadioButtonId();
                switch (id) {
                    // 综合排序
                    case R.id.rb_integrateSort:
                        priceSortFlag = 0;
                        changeDrawableRight();
                        adapter.setDataList(normalList);
                        break;

                    // 销量优先
                    case R.id.rb_salesSort:
                        priceSortFlag = 0;
                        changeDrawableRight();
                        Collections.sort(list, new Comparator<CategoriesList>() {
                            @Override
                            public int compare(CategoriesList arg0, CategoriesList arg1) {
                                return arg0.getSalesNum() > arg1.getSalesNum() ? -1 : (arg0.getSalesNum() == arg1.getSalesNum() ? 0 : 1);
                            }
                        });
                        adapter.setDataList(list);
                        break;

                    // 价格排序
                    case R.id.rb_priceSort:
                        Collections.sort(list, new Comparator<CategoriesList>() {
                            @Override
                            public int compare(CategoriesList arg0, CategoriesList arg1) {
                                return arg0.getGoodsPrice() < arg1.getGoodsPrice() ? -1 : (arg0.getGoodsPrice() == arg1.getGoodsPrice() ? 0 : 1);
                            }
                        });
                        adapter.setDataList(list);
                        break;
                }
            }
        });

        initRecyclerView();
    }

    @OnClick({R.id.rb_integrateSort, R.id.rb_salesSort, R.id.rb_priceSort})
    public void onClick(View view) {
        switch (view.getId()) {
            // 综合排序
            case R.id.rb_integrateSort:
                break;

            // 销量优先
            case R.id.rb_salesSort:
                break;

            // 价格排序
            case R.id.rb_priceSort:
                Log.i(AppConfig.TAG, "hhh");
                if (priceSortFlag == 0) {
                    priceSortFlag = 1;
                } else if (priceSortFlag == 1) {
                    priceSortFlag = 2;
                    Collections.sort(list, new Comparator<CategoriesList>() {
                        @Override
                        public int compare(CategoriesList arg0, CategoriesList arg1) {
                            return arg1.getGoodsPrice() < arg0.getGoodsPrice() ? -1 : (arg0.getGoodsPrice() == arg1.getGoodsPrice() ? 0 : 1);
                        }
                    });
                } else if (priceSortFlag == 2) {
                    priceSortFlag = 1;
                    Collections.sort(list, new Comparator<CategoriesList>() {
                        @Override
                        public int compare(CategoriesList arg0, CategoriesList arg1) {
                            return arg0.getGoodsPrice() < arg1.getGoodsPrice() ? -1 : (arg0.getGoodsPrice() == arg1.getGoodsPrice() ? 0 : 1);
                        }
                    });
                }
                changeDrawableRight();
                adapter.setDataList(list);
                break;
        }
    }

    /**
     * 改变价格排序RadioButton的DrawableRight
     */
    private void changeDrawableRight() {
        if (priceSortFlag == 0)
            setDrawableRight(rb_priceSort, R.mipmap.ic_paixu_1);
        else if (priceSortFlag == 1)
            setDrawableRight(rb_priceSort, R.mipmap.ic_paixu_2);
        else if (priceSortFlag == 2)
            setDrawableRight(rb_priceSort, R.mipmap.ic_paixu_3);
    }

    /**
     * 设置RadioButton的DrawableRight
     *
     * @param drawableRightID 资源ID
     */
    private void setDrawableRight(RadioButton rb, int drawableRightID) {
        Drawable drawable = getResources().getDrawable(drawableRightID);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        rb.setCompoundDrawables(null, null, drawable, null);
    }

    private void initRecyclerView() {
        initGoodsList();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        // 设置间隔
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(
                getResources().getDimensionPixelSize(R.dimen.space_3),
                getResources().getDimensionPixelSize(R.dimen.space_3),
                getResources().getDimensionPixelSize(R.dimen.space_3),
                getResources().getDimensionPixelSize(R.dimen.space_3)
        ));
        adapter = new CategoriesListAdapter(list, this);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("CategoriesList", list.get(position));
                startActivity(EggDetailAty.class, bundle);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    private void initGoodsList() {
        if (type.equals("仙居鸡")) {
            CategoriesList entity = new CategoriesList(R.mipmap.xianju_lr, "100003", "仙居鸡AA级泰笛老人蛋", 180, "18/箱", 4, 704);
            list.add(entity);
            CategoriesList entity2 = new CategoriesList(R.mipmap.xianju_yf, "100023", "仙居鸡AAA级泰笛孕妇蛋", 230, "23/箱", 2, 737);
            list.add(entity2);
            CategoriesList entity3 = new CategoriesList(R.mipmap.xianju_bb, "100038", "仙居鸡AAA+级泰笛宝宝蛋", 257, "25.7/箱", 1, 673);
            list.add(entity3);
            CategoriesList entity4 = new CategoriesList(R.mipmap.xianju_sn, "100025", "仙居鸡AA级泰笛青少年蛋", 220, "22/箱", 3, 657);
            list.add(entity4);
            normalList = list;
            return;
        } else if (type.equals("星杂288")) {
            CategoriesList entity = new CategoriesList(R.mipmap.xingza_bb, "100004", "星杂288AAA+级泰笛宝宝蛋", 273, "27.3/箱", 1, 665);
            list.add(entity);
            CategoriesList entity2 = new CategoriesList(R.mipmap.xingza_yf, "100014", "星杂289AAA级泰笛孕妇蛋", 165, "16.5/箱", 2, 687);
            list.add(entity2);
            CategoriesList entity3 = new CategoriesList(R.mipmap.xingza_sn, "100020", "星杂290AAA+级泰笛青少年蛋", 246, "24.6/箱", 3, 753);
            list.add(entity3);
            CategoriesList entity4 = new CategoriesList(R.mipmap.xingza_lr, "100033", "星杂291AAA+级泰笛老人蛋", 256, "25.6/箱", 4, 748);
            list.add(entity4);
            normalList = list;
            return;
        } else if (type.equals("浦东")) {
            CategoriesList entity = new CategoriesList(R.mipmap.pudong_lr, "100003", type + "AA级泰笛老人蛋", 180, "18/箱", 4, 704);
            list.add(entity);
            CategoriesList entity2 = new CategoriesList(R.mipmap.pudong_yf, "100023", type + "AAA级泰笛孕妇蛋", 230, "23/箱", 2, 737);
            list.add(entity2);
            CategoriesList entity3 = new CategoriesList(R.mipmap.pudong_bb, "100038", type + "AAA+级泰笛宝宝蛋", 257, "25.7/箱", 1, 673);
            list.add(entity3);
            CategoriesList entity4 = new CategoriesList(R.mipmap.pudong_sn, "100025", type + "AA级泰笛青少年蛋", 220, "22/箱", 3, 657);
            list.add(entity4);
            normalList = list;
            return;
        } else if (type.equals("北京油鸡")) {
            CategoriesList entity = new CategoriesList(R.mipmap.beijing_lr, "100003", type + "AA级泰笛老人蛋", 180, "18/箱", 4, 704);
            list.add(entity);
            CategoriesList entity2 = new CategoriesList(R.mipmap.beijing_yf, "100023", type + "AAA级泰笛孕妇蛋", 230, "23/箱", 2, 737);
            list.add(entity2);
            CategoriesList entity3 = new CategoriesList(R.mipmap.beijing_bb, "100038", type + "AAA+级泰笛宝宝蛋", 257, "25.7/箱", 1, 673);
            list.add(entity3);
            CategoriesList entity4 = new CategoriesList(R.mipmap.beijing_sn, "100025", type + "AA级泰笛青少年蛋", 220, "22/箱", 3, 657);
            list.add(entity4);
            normalList = list;
            return;
        } else if (type.equals("狼山鸡")) {
            CategoriesList entity = new CategoriesList(R.mipmap.langshan_lr, "100003", type + "AA级泰笛老人蛋", 180, "18/箱", 4, 704);
            list.add(entity);
            CategoriesList entity2 = new CategoriesList(R.mipmap.langshan_yf, "100023", type + "AAA级泰笛孕妇蛋", 230, "23/箱", 2, 737);
            list.add(entity2);
            CategoriesList entity3 = new CategoriesList(R.mipmap.langshan_bb, "100038", type + "AAA+级泰笛宝宝蛋", 257, "25.7/箱", 1, 673);
            list.add(entity3);
            CategoriesList entity4 = new CategoriesList(R.mipmap.langshan_sn, "100025", type + "AA级泰笛青少年蛋", 220, "22/箱", 3, 657);
            list.add(entity4);
            normalList = list;
            return;
        } else if (type.equals("龙凤鸡")) {
            CategoriesList entity = new CategoriesList(R.mipmap.longf_lr, "100003", type + "AA级泰笛老人蛋", 180, "18/箱", 4, 704);
            list.add(entity);
            CategoriesList entity2 = new CategoriesList(R.mipmap.longf_yf, "100023", type + "AAA级泰笛孕妇蛋", 230, "23/箱", 2, 737);
            list.add(entity2);
            CategoriesList entity3 = new CategoriesList(R.mipmap.longf_bb, "100038", type + "AAA+级泰笛宝宝蛋", 257, "25.7/箱", 1, 673);
            list.add(entity3);
            CategoriesList entity4 = new CategoriesList(R.mipmap.longf_sn, "100025", type + "AA级泰笛青少年蛋", 220, "22/箱", 3, 657);
            list.add(entity4);
            normalList = list;
            return;
        }
        CategoriesList entity = new CategoriesList(R.mipmap.pudong_lr, "100003", type + "AA级泰笛老人蛋", 180, "18/箱", 4, 704);
        list.add(entity);
        CategoriesList entity2 = new CategoriesList(R.mipmap.pudong_yf, "100023", type + "AAA级泰笛孕妇蛋", 230, "23/箱", 2, 737);
        list.add(entity2);
        CategoriesList entity3 = new CategoriesList(R.mipmap.pudong_bb, "100038", type + "AAA+级泰笛宝宝蛋", 257, "25.7/箱", 1, 673);
        list.add(entity3);
        CategoriesList entity4 = new CategoriesList(R.mipmap.pudong_sn, "100025", type + "AA级泰笛青少年蛋", 220, "22/箱", 3, 657);
        list.add(entity4);
        normalList = list;
    }
}
