package com.tidy.tidyegg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.adapter.DetailgoodsAdapter;
import com.tidy.tidyegg.adapter.DetailorderlistAdapter;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.bean.DetailGoodsBean;
import com.tidy.tidyegg.bean.DetailOrderListBean;
import com.tidy.tidyegg.bean.MineOrderListBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dou on 2016/10/12.
 * 我的订单详情
 */
public class OrderListDetailAty extends BaseActivity implements View.OnClickListener {
    /** 头标题 */
    @BindView(R.id.head_orderlistdetail)
    RelativeLayout head_orderlistdetail;
    /** 详情标题 */
    @BindView(R.id.tv_orderdetail_title)
    TextView tv_titel;
    /** 订单金额 */
    @BindView(R.id.tv_detail_paywithfreight)
    TextView tv_paywithfreight;
    /** 收货人 */
    @BindView(R.id.tv_detail_receiver)
    TextView tv_receiver;
    /** 收货人电话 */
    @BindView(R.id.tv_detail_receivephone)
    TextView tv_receivephone;

    /** 收货人地址 */
    @BindView(R.id.tv_detail_receiveadd)
    TextView tv_detail_receiveadd;
    /** 物流和收货人地址中间的横线 */
    @BindView(R.id.view_wuli)
    View view_wuli;
    /** 物流布局 */
    @BindView(R.id.ll_detail_wulixinxi)
    LinearLayout ll_wulixinxi;
    /** 物流时间 */
    @BindView(R.id.tv_detail_time)
    TextView tv_detail_time;
    /** 物流信息 */
    @BindView(R.id.tv_detail_mess)
    TextView tv_detail_mess;
    /** 状态 */
    @BindView(R.id.tv_detail_type)
    TextView tv_detail_type;
    /** 商品list */
    @BindView(R.id.rv_detail_goods)
    RecyclerView rv_goods;
    /** 实付费 */
    @BindView(R.id.tv_detail_freightpay)
    TextView tv_detail_freightpay;
    /** 订单信息list */
    @BindView(R.id.rv_detail_orderlist)
    RecyclerView rv_orderlist;
    /** 按钮布局 */
    @BindView(R.id.ll_detail_btn)
    LinearLayout ll_detail_btn;
    /** 左按钮 */
    @BindView(R.id.tv_detailbtn_left)
    TextView tv_left;
    /** 右按钮 */
    @BindView(R.id.tv_detailbtn_night)
    TextView tv_night;
    private int type;
    private MineOrderListBean orderMess;
    private String pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getBundle();
        if (bundle != null){
            type = bundle.getInt("type");
            orderMess = (MineOrderListBean)bundle.getSerializable("orderMess");
            pay = bundle.getString("pay");
        }
        showView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_orderlistdetail;
    }

    public void showView(){
        this.setTitle(head_orderlistdetail,"订单详情");
        this.setLeft(head_orderlistdetail);
        if (type == 1){
            tv_detail_type.setText("待付款");
            tv_titel.setText("等待买家付款");
            view_wuli.setVisibility(View.GONE);
            ll_wulixinxi.setVisibility(View.GONE);
            tv_detail_mess.setVisibility(View.GONE);
            ll_detail_btn.setVisibility(View.VISIBLE);
            tv_left.setText("取消订单");
            tv_night.setText("付款");
        }else if (type == 2){
            tv_detail_type.setText("待发货");
            tv_titel.setText("已付款，等待卖家发货");
            view_wuli.setVisibility(View.GONE);
            ll_wulixinxi.setVisibility(View.GONE);
            tv_detail_mess.setVisibility(View.GONE);
            ll_detail_btn.setVisibility(View.GONE);
        }else if (type == 3){
            tv_detail_type.setText("待收货");
            tv_titel.setText("卖家已发货");
            view_wuli.setVisibility(View.GONE);
            ll_wulixinxi.setVisibility(View.GONE);
            tv_detail_mess.setVisibility(View.GONE);
            ll_detail_btn.setVisibility(View.VISIBLE);
            tv_left.setText("查看物流");
            tv_night.setText("确认收货");
        }else if (type == 4){
            tv_detail_type.setText("待评价");
            tv_titel.setText("卖家已发货");
            view_wuli.setVisibility(View.VISIBLE);
            ll_wulixinxi.setVisibility(View.VISIBLE);
            tv_detail_mess.setVisibility(View.VISIBLE);
            ll_detail_btn.setVisibility(View.VISIBLE);
            tv_left.setText("查看物流");
            tv_night.setText("订单评价");
        }
        rv_goods.setLayoutManager(new GridLayoutManager(mContext, 1));
        rv_goods.setHasFixedSize(true);
        rv_orderlist.setLayoutManager(new GridLayoutManager(mContext, 1));
        rv_orderlist.setHasFixedSize(true);
        initData();
        tv_left.setOnClickListener(this);
        tv_night.setOnClickListener(this);

        tv_paywithfreight.setText("订单金额:"+pay);
        tv_detail_freightpay.setText(mContext.getString(R.string.RMB)+pay);

    }

    public void initData(){
        List<DetailGoodsBean> dataList = new ArrayList<DetailGoodsBean>();
        List<MineOrderListBean.Goods> goods = orderMess.getGoods();
        for (int i=0;i<goods.size();i++){
            MineOrderListBean.Goods good = goods.get(i);
            DetailGoodsBean bean = new DetailGoodsBean();
            bean.setName(good.getName());
            bean.setNum(good.getNum());
            bean.setPrice(good.getPrice());
            bean.setPicId(good.getPicid());
            dataList.add(bean);
        }
        DetailgoodsAdapter detailgoodsAdapter = new DetailgoodsAdapter(mContext, dataList);
        rv_goods.setAdapter(detailgoodsAdapter);

        List<DetailOrderListBean> orderList = new ArrayList<DetailOrderListBean>();
        DetailOrderListBean bean = new DetailOrderListBean();
        bean.setName("订单编号:"+ orderMess.getOrderId());

        orderList.add(bean);
        DetailorderlistAdapter detailorderlistAdapter = new DetailorderlistAdapter(mContext, orderList);
        rv_orderlist.setAdapter(detailorderlistAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /** 左按钮 */
            case R.id.tv_detailbtn_left:
                if (type == 1){
                    //取消订单

                }else if (type == 3){
                    //查看物流
                    Intent intent = new Intent(mContext ,LogisticsAty.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("orderMess",orderMess);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if (type == 4){
                    //查看物流
                    Intent intent = new Intent(mContext ,LogisticsAty.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("orderMess",orderMess);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                break;
            /** 右按钮 */
            case R.id.tv_detailbtn_night:
                if (type == 1){
                    //付款

                }else if (type == 3){
                    //确认收货

                }else if (type == 4){
                    //我要评价
                    Intent intent = new Intent(mContext ,EvaluateAty.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("orderMess",orderMess);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
        }
    }
}
