package com.tidy.tidyegg.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.base.BaseApplication;
import com.tidy.tidyegg.bean.CategoriesList;
import com.tidy.tidyegg.bean.ShoppingCartGoods;
import com.tidy.tidyegg.bean.TodayNewGoods;
import com.tidy.tidyegg.event.ShoppingGoodsEvent;
import com.tidy.tidyegg.fragment.EggGoodsDetailFrag;
import com.tidy.tidyegg.fragment.EggeValuationDetailFrag;
import com.tidy.tidyegg.utils.SpUtils;
import com.tidy.tidyegg.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by qiang on 16/10/18.
 * 各类型鸡蛋的详情页面
 */
public class EggDetailAty extends BaseActivity {
    @BindView(R.id.title_eggDetail)
    RelativeLayout title;
    @BindView(R.id.tv_favorite)
    TextView tvFavorite;
    @BindView(R.id.btn_addShoppingCart)
    Button btnAddShoppingCart;
    @BindView(R.id.btn_buyNow)
    Button btnBuyNow;
    @BindView(R.id.iv_eggDetail)
    ImageView ivEggDetail;
    @BindView(R.id.tv_eggName)
    TextView tvEggName;
    @BindView(R.id.tv_eggPrice)
    TextView tvEggPrice;
    @BindView(R.id.tv_eggDeposit)
    TextView tvEggDeposit;
    @BindView(R.id.tv_eggInventory)
    TextView tvEggInventory;
    @BindView(R.id.rb_eggDetail_goods)
    RadioButton rbEggDetailGoods;
    @BindView(R.id.rb_eggDetail_evaluation)
    RadioButton rbEggDetailEvaluation;
    @BindView(R.id.rg_eggDetail)
    RadioGroup rgEggDetail;
    @BindView(R.id.fl_eggDetail_fragment)
    FrameLayout mFrameLayout;
    @BindView(R.id.sv_eggDetail)
    NestedScrollView mScrollView;

    private TodayNewGoods todayNewGoods = null;
    private CategoriesList categoriesList = null;

    private String goodsID;
    private double totalPrice;

    private Fragment mContent;// 当前显示的Fragment
    private EggGoodsDetailFrag goodsFrag;
    private EggeValuationDetailFrag valuationFrag;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_egg_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLeft(title);

