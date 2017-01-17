package com.tidy.tidyegg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.activity.EvaluateAty;
import com.tidy.tidyegg.activity.LogisticsAty;
import com.tidy.tidyegg.activity.OrderListDetailAty;
import com.tidy.tidyegg.adapter.MineOrderAdapter;
import com.tidy.tidyegg.base.BaseFragment;
import com.tidy.tidyegg.bean.MineOrderListBean;
import com.tidy.tidyegg.callback.ListViewItemButtonClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dou on 2016/10/11.
 * 我的订单中的page。。待付款，待发货，待收货，待评价
 */
public class MineOrderFrag extends BaseFragment implements ListViewItemButtonClickListener{
    @BindView(R.id.lv_mineorder)
    ListView lv_mineorder;
    private int type;
    private List<MineOrderListBean> beanlist = new ArrayList<MineOrderListBean>();;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_mineorder;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null){
            type = bundle.getInt("type");
        }
        beanlist.clear();
        if (type == 1){
            List<MineOrderListBean.Goods> goodsdses = new ArrayList<MineOrderListBean.Goods>();
            MineOrderListBean mineOrderListBean1 = new MineOrderListBean();
            mineOrderListBean1.setTopay("13920.00");
            mineOrderListBean1.setOrderId("TD20161009HZ00059");
            MineOrderListBean.Goods goods = new MineOrderListBean.Goods();
            goods.setName("仙居鸡AA级泰笛老人蛋");
            goods.setPrice(mContext.getString(R.string.RMB)+"180/箱");
            goods.setNum("x50");
            goods.setPicid("xjlr");
            MineOrderListBean.Goods goods1 = new MineOrderListBean.Goods();
            goods1.setName("农昌2号AAA级泰笛宝宝蛋");
            goods1.setPrice(mContext.getString(R.string.RMB)+"246/箱");
            goods1.setNum("x20");
            goods1.setPicid("ncbb");
            goodsdses.add(goods);
            goodsdses.add(goods1);
            mineOrderListBean1.setGoods(goodsdses);

            List<MineOrderListBean.Goods> goodsdses1 = new ArrayList<MineOrderListBean.Goods>();
            MineOrderListBean mineOrderListBean2 = new MineOrderListBean();
            mineOrderListBean2.setTopay("1650.00");
            mineOrderListBean2.setOrderId("TD20161009SH00158");
            MineOrderListBean.Goods goods2 = new MineOrderListBean.Goods();
            goods2.setName("浦东鸡AAA级泰笛青少年蛋");
            goods2.setPrice(mContext.getString(R.string.RMB)+"165/箱");
            goods2.setNum("x10");
            goods2.setPicid("pdsn");
            goodsdses1.add(goods2);
            mineOrderListBean2.setGoods(goodsdses1);


            List<MineOrderListBean.Goods> goodsdses2 = new ArrayList<MineOrderListBean.Goods>();
            MineOrderListBean mineOrderListBean3 = new MineOrderListBean();
            mineOrderListBean3.setTopay("21840.00");
            mineOrderListBean3.setOrderId("TD20161009SH00157");
            MineOrderListBean.Goods goods3 = new MineOrderListBean.Goods();
            goods3.setName("海兰褐AAA级泰笛老人蛋");
            goods3.setPrice(mContext.getString(R.string.RMB)+"273/箱");
            goods3.setNum("x80");
            goods3.setPicid("hlhlr");
            goodsdses2.add(goods3);
            mineOrderListBean3.setGoods(goodsdses2);

            List<MineOrderListBean.Goods> goodsdses3 = new ArrayList<MineOrderListBean.Goods>();
            MineOrderListBean mineOrderListBean4 = new MineOrderListBean();
            mineOrderListBean4.setTopay("17220.00");
            mineOrderListBean4.setOrderId("TD20161009HZ00059");
            MineOrderListBean.Goods goods4 = new MineOrderListBean.Goods();
            goods4.setName("星杂290AAA+级泰笛青少年蛋");
            goods4.setPrice(mContext.getString(R.string.RMB)+"246/箱");
            goods4.setNum("x70");
            goods4.setPicid("xzsn");
            goodsdses3.add(goods4);
            mineOrderListBean4.setGoods(goodsdses3);

            beanlist.add(mineOrderListBean1);
            beanlist.add(mineOrderListBean2);
            beanlist.add(mineOrderListBean3);
            beanlist.add(mineOrderListBean4);
        }else if (type == 2){
            List<MineOrderListBean.Goods> goodsdses = new ArrayList<MineOrderListBean.Goods>();
            MineOrderListBean mineOrderListBean1 = new MineOrderListBean();
            mineOrderListBean1.setTopay("40440.00");
            mineOrderListBean1.setOrderId("TD20161009SH00158");
            MineOrderListBean.Goods goods = new MineOrderListBean.Goods();
            goods.setName("海兰褐AAA级泰笛老人蛋");
            goods.setPrice(mContext.getString(R.string.RMB)+"273/箱");
            goods.setNum("x60");
            goods.setPicid("hlhlr");
            MineOrderListBean.Goods goods1 = new MineOrderListBean.Goods();
            goods1.setName("海兰褐AAA级泰笛青少年蛋");
            goods1.setPrice(mContext.getString(R.string.RMB)+"258/箱");
            goods1.setNum("x60");
            goods1.setPicid("hlhsn");
            MineOrderListBean.Goods goods2 = new MineOrderListBean.Goods();
            goods2.setName("海兰褐AAA级泰笛宝宝蛋");
            goods2.setPrice(mContext.getString(R.string.RMB)+"268/箱");
            goods2.setNum("x30");
            goods2.setPicid("hlhbb");
            goodsdses.add(goods);
            goodsdses.add(goods1);
            goodsdses.add(goods2);
            mineOrderListBean1.setGoods(goodsdses);

            List<MineOrderListBean.Goods> goodsdses1 = new ArrayList<MineOrderListBean.Goods>();
            MineOrderListBean mineOrderListBean2 = new MineOrderListBean();
            mineOrderListBean2.setTopay("16500.00");
            mineOrderListBean2.setOrderId("TD20161019SH64832");
            MineOrderListBean.Goods goods3 = new MineOrderListBean.Goods();
            goods3.setName("星杂289AAA级泰笛孕妇蛋");
            goods3.setPrice(mContext.getString(R.string.RMB)+"165/箱");
            goods3.setNum("x100");
            goods3.setPicid("xzyf");
            goodsdses1.add(goods3);
            mineOrderListBean2.setGoods(goodsdses1);

            beanlist.add(mineOrderListBean1);
            beanlist.add(mineOrderListBean2);
        }else if (type == 3){
            List<MineOrderListBean.Goods> goodsdses = new ArrayList<MineOrderListBean.Goods>();
            MineOrderListBean mineOrderListBean1 = new MineOrderListBean();
            mineOrderListBean1.setTopay("13650.00");
            mineOrderListBean1.setOrderId("TD20161018SH39634");
            MineOrderListBean.Goods goods = new MineOrderListBean.Goods();
            goods.setName("星杂288AAA+级泰笛宝宝蛋");
            goods.setPrice(mContext.getString(R.string.RMB)+"273/箱");
            goods.setNum("x50");
            goods.setPicid("xzbb");
            goodsdses.add(goods);
            mineOrderListBean1.setGoods(goodsdses);

            List<MineOrderListBean.Goods> goodsdses1 = new ArrayList<MineOrderListBean.Goods>();
            MineOrderListBean mineOrderListBean2 = new MineOrderListBean();
            mineOrderListBean2.setTopay("11500.00");
            mineOrderListBean2.setOrderId("TD20161018SH64231");
            MineOrderListBean.Goods goods1 = new MineOrderListBean.Goods();
            goods1.setName("星杂289AAA级泰笛孕妇蛋");
            goods1.setPrice(mContext.getString(R.string.RMB)+"165/箱");
            goods1.setNum("x70");
            goods1.setPicid("xzyf");
            goodsdses1.add(goods1);
            mineOrderListBean2.setGoods(goodsdses1);

            List<MineOrderListBean.Goods> goodsdses2 = new ArrayList<MineOrderListBean.Goods>();
            MineOrderListBean mineOrderListBean3 = new MineOrderListBean();
            mineOrderListBean3.setTopay("4920.00");
            mineOrderListBean3.setOrderId("TD20161018SH03284");
            MineOrderListBean.Goods goods2 = new MineOrderListBean.Goods();
            goods2.setName("星杂290AAA+级泰笛青少年蛋");
            goods2.setPrice(mContext.getString(R.string.RMB)+"246/箱");
            goods2.setNum("x20");
            goods2.setPicid("xzsn");
            goodsdses2.add(goods2);
            mineOrderListBean3.setGoods(goodsdses2);

            beanlist.add(mineOrderListBean1);
            beanlist.add(mineOrderListBean2);
            beanlist.add(mineOrderListBean3);
        }else if (type == 4){
            List<MineOrderListBean.Goods> goodsdses = new ArrayList<MineOrderListBean.Goods>();
            MineOrderListBean mineOrderListBean1 = new MineOrderListBean();
            mineOrderListBean1.setTopay("12850.00");
            mineOrderListBean1.setOrderId("TD20161009SH00159");
            MineOrderListBean.Goods goods = new MineOrderListBean.Goods();
            goods.setName("仙居鸡AAA+级泰笛宝宝蛋");
            goods.setPrice(mContext.getString(R.string.RMB)+"257/箱");
            goods.setNum("x50");
            goods.setPicid("xjbb");
            goodsdses.add(goods);
            mineOrderListBean1.setGoods(goodsdses);

            List<MineOrderListBean.Goods> goodsdses1 = new ArrayList<MineOrderListBean.Goods>();
            MineOrderListBean mineOrderListBean2 = new MineOrderListBean();
            mineOrderListBean2.setTopay("11000.00");
            mineOrderListBean2.setOrderId("TD20161016SH48376");
            MineOrderListBean.Goods goods1 = new MineOrderListBean.Goods();
            goods1.setName("仙居鸡AA级泰笛青少年蛋");
            goods1.setPrice(mContext.getString(R.string.RMB)+"220/箱");
            goods1.setNum("x50");
            goods1.setPicid("xjsn");
            goodsdses1.add(goods1);
            mineOrderListBean2.setGoods(goodsdses1);

            List<MineOrderListBean.Goods> goodsdses2 = new ArrayList<MineOrderListBean.Goods>();
            MineOrderListBean mineOrderListBean3 = new MineOrderListBean();
            mineOrderListBean3.setTopay("16380.00");
            mineOrderListBean3.setOrderId("TD20161015SH68521");
            MineOrderListBean.Goods goods2 = new MineOrderListBean.Goods();
            goods2.setName("星杂288AAA+级泰笛宝宝蛋");
            goods2.setPrice(mContext.getString(R.string.RMB)+"273/箱");
            goods2.setNum("x60");
            goods2.setPicid("xzbb");
            goodsdses2.add(goods2);
            mineOrderListBean3.setGoods(goodsdses2);

            List<MineOrderListBean.Goods> goodsdses3 = new ArrayList<MineOrderListBean.Goods>();
            MineOrderListBean mineOrderListBean4 = new MineOrderListBean();
            mineOrderListBean4.setTopay("12375.00");
            mineOrderListBean4.setOrderId("TD20161009SH00157");
            MineOrderListBean.Goods goods3 = new MineOrderListBean.Goods();
            goods3.setName("星杂289AAA级泰笛孕妇蛋");
            goods3.setPrice(mContext.getString(R.string.RMB)+"165/箱");
            goods3.setNum("x75");
            goods3.setPicid("xzyf");
            goodsdses3.add(goods3);
            mineOrderListBean4.setGoods(goodsdses3);

            beanlist.add(mineOrderListBean1);
            beanlist.add(mineOrderListBean2);
            beanlist.add(mineOrderListBean3);
            beanlist.add(mineOrderListBean4);
        }

        lv_mineorder.setFocusable(true);
        MineOrderAdapter mineOrderAdapter = new MineOrderAdapter(mContext, beanlist,type,this);
        lv_mineorder.setAdapter(mineOrderAdapter);
        lv_mineorder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext ,OrderListDetailAty.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type",type);
                MineOrderListBean orderListBean = beanlist.get(position);
                bundle.putString("pay",orderListBean.getTopay());
                bundle.putSerializable("orderMess",orderListBean);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View item, View widget, int position, int which) {
        switch (which){
            case R.id.tv_orderlist_btnleft:
                if (type == 1){
                    //取消订单

                }else if (type == 3){
                    //查看物流
                    Intent intent = new Intent(mContext ,LogisticsAty.class);
                    Bundle bundle = new Bundle();
                    MineOrderListBean orderListBean = beanlist.get(position);
                    bundle.putSerializable("orderMess",orderListBean);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if (type == 4){

                }
                break;

            case R.id.tv_orderlist_btnright:
                if (type == 1){
                    //立即付款

                }else if (type == 3){
                    //确认收货

                }else if (type == 4){
                    //我要评价
                    Intent intent = new Intent(mContext ,EvaluateAty.class);
                    Bundle bundle = new Bundle();
                    MineOrderListBean orderListBean = beanlist.get(position);
                    bundle.putSerializable("orderMess",orderListBean);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.ll_list:
                Intent intent = new Intent(mContext ,OrderListDetailAty.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type",type);
                MineOrderListBean orderListBean = beanlist.get(position);
                bundle.putString("pay",orderListBean.getTopay());
                bundle.putSerializable("orderMess",orderListBean);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
