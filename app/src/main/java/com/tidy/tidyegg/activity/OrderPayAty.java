package com.tidy.tidyegg.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.adapter.ShoppingCartAdapter;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.bean.ShoppingCartGoods;
import com.tidy.tidyegg.widget.RadioButtonDrawableLeft;
import com.tidy.tidyegg.widget.ScrollViewWithListView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by qiang on 16/10/17.
 * 生成订单并进行付款页面
 */
public class OrderPayAty extends BaseActivity {
    @BindView(R.id.title_orderPay)
    RelativeLayout title;
    @BindView(R.id.tv_orderPay_total)
    TextView tvTotal;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.tv_orderNum)
    TextView tvOrderNum;
    @BindView(R.id.lv_orderPay_list)
    ScrollViewWithListView mListView;
    @BindView(R.id.tv_orderPay_price)
    TextView tvPrice;
    @BindView(R.id.rb_pay_weixin)
    RadioButtonDrawableLeft rbPayWeixin;
    @BindView(R.id.rb_pay_ali)
    RadioButtonDrawableLeft rbPayAli;
    @BindView(R.id.sv_orderPay)
    ScrollView mScrollView;
    @BindView(R.id.rg_orderPay)
    RadioGroup mRadioGroup;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_order_pay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLeft(title);
        setTitle(title, "订单付款");

        initData();
        mScrollView.smoothScrollTo(0, 20);
    }

    private void initData() {
        Bundle bundle = getBundle();
        if (bundle != null) {
            tvOrderNum.setText("订单号: " + System.currentTimeMillis());

            ArrayList<ShoppingCartGoods> list = bundle.getParcelableArrayList("goodsList");
            ShoppingCartAdapter adapter = new ShoppingCartAdapter(this, list, decimalFormat, false, null);
            mListView.setAdapter(adapter);


            double totalPrice = bundle.getDouble("totalPrice");
            tvPrice.setText(getString(R.string.RMB) + decimalFormat.format(totalPrice));
            tvTotal.setText(Html.fromHtml("应付金额：" + "<font color='red'><big>" + getString(R.string.RMB) + decimalFormat.format(totalPrice) + "</big></font>"));


            initRadioGroup();
        }
    }

    private void initRadioGroup() {
        // 使用代码设置drawableleft
        final Drawable drawableNormal = getResources().getDrawable(R.mipmap.ic_pay_normal);
        // 这一步必须要做,否则不会显示.
        drawableNormal.setBounds(0, 0, drawableNormal.getIntrinsicWidth(), drawableNormal.getIntrinsicHeight());

        final Drawable drawablePress = getResources().getDrawable(R.mipmap.ic_pay_press);
        drawablePress.setBounds(0, 0, drawablePress.getIntrinsicWidth(), drawablePress.getIntrinsicHeight());

        rbPayWeixin.setChecked(true);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rb_pay_weixin:
                        rbPayWeixin.setChecked(true);
                        rbPayAli.setChecked(false);
                        rbPayAli.setCompoundDrawables(drawableNormal, null, null, null);
                        break;
                    case R.id.rb_pay_ali:
                        rbPayWeixin.setChecked(false);
                        rbPayAli.setChecked(true);
                        rbPayAli.setCompoundDrawables(drawablePress, null, null, null);
                        break;
                }
            }
        });
    }

}
