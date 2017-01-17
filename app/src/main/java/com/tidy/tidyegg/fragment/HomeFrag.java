package com.tidy.tidyegg.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.tidy.tidyegg.R;
import com.tidy.tidyegg.activity.ChooseCityAty;
import com.tidy.tidyegg.activity.EggDetailAty;
import com.tidy.tidyegg.activity.GoogsSpikeAty;
import com.tidy.tidyegg.adapter.HomeTodayNewGoodsAdapter;
import com.tidy.tidyegg.app.AppConfig;
import com.tidy.tidyegg.base.BaseFragment;
import com.tidy.tidyegg.bean.TodayNewGoods;
import com.tidy.tidyegg.callback.OnItemClickListener;
import com.tidy.tidyegg.utils.Utils;
import com.tidy.tidyegg.widget.recyclerview.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by qiang on 2016/10/9.
 * 导航首页页面
 */
public class HomeFrag extends BaseFragment {
    @BindView(R.id.slider)
    SliderLayout mSlider;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_cityName)
    TextView tv_cityName;
    @BindView(R.id.iv_QRcode)
    ImageView iv_QRcode;
    @BindView(R.id.ll_spike)
    LinearLayout ll_spike;
    @BindView(R.id.et_searchContent)
    EditText et_SearchContent;
    @BindView(R.id.sv_home)
    NestedScrollView mScrollView;
    @BindView(R.id.tv_days)
    TextView tvDays;
    @BindView(R.id.tv_hours)
    TextView tvHours;
    @BindView(R.id.tv_minutes)
    TextView tvMinutes;
    @BindView(R.id.tv_seconds)
    TextView tvSeconds;
    @BindView(R.id.ll_searchLayout)
    LinearLayout searchLayout;

    private CountDownTimer timer;

    private List<TodayNewGoods> list = new ArrayList<TodayNewGoods>();

    @Override
    protected int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    protected void initData() {
        // 设置搜索框的背景透明度
        searchLayout.getBackground().setAlpha(120);
        // 下面两行代码用来设置禁止RecyclerView获取焦点导致ScrollView自动滚动的问题
        mRecyclerView.setFocusable(false);
        mRecyclerView.setFocusableInTouchMode(false);

        initSlider();
        initCountdown();
        initRecyclerView();
        mScrollView.smoothScrollTo(0, 20);
    }

    private void initSlider() {
        LinkedHashMap<String, Integer> file_maps = new LinkedHashMap<String, Integer>();
        file_maps.put("大大的鸡蛋", R.mipmap.banner_1);
        file_maps.put("每一颗都安全", R.mipmap.banner_2);
        file_maps.put("通体饱满", R.mipmap.banner_3);
        file_maps.put("味道鲜美", R.mipmap.banner_4);
        for (String name : file_maps.keySet()) {
//            TextSliderView textSliderView = new TextSliderView(getActivity());
//            // initialize a SliderLayout
//            textSliderView
//                    .description(name)
//                    .image(file_maps.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);
//
//            //add your extra information
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle().putString("extra", name);
//            mSlider.addSlider(textSliderView);

            DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
            defaultSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);
            mSlider.addSlider(defaultSliderView);
        }
        mSlider.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setDuration(4000);
    }

    private void initCountdown() {
        timer = new CountDownTimer(1479257095 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                String string = Utils.countdown(1479257095);
                tvDays.setText(string.substring(0, string.lastIndexOf("天")));
                tvHours.setText(string.substring(string.lastIndexOf("天") + 1, string.lastIndexOf("时")));
                tvMinutes.setText(string.substring(string.lastIndexOf("时") + 1, string.lastIndexOf("分")));
                tvSeconds.setText(string.substring(string.lastIndexOf("分") + 1, string.lastIndexOf("秒")));
            }

            @Override
            public void onFinish() {
            }
        };
        timer.start();
    }

    private void initRecyclerView() {
        initGoods();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        // 设置间隔
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(
                getResources().getDimensionPixelSize(R.dimen.space_3),
                getResources().getDimensionPixelSize(R.dimen.space_3),
                getResources().getDimensionPixelSize(R.dimen.space_3),
                getResources().getDimensionPixelSize(R.dimen.space_3)
        ));
        // 让RecyclerView顺滑滑动
        mRecyclerView.setNestedScrollingEnabled(false);
        HomeTodayNewGoodsAdapter adapter = new HomeTodayNewGoodsAdapter(getActivity(), list);
        adapter.setOnItemClickLitener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                BaseApplication application = (BaseApplication) getActivity().getApplication();
//                application.addGoods(new ShoppingCartGoods("000011", R.mipmap.goods_image, "呵呵蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋蛋", 500.02, 1));
//                EventBus.getDefault().postSticky(new ShoppingGoodsEvent(true));

                Bundle bundle = new Bundle();
                bundle.putParcelable("TodayNewGoods", list.get(position));
                startActivity(EggDetailAty.class, bundle);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    private void initGoods() {
        TodayNewGoods bean = new TodayNewGoods();
        bean.setImage(R.mipmap.xianju_lr);
        bean.setGoodsID("100003");
        bean.setGoodsName("仙居鸡AA级泰笛老人蛋");
        bean.setGoodsPrice(180);
        bean.setGoodsDeposit("18/箱");
        bean.setEggType(4);
        bean.setSalesNum(704);
        list.add(bean);

        TodayNewGoods bean2 = new TodayNewGoods();
        bean2.setImage(R.mipmap.xingza_bb);
        bean2.setGoodsID("100004");
        bean2.setGoodsName("星杂288AAA+级泰笛宝宝蛋");
        bean2.setGoodsPrice(273);
        bean2.setGoodsDeposit("27.3/箱");
        bean2.setEggType(1);
        bean2.setSalesNum(665);
        list.add(bean2);

        TodayNewGoods bean3 = new TodayNewGoods();
        bean3.setImage(R.mipmap.xingza_sn);
        bean3.setGoodsID("100020");
        bean3.setGoodsName("星杂290AAA+级泰笛青少年蛋");
        bean3.setGoodsPrice(246);
        bean3.setGoodsDeposit("24.6/箱");
        bean3.setEggType(3);
        bean3.setSalesNum(753);
        list.add(bean3);

        TodayNewGoods bean4 = new TodayNewGoods();
        bean4.setImage(R.mipmap.xianju_yf);
        bean4.setGoodsID("100023");
        bean4.setGoodsName("仙居鸡AAA级泰笛孕妇蛋");
        bean4.setGoodsPrice(230);
        bean4.setGoodsDeposit("23/箱");
        bean4.setEggType(2);
        bean4.setSalesNum(737);
        list.add(bean4);
    }

    @OnClick({R.id.tv_cityName, R.id.iv_QRcode, R.id.ll_spike})
    public void onClick(View view) {
        switch (view.getId()) {
            // 选择城市
            case R.id.tv_cityName:
                startActivityForResult(ChooseCityAty.class, null, AppConfig.CHOOSE_CITY);
                break;

            // 商品秒杀
            case R.id.ll_spike:
                startActivity(GoogsSpikeAty.class);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppConfig.CHOOSE_CITY && resultCode == Activity.RESULT_OK && data != null) {
            tv_cityName.setText(data.getStringExtra("city"));
        }
    }

    @Override
    public void onDestroy() {
        if (mSlider != null)
            mSlider.stopAutoCycle();
        if (timer != null)
            timer.cancel();
        super.onDestroy();
    }

}
