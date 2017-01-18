package com.tidy.tidyegg.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * Created by dou on 2016/10/13.
 * 新增地址和修改地址的公共类
 */
public class NewAddressAty extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.head_newaddress)
    RelativeLayout head_newaddress;

    @BindView(R.id.ed_newaddress_name)
    EditText ed_name;

    @BindView(R.id.ed_newaddress_number)
    EditText ed_number;

    @BindView(R.id.tv_select_address)
    TextView tv_address;

    @BindView(R.id.tv_provinces)
    TextView tv_provinces;

    @BindView(R.id.tv_city)
    TextView tv_city;

    @BindView(R.id.tv_county)
    TextView tv_county;

    @BindView(R.id.ed_street)
    EditText ed_street;

    @BindView(R.id.cb_moren)
    CheckBox cb_moren;

    @BindView(R.id.ll_newaddress_save)
    LinearLayout ll_save;

    @BindView(R.id.btn_newaddress_save)
    Button btn_save;
    private String seltype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getBundle();
        if (bundle != null){
            seltype = bundle.getString("seltype");
        }
        showView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_newaddress;
    }

    public void showView(){
        if (seltype.equals("add")){
            this.setTitle(head_newaddress,"添加新收货地址");
        }else if (seltype.equals("modify")){
            this.setTitle(head_newaddress,"修改收货地址");
        }
        this.setLeft(head_newaddress);
        tv_address.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_select_address:
//                try {
//                    ArrayList<AddressPicker.Province> data = new ArrayList<AddressPicker.Province>();
//                    String json = ConvertUtils.toString(getAssets().open("city.json"));
//                    data.addAll(JSON.parseArray(json, AddressPicker.Province.class));
//                    AddressPicker picker = new AddressPicker(this, data);
//                    picker.setSelectedItem("上海", "上海", "长宁");
//                    picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
//                        @Override
//                        public void onAddressPicked(AddressPicker.Province province, AddressPicker.City city, AddressPicker.County county) {
//                            tv_provinces.setText("省份:"+province.getAreaName());
//                            tv_city.setText("城市:"+city.getAreaName());
//                            tv_county.setText("区县:"+county.getAreaName());
//                        }
//                    });
//                    picker.show();
//                } catch (Exception e) {
//                    toast(e.toString());
//                }
                break;
            default:
                break;
        }
    }
}
