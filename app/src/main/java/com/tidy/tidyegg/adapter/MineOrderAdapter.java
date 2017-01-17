package com.tidy.tidyegg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.COBaseAdapter;
import com.tidy.tidyegg.bean.MineOrderListBean;
import com.tidy.tidyegg.callback.ListViewItemButtonClickListener;
import com.tidy.tidyegg.widget.MyListview;

import java.util.List;

/**
 * Created by dou on 2016/10/11.
 * 我的订单的adapter
 */
public class MineOrderAdapter extends COBaseAdapter<MineOrderListBean> {

    private Context mContext;
    private List<MineOrderListBean> dataList;
    private int type;
    private ListViewItemButtonClickListener callBack;
    public MineOrderAdapter(Context mContext, List<MineOrderListBean> dataList,int type,ListViewItemButtonClickListener callBack){
        super(dataList);
        this.dataList = dataList;
        this.mContext = mContext;
        this.type = type;
        this.callBack = callBack;
    }

    @Override
    public View getView(int position, View convertView,final ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mineorder, parent, false);
            holder.tv_orderlist_type = (TextView) convertView.findViewById(R.id.tv_orderlist_type);
            holder.ll_list = (LinearLayout) convertView.findViewById(R.id.ll_list);
            holder.lv_orderlist = (MyListview) convertView.findViewById(R.id.lv_orderlist);
            holder.tv_orderlist_forpay = (TextView) convertView.findViewById(R.id.tv_orderlist_forpay);
            holder.ll_orderlist_btn = (LinearLayout) convertView.findViewById(R.id.ll_orderlist_btn);
            holder.tv_orderlist_btnleft = (TextView) convertView.findViewById(R.id.tv_orderlist_btnleft);
            holder.tv_orderlist_btnright = (TextView) convertView.findViewById(R.id.tv_orderlist_btnright);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        List<MineOrderListBean.Goods> goods = dataList.get(position).getGoods();
        holder.lv_orderlist.setFocusable(false);
        holder.lv_orderlist.setEnabled(false);
        holder.lv_orderlist.setPressed(false);
        holder.lv_orderlist.setClickable(false);
        OrderListGoodsAdapter goodsAdapter = new OrderListGoodsAdapter(mContext, goods);
        holder.lv_orderlist.setAdapter(goodsAdapter);

        holder.tv_orderlist_forpay.setText(mContext.getString(R.string.RMB)+dataList.get(position).getTopay());
        if (type == 1){
            holder.tv_orderlist_type.setText("待付款");
            holder.ll_orderlist_btn.setVisibility(View.VISIBLE);
            holder.tv_orderlist_btnleft.setText("取消订单");
            holder.tv_orderlist_btnright.setText("立即支付");
        }else if (type == 2){
            holder.tv_orderlist_type.setText("待发货");
            holder.ll_orderlist_btn.setVisibility(View.GONE);
        }else if (type == 3){
            holder.tv_orderlist_type.setText("待收货");
            holder.ll_orderlist_btn.setVisibility(View.VISIBLE);
            holder.tv_orderlist_btnleft.setText("查看物流");
            holder.tv_orderlist_btnright.setText("确认收货");
        }else if (type == 4){
            holder.tv_orderlist_type.setText("待评价");
            holder.ll_orderlist_btn.setVisibility(View.VISIBLE);
            holder.tv_orderlist_btnleft.setVisibility(View.INVISIBLE);
            holder.tv_orderlist_btnright.setText("我要评价");
        }

        final View view = convertView;
        final int p = position;
        final int left = holder.tv_orderlist_btnleft.getId();
        final int right = holder.tv_orderlist_btnright.getId();
        final int id = holder.ll_list.getId();
        holder.tv_orderlist_btnleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onClick(view, parent, p, left);
            }
        });

        holder.tv_orderlist_btnright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onClick(view, parent, p, right);
            }
        });

        holder.ll_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onClick(view, parent, p, id);
            }
        });
        return convertView;
    }

    static final class ViewHolder {
        TextView tv_orderlist_type;//列表类型
        LinearLayout ll_list;
        MyListview lv_orderlist;//商品显示的list
        TextView tv_orderlist_forpay;//实际支付
        LinearLayout ll_orderlist_btn;//按钮显示
        TextView tv_orderlist_btnleft;//左边按钮
        TextView tv_orderlist_btnright;//右边按钮
    }
}
