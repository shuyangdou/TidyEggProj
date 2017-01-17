package com.tidy.tidyegg.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.adapter.GoogsSpikeAdapter;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.base.BaseApplication;
import com.tidy.tidyegg.bean.GoogsSpike;
import com.tidy.tidyegg.bean.ShoppingCartGoods;
import com.tidy.tidyegg.callback.OnItemClickListener;
import com.tidy.tidyegg.event.ShoppingGoodsEvent;
import com.tidy.tidyegg.utils.Utils;
import com.tidy.tidyegg.widget.recyclerview.ListItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by qiang on 000011/10/11.
 * 商品秒杀界面
 */
public class GoogsSpikeAty extends BaseActivity implements OnItemClickListener {
    @BindView(R.id.title_goodsSpike)
    RelativeLayout title;
    @BindView(R.id.recyclerView_goodsSpike)
    RecyclerView mRecyclerView;

    private LinearLayoutManager manager;

    private List<GoogsSpike> list = new ArrayList<GoogsSpike>();
    private GoogsSpikeAdapter adapter;

    private CountDownTimer timer;// 倒计时，每隔一秒刷新adapter

    @Override
    protected int getLayoutId() {
        return R.layout.aty_googs_spike;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLeft(title);
        setTitle(title, "心跳夺蛋");

        initData();
    }

    private void initData() {
        GoogsSpike bean = new GoogsSpike(R.mipmap.xingza_bb, "100箱", "星杂288AAA+级泰笛宝宝蛋", "25.3/箱", 253, 1476524582, 1477238400, 1, "200004", 1, 665);
        list.add(bean);
        GoogsSpike bean2 = new GoogsSpike(R.mipmap.xingza_lr, "100箱", "星杂291AAA+级泰笛老人蛋", "20/箱", 200, 1476524582, 1477357170, 1, "200033", 4, 748);
        list.add(bean2);
        GoogsSpike bean3 = new GoogsSpike(R.mipmap.xianju_lr, "100箱", "仙居鸡AA级泰笛老人蛋", "15/箱", 150, 1476524582, 1477537871, 1, "200003", 4, 704);
        list.add(bean3);
        GoogsSpike bean4 = new GoogsSpike(R.mipmap.xingza_yf, "100箱", "星杂289AAA级泰笛孕妇蛋", "14.5/箱", 145, 1477624271, 1478833871, 2, "200014", 2, 687);
        list.add(bean4);

        manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new ListItemDecoration(this, ListItemDecoration.VERTICAL_LIST));
        adapter = new GoogsSpikeAdapter(list, this);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        startCountDownTime(bean4.getEndTime());
    }

    private void startCountDownTime(long l) {
        timer = new CountDownTimer(l * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int firstVisible = manager.findFirstVisibleItemPosition();
                int lastVisible = manager.findLastVisibleItemPosition();
                for (int i = 0; i < list.size(); i++) {
                    list.set(i, list.get(i));
                    if (i >= firstVisible && i <= lastVisible) {
                        final GoogsSpikeAdapter.MyViewHolder holder = (GoogsSpikeAdapter.MyViewHolder) mRecyclerView.findViewHolderForPosition(i);
                        final long startTime = list.get(i).getStartTime() - 1;
                        final long endTime = list.get(i).getEndTime() - 1;
                        if (list.get(i).getStatus() == 1) {
                            holder.countdown.setText(Utils.countdown(endTime) + "后结束");
                        } else if (list.get(i).getStatus() == 2) {
                            holder.countdown.setText(Utils.countdown(startTime) + "后开始");
                        }
                    }
                }
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFinish() {
            }
        };
        timer.start();
    }

    @Override
    public void onItemClick(View view, int position) {
        GoogsSpike entity = list.get(position);
        if (entity.getStatus() == 1) {
            ShoppingCartGoods goods = new ShoppingCartGoods(entity.getGoodsID(), entity.getImage(), entity.getGoodsName(), entity.getPrice(), 1, true);
            BaseApplication application = (BaseApplication) getApplication();
            if (!application.getGoodsList().contains(goods)) {
                application.addGoods(goods);
                EventBus.getDefault().postSticky(new ShoppingGoodsEvent(true));
                toast("加入购物车成功");
            } else {
                toast("该秒杀商品已放入购物车，每人限购一件");
            }
        } else {
            toast("活动尚未开启，敬请期待");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
    }

}
