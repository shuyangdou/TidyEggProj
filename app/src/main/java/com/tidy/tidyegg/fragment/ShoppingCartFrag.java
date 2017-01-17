package com.tidy.tidyegg.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.tidy.tidyegg.R;
import com.tidy.tidyegg.activity.SubmitOrderAty;
import com.tidy.tidyegg.adapter.ShoppingCartAdapter;
import com.tidy.tidyegg.app.AppConfig;
import com.tidy.tidyegg.base.BaseApplication;
import com.tidy.tidyegg.base.BaseFragment;
import com.tidy.tidyegg.bean.ShoppingCartGoods;
import com.tidy.tidyegg.callback.OnClickListener;
import com.tidy.tidyegg.callback.OnItemViewClickListener;
import com.tidy.tidyegg.event.ShoppingGoodsEvent;
import com.tidy.tidyegg.utils.DensityUtils;
import com.tidy.tidyegg.utils.Utils;
import com.tidy.tidyegg.utils.ValueUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by qiang on 2016/10/9.
 * 导航购物车页面
 */
public class ShoppingCartFrag extends BaseFragment implements OnItemViewClickListener {
    @BindView(R.id.title_shoppingCat)
    RelativeLayout title;
    @BindView(R.id.swipeMenuListView)
    SwipeMenuListView mListView;
    @BindView(R.id.tv_total)
    TextView tv_total;
    @BindView(R.id.btn_account)
    Button btn_account;
    @BindView(R.id.rl_account)
    RelativeLayout layout_account;
    @BindView(R.id.appTitle_title)
    TextView appTitle;
    @BindView(R.id.appTitle_rightText)
    TextView appTitleRight;
    @BindView(R.id.tv_emptyTips)
    TextView tvEmptyTips;

    private BaseApplication baseApplication;

    private ArrayList<ShoppingCartGoods> list = new ArrayList<ShoppingCartGoods>();
    private ShoppingCartAdapter adapter;

    private boolean isEdit = false;// 是否编辑购物车商品，true为正在编辑

    private double totalPrice = 0.00;// 购物车商品总价

    @Override
    protected int getLayoutId() {
        return R.layout.frag_shoppingcart;
    }

    @Override
    protected void initData() {
        baseApplication = (BaseApplication) getActivity().getApplication();
        EventBus.getDefault().register(this);

        appTitle.setTextColor(getResources().getColor(R.color.red));
        setTitle(title, "购物车");
        setTitleRight(title, "编辑", new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (appTitleRight.getText().equals("编辑")) {// 正在编辑
                    appTitleRight.setText("完成");
                    adapter.setEdit(true);
                    Utils.inVisible(btn_account);
                } else if (appTitleRight.getText().equals("完成")) {// 完成编辑
                    checkList(list);
                    appTitleRight.setText("编辑");
                    adapter.setEdit(false);
                    Utils.show(btn_account);
                }
            }
        });

        initListView();
    }

    private void initListView() {
        Log.w(AppConfig.TAG, "initListView");

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(DensityUtils.dp2px(getActivity(), 90));
                // set a icon
                deleteItem.setIcon(R.mipmap.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                if (index == 0) {
                    list.remove(position);
                    adapter.setDataList(list);
                    adapter.notifyDataSetChanged();
                    checkList(list);
                }
                return false;
            }
        });

        adapter = new ShoppingCartAdapter(getActivity(), list, decimalFormat, isEdit, ShoppingCartFrag.this);
        mListView.setAdapter(adapter);
        checkList(list);
    }

    private void initGoods() {
        ShoppingCartGoods entity = new ShoppingCartGoods("000011", R.color.colorTidy, "蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋", 500.02, 1, false);
        list.add(entity);
        ShoppingCartGoods entity2 = new ShoppingCartGoods("000012", R.color.colorTidy, "蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋", 400.00, 2, false);
        list.add(entity2);
        ShoppingCartGoods entity3 = new ShoppingCartGoods("000014", R.color.colorTidy, "蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋", 600.909, 5, false);
        list.add(entity3);
        ShoppingCartGoods entity4 = new ShoppingCartGoods("000013", R.color.colorTidy, "蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋", 700.78, 1, false);
        list.add(entity4);
    }

    /**
     * 检查list是否为空
     *
     * @param list 当前购物车内的商品
     */
    private void checkList(List<ShoppingCartGoods> list) {
        if (ValueUtils.isListEmpty(list)) {
            setTitle(title, "购物车");
            Utils.gone(appTitleRight, layout_account);
            Utils.show(tvEmptyTips);
        } else {
            totalPrice = 0.00;
            for (ShoppingCartGoods entity : list) {
                totalPrice += (entity.getGoodsPrice() * entity.getGoodsNum());
            }
            tv_total.setText(Html.fromHtml("总计：" + "<font color='red'><big>" + getString(R.string.RMB) + decimalFormat.format(totalPrice) + "</big></font>"));
            setTitle(title, "购物车(" + list.size() + ")");
            btn_account.setText("结算(" + list.size() + ")");
            Utils.show(appTitleRight, layout_account);
            Utils.gone(tvEmptyTips);
        }
    }

    @Override
    public void onClick(View item, View widget, int position, int which) {
        ShoppingCartGoods entity = list.get(position);
        switch (which) {
            // 减少
            case R.id.item_tv_subtract:
                if (entity.getGoodsNum() == 1) {
                    toast("受不了了，宝贝不能再减少了哦");
                } else {
                    entity.setGoodsNum(entity.getGoodsNum() - 1);
                }
                break;

            // 增加
            case R.id.item_tv_add:
                entity.setGoodsNum(entity.getGoodsNum() + 1);
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_account)
    public void onClick() {
        Bundle bundle = new Bundle();
        bundle.putDouble("totalPrice", totalPrice);
        bundle.putParcelableArrayList("goodsList", list);
        startActivity(SubmitOrderAty.class, bundle);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(ShoppingGoodsEvent event) {
        if (event.isAdd) {
            if (ValueUtils.isListNotEmpty(baseApplication.getGoodsList())) {
                List<ShoppingCartGoods> localList = baseApplication.getGoodsList();// 本地存储在Application的购物车商品，但尚未加入购物车的商品
                for (int i = 0; i < localList.size(); i++) {
                    ShoppingCartGoods entity = localList.get(i);
                    if (list.contains(entity)) {
                        int position = list.indexOf(entity);
                        int num = list.get(position).getGoodsNum();
                        list.get(position).setGoodsNum(num + 1);
                    } else {
                        list.add(entity);
                    }
                }
                baseApplication.clearGoodsList();
                if (adapter != null) {
                    adapter.notifyDataSetChanged(list);
                    checkList(list);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
