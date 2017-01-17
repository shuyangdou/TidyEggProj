package com.tidy.tidyegg.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.adapter.GoodsAdapter;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.bean.DetailGoodsBean;
import com.tidy.tidyegg.bean.LogisticsBean;
import com.tidy.tidyegg.bean.MineOrderListBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dou on 2016/10/13.
 * 我要评价的页面
 */
public class EvaluateAty extends BaseActivity{

    @BindView(R.id.head_evaluate)
    RelativeLayout head_evaluate;
    @BindView(R.id.lv_evaluate)
    RecyclerView lv_evaluate;
    @BindView(R.id.rb_evaluate_miaoshu)
    RatingBar rb_miaoshu;
    @BindView(R.id.rb_evaluate_fuwu)
    RatingBar rb_fuwu;
    @BindView(R.id.rb_evaluate_speed)
    RatingBar rb_speed;
    @BindView(R.id.ed_evaluate)
    EditText ed_evaluate;
    @BindView(R.id.btn_evaluate)
    Button btn_evaluate;
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
        return R.layout.aty_evaluate;
    }

    public void showView(){
        this.setLeft(head_evaluate);
        this.setTitle(head_evaluate,"写评价");
        lv_evaluate.setLayoutManager(new GridLayoutManager(mContext, 1));
        lv_evaluate.setHasFixedSize(true);
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

        GoodsAdapter goodsAdapter = new GoodsAdapter(mContext, goodsList);
        lv_evaluate.setAdapter(goodsAdapter);
    }
}
