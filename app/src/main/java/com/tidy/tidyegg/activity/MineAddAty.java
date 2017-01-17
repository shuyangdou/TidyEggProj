package com.tidy.tidyegg.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.tidy.tidyegg.R;
import com.tidy.tidyegg.adapter.MineAddAdapter;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.bean.MineAddBean;
import com.tidy.tidyegg.callback.ListViewItemButtonClickListener;
import com.tidy.tidyegg.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by DOU on 2016/10/11
 * 我的收货地址
 */
public class MineAddAty extends BaseActivity implements View.OnClickListener,ListViewItemButtonClickListener {

    @BindView(R.id.head_mineadd)
    RelativeLayout head_mineadd;

    @BindView(R.id.lv_mineadd)
    SwipeMenuListView lv_mineadd;

    @BindView(R.id.tv_mineadd_empty)
    TextView tv_mineadd_empty;
    @BindView(R.id.tv_add)
    TextView tv_add;
    private List<MineAddBean> addList;
    private boolean isChoose;
    private MineAddAdapter addAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getBundle();
        if (bundle != null){
            isChoose = bundle.getBoolean("isChoose");
        }
        showView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_mineadd;
    }

    public void initData() {
        addList = new ArrayList<MineAddBean>();
        MineAddBean addBean1 = new MineAddBean();
        addBean1.setName("李先生");
        addBean1.setPhone("18915581676");
        addBean1.setAdd("上海市虹口区广济路766号B栋-503");
        addBean1.setMo("yes");
        MineAddBean addBean2 = new MineAddBean();
        addBean2.setName("王先生");
        addBean2.setPhone("15993002661");
        addBean2.setAdd("上海市长宁区平塘路309弄27栋-302");
        addBean2.setMo("no");
        addList.add(addBean1);
        addList.add(addBean2);
        addAdapter = new MineAddAdapter(mContext, addList,MineAddAty.this);
        lv_mineadd.setAdapter(addAdapter);
    }

    public void showView() {
        this.setTitle(head_mineadd, "我的收货地址");
        this.setLeft(head_mineadd);
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
        lv_mineadd.setMenuCreator(creator);

        lv_mineadd.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        addList.remove(position);
                        addAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

        lv_mineadd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isChoose){
                    Intent intent = new Intent();
                    intent.putExtra("MineAddBean",addList.get(position));
                    setResult(RESULT_OK,intent);
                    finish();
                }else{

                }
            }
        });

        tv_add.setOnClickListener(this);
        initData();
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add:
                Bundle bundle = new Bundle();
                bundle.putString("seltype", "add");
                startActivity(NewAddressAty.class, bundle);
                break;
        }
    }

    @Override
    public void onClick(View item, View widget, int position, int which) {
        switch (which){
            case R.id.tv_addmodify:
                Bundle bundle = new Bundle();
                bundle.putString("seltype", "modify");
                startActivity(NewAddressAty.class, bundle);
                break;
        }
    }
}
