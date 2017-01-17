package com.tidy.tidyegg.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.tidy.tidyegg.R;
import com.tidy.tidyegg.adapter.MineCollectAdapter;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.bean.MineCollectBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dou on 2016/10/12.
 * 我的收藏
 */
public class MineCollectionAty extends BaseActivity {

    @BindView(R.id.head_minecollection)
    RelativeLayout head_minecollection;
    @BindView(R.id.lv_minecollection)
    SwipeMenuListView lv_minecollection;
    @BindView(R.id.tv_minecollection_empty)
    TextView tv_minecollection_empty;
    private MineCollectAdapter collectAdapter;
    private List<MineCollectBean> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_minecollection;
    }

    public void showView() {
        this.setTitle(head_minecollection, "我的收藏");
        this.setLeft(head_minecollection);
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(0xffffd600));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(16);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
            }
        };
        lv_minecollection.setMenuCreator(creator);

        lv_minecollection.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        dataList.remove(position);
                        collectAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
        initData();
    }

    public void initData() {
        dataList = new ArrayList<MineCollectBean>();
        MineCollectBean bean1 = new MineCollectBean();
        bean1.setName("狼山鸡AAA+级泰笛孕妇蛋");
        bean1.setDeposit(mContext.getString(R.string.RMB) + "60/箱");
        bean1.setPay(mContext.getString(R.string.RMB) + "1200/箱");
        bean1.setPicid("xzyf");
        MineCollectBean bean2 = new MineCollectBean();
        bean2.setName("仙居鸡AAA级泰笛老人蛋");
        bean2.setDeposit(mContext.getString(R.string.RMB) + "44/箱");
        bean2.setPay(mContext.getString(R.string.RMB) + "880/箱");
        bean2.setPicid("xjlr");
        MineCollectBean bean3 = new MineCollectBean();
        bean3.setName("浦东鸡AA级泰笛老人蛋");
        bean3.setDeposit(mContext.getString(R.string.RMB) + "14/箱");
        bean3.setPay(mContext.getString(R.string.RMB) + "280/箱");
        bean3.setPicid("pdsn");
        dataList.add(bean1);
        dataList.add(bean2);
        dataList.add(bean3);
        collectAdapter = new MineCollectAdapter(dataList, mContext);
        lv_minecollection.setAdapter(collectAdapter);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