        Bundle bundle = getBundle();
        if (bundle != null) {
            todayNewGoods = bundle.getParcelable("TodayNewGoods");
            categoriesList = bundle.getParcelable("CategoriesList");
            if (todayNewGoods != null) {
                setTitle(title, todayNewGoods.getGoodsName());
                tvEggName.setText(todayNewGoods.getGoodsName());
                tvEggPrice.setText("售价:" + getString(R.string.RMB) + decimalFormat2.format(todayNewGoods.getGoodsPrice()) + "/箱");
                tvEggDeposit.setText("定金:" + getString(R.string.RMB) + todayNewGoods.getGoodsDeposit());
                tvEggInventory.setText("销量:" + todayNewGoods.getSalesNum() + "箱");
                goodsID = todayNewGoods.getGoodsID();
                totalPrice = todayNewGoods.getGoodsPrice();
                initData(todayNewGoods.getEggType(), goodsID);
            } else if (categoriesList != null) {
                setTitle(title, categoriesList.getGoodsName());
                tvEggName.setText(categoriesList.getGoodsName());
                tvEggPrice.setText("售价:" + getString(R.string.RMB) + decimalFormat2.format(categoriesList.getGoodsPrice()) + "/箱");
                tvEggDeposit.setText("定金:" + getString(R.string.RMB) + categoriesList.getGoodsDeposit());
                tvEggInventory.setText("销量:" + categoriesList.getSalesNum() + "箱");
                goodsID = categoriesList.getGoodsID();
                totalPrice = categoriesList.getGoodsPrice();
                initData(categoriesList.getEggType(), goodsID);
            }
        }
    }

    private void initData(int eggType, String goodsID) {
        boolean b = SpUtils.getSharePreBoolean(this, "favorite", goodsID);
        if (b) {
            Utils.setCompoundDrawablesTop(tvFavorite, getResources().getDrawable(R.mipmap.favorite_yes));
        } else {
            Utils.setCompoundDrawablesTop(tvFavorite, getResources().getDrawable(R.mipmap.favorite_no));
        }

        if (eggType == 1) {
            ivEggDetail.setBackgroundResource(R.mipmap.egg_1);
        } else if (eggType == 2) {
            ivEggDetail.setBackgroundResource(R.mipmap.egg_2);
        } else if (eggType == 3) {
            ivEggDetail.setBackgroundResource(R.mipmap.egg_3);
        } else if (eggType == 4) {
            ivEggDetail.setBackgroundResource(R.mipmap.egg_4);
        }

        goodsFrag = new EggGoodsDetailFrag(eggType);
        valuationFrag = new EggeValuationDetailFrag(goodsID);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_eggDetail_fragment, goodsFrag).commit();
        mContent = goodsFrag;
    }

    @OnClick({R.id.rb_eggDetail_goods, R.id.rb_eggDetail_evaluation, R.id.tv_favorite, R.id.btn_addShoppingCart, R.id.btn_buyNow})
    public void onClick(View view) {
        switch (view.getId()) {
            // 商品详情
            case R.id.rb_eggDetail_goods:
                switchContent(valuationFrag, goodsFrag);
                break;

            // 评价详情
            case R.id.rb_eggDetail_evaluation:
                switchContent(goodsFrag, valuationFrag);
                break;

            // 收藏
            case R.id.tv_favorite:
                boolean b = SpUtils.getSharePreBoolean(this, "favorite", goodsID);
                if (!b) {
                    Utils.setCompoundDrawablesTop(tvFavorite, getResources().getDrawable(R.mipmap.favorite_yes));
                    SpUtils.putSharePre(this, "favorite", goodsID, true);
                    toast("收藏成功");
                } else {
                    Utils.setCompoundDrawablesTop(tvFavorite, getResources().getDrawable(R.mipmap.favorite_no));
                    SpUtils.putSharePre(this, "favorite", goodsID, false);
                    toast("取消收藏");
                }
                break;

            // 加入购物车
            case R.id.btn_addShoppingCart:
                BaseApplication application = (BaseApplication) getApplication();
                if (todayNewGoods != null)
                    application.addGoods(new ShoppingCartGoods(goodsID, todayNewGoods.getImage(), todayNewGoods.getGoodsName(), todayNewGoods.getGoodsPrice(), 1, false));
                else if (categoriesList != null)
                    application.addGoods(new ShoppingCartGoods(goodsID, categoriesList.getImageID(), categoriesList.getGoodsName(), categoriesList.getGoodsPrice(), 1, false));
                EventBus.getDefault().postSticky(new ShoppingGoodsEvent(true));
                toast("加入购物车成功");
                break;

            case R.id.btn_buyNow:
                ArrayList<ShoppingCartGoods> list = new ArrayList<>();
                ShoppingCartGoods entity = null;
                if (todayNewGoods != null)
                    entity = new ShoppingCartGoods(goodsID, todayNewGoods.getImage(), todayNewGoods.getGoodsName(), todayNewGoods.getGoodsPrice(), 1, false);
                else if (categoriesList != null)
                    entity = new ShoppingCartGoods(goodsID, categoriesList.getImageID(), categoriesList.getGoodsName(), categoriesList.getGoodsPrice(), 1, false);
                list.add(entity);
                Bundle bundle = new Bundle();
                bundle.putDouble("totalPrice", totalPrice);
                bundle.putParcelableArrayList("goodsList", list);
                startActivity(SubmitOrderAty.class, bundle);
                break;
        }
    }

    private void switchContent(Fragment from, Fragment to) {
        if (mContent != to) {
            mContent = to;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) {// 先判断是否被add过
                transaction.hide(from).add(R.id.fl_eggDetail_fragment, to).commit();// 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit();// 隐藏当前的fragment，显示下一个
            }
        }
    }

}
