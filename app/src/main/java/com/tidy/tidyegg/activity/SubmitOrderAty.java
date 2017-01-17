package com.tidy.tidyegg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.adapter.ShoppingCartAdapter;
import com.tidy.tidyegg.app.AppConfig;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.bean.MineAddBean;
import com.tidy.tidyegg.bean.ShoppingCartGoods;
import com.tidy.tidyegg.utils.SpUtils;
import com.tidy.tidyegg.widget.ScrollViewWithListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by qiang on 16/10/14.
 * 购物车商品结算，提交订单页面
 */
public class SubmitOrderAty extends BaseActivity {
    @BindView(R.id.title_submitOrder)
    RelativeLayout title;
    @BindView(R.id.tv_receiverInfo)
    TextView tv_receiverInfo;
    @BindView(R.id.rl_receiverInfo)
    RelativeLayout rl_receiverInfo;
    @BindView(R.id.lv_goodsList)
    ScrollViewWithListView mListView;
    @BindView(R.id.et_messageContent)
    EditText et_message;
    @BindView(R.id.tv_submitOrder_total)
    TextView tv_total;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.sv_submitOrder)
    ScrollView mScrollView;

    private ArrayList<ShoppingCartGoods> list;// 商品数据
    private double totalPrice;// 订单总价

    @Override
    protected int getLayoutId() {
        return R.layout.aty_submit_order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(title, "提交订单");
        setLeft(title);

        initData();
        mScrollView.smoothScrollTo(0, 20);
    }

    @OnClick({R.id.rl_receiverInfo, R.id.btn_submit})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            // 选择收件人
            case R.id.rl_receiverInfo:
                bundle.putBoolean("isChoose", true);
                startActivityForResult(MineAddAty.class, bundle, AppConfig.CHOOSE_ADDRESS);
                break;

            // 提交订单
            case R.id.btn_submit:
                bundle.putParcelableArrayList("goodsList", list);
                bundle.putDouble("totalPrice", totalPrice);
                startActivity(OrderPayAty.class, bundle);
                break;
        }
    }

    private void initData() {
        Bundle bundle = getBundle();
        if (bundle != null) {
            MineAddBean entity = (MineAddBean) SpUtils.getObject(this, "ADD_MOREN", "MOREN");
            if (entity != null)
                tv_receiverInfo.setText("收货人:" + entity.getName() + "       " + entity.getPhone() + "\n\n收货地址:" + entity.getAdd());

            list = bundle.getParcelableArrayList("goodsList");
            ShoppingCartAdapter adapter = new ShoppingCartAdapter(this, list, decimalFormat, false, null);
            mListView.setAdapter(adapter);

            totalPrice = bundle.getDouble("totalPrice");
            tv_total.setText(Html.fromHtml("总计: " + "<font color='red'><big>" + getString(R.string.RMB) + decimalFormat.format(totalPrice) + "</big></font>"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppConfig.CHOOSE_ADDRESS && resultCode == RESULT_OK && data != null) {
            MineAddBean entity = (MineAddBean) data.getSerializableExtra("MineAddBean");
            tv_receiverInfo.setText("收货人:" + entity.getName() + "       " + entity.getPhone() + "\n\n收货地址:" + entity.getAdd());
        }
    }
}
