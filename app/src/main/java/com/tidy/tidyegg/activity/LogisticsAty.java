package com.tidy.tidyegg.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.adapter.GoodsAdapter;
import com.tidy.tidyegg.adapter.LogisticsMessageAdapter;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.bean.LogisticsBean;
import com.tidy.tidyegg.bean.MineOrderListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dou on 2016/10/13.
 * 物流信息
 */
public class LogisticsAty extends BaseActivity{

    @BindView(R.id.head_logistics)
    RelativeLayout head_logistics;
    @BindView(R.id.lv_logistics_goods)
    RecyclerView lv_goods;
    @BindView(R.id.lv_logistics)
    RecyclerView lv_logistics;
    private MineOrderListBean orderMess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getBundle();
        if (bundle!=null){
            orderMess =  (MineOrderListBean)bundle.getSerializable("orderMess");
        }
        showView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_logistics;
    }

    public void showView(){
        this.setTitle(head_logistics,"物流信息");
        this.setLeft(head_logistics);
        lv_goods.setLayoutManager(new GridLayoutManager(mContext, 1));
        lv_goods.setHasFixedSize(true);
        lv_logistics.setLayoutManager(new GridLayoutManager(mContext, 1));
        lv_logistics.setHasFixedSize(true);
        initData();
    }

    public void initData(){
//        LogisticsBean logisticsBean = new LogisticsBean();
        List<LogisticsBean.Goods> goodsList = new ArrayList<LogisticsBean.Goods>();
        List<MineOrderListBean.Goods> goods = orderMess.getGoods();
        for (int i=0;i<goods.size();i++){
            MineOrderListBean.Goods good = goods.get(i);
            LogisticsBean.Goods bean = new LogisticsBean.Goods();
            bean.setName(good.getName());
            bean.setNum(good.getNum());
            bean.setPrice(good.getPrice());
            bean.setPicid(good.getPicid());
            goodsList.add(bean);
        }
        List<LogisticsBean.LogisticsMessage> logisticsList = new ArrayList<LogisticsBean.LogisticsMessage>();
        LogisticsBean.LogisticsMessage message1 = new LogisticsBean.LogisticsMessage();
        message1.setLocation("您的订单开始处理");
        message1.setTime("2016/10/18  23:05:12");
        logisticsList.add(message1);
        LogisticsBean.LogisticsMessage message2 = new LogisticsBean.LogisticsMessage();
        message2.setLocation("您的订单待配货");
        message2.setTime("2016/10/18  23:05:22");
        logisticsList.add(message2);
        LogisticsBean.LogisticsMessage message3 = new LogisticsBean.LogisticsMessage();
        message3.setLocation("包裹正在等待揽收");
        message3.setTime("2016/10/19  0:33:54");
        logisticsList.add(message3);
        LogisticsBean.LogisticsMessage message4 = new LogisticsBean.LogisticsMessage();
        message4.setLocation("您的包裹已出库");
        message4.setTime("2016/10/19  1:18:13");
        logisticsList.add(message4);
        LogisticsBean.LogisticsMessage message5 = new LogisticsBean.LogisticsMessage();
        message5.setLocation("您的包裹已揽件，揽收员：李伟");
        message5.setTime("2016/10/19  1:18:13");
        logisticsList.add(message5);
        LogisticsBean.LogisticsMessage message6 = new LogisticsBean.LogisticsMessage();
        message6.setLocation("上海市泰笛（上海）电子商务有限公司 从站点出发");
        message6.setTime("2016/10/19  7:50:42");
        logisticsList.add(message6);
        LogisticsBean.LogisticsMessage message7 = new LogisticsBean.LogisticsMessage();
        message7.setLocation("包裹已到达 上海速递处理中心");
        message7.setTime("2016/10/19  10:37:35");
        logisticsList.add(message7);
        LogisticsBean.LogisticsMessage message8 = new LogisticsBean.LogisticsMessage();
        message8.setLocation("上海速递处理中心 已发出");
        message8.setTime("2016/10/19  12:02:21");
        logisticsList.add(message8);
        LogisticsBean.LogisticsMessage message9 = new LogisticsBean.LogisticsMessage();
        message9.setLocation("静安（本部），安排投递，操作员：王军均");
        message9.setTime("2016/10/19  15:28:01");
        logisticsList.add(message9);
        LogisticsBean.LogisticsMessage message10 = new LogisticsBean.LogisticsMessage();
        message10.setLocation("上海速递 已发出");
        message10.setTime("2016/10/19  15:56:43");
        logisticsList.add(message10);
        LogisticsBean.LogisticsMessage message11 = new LogisticsBean.LogisticsMessage();
        message11.setLocation("静安（本部）派件员：何佳1036正在为您派件");
        message11.setTime("2016/10/19  16:34:40");
        logisticsList.add(message11);

        GoodsAdapter goodsAdapter = new GoodsAdapter(mContext, goodsList);
        lv_goods.setAdapter(goodsAdapter);

        LogisticsMessageAdapter logisticsMessageAdapter = new LogisticsMessageAdapter(mContext, logisticsList);
        lv_logistics.setAdapter(logisticsMessageAdapter);
    }
}
