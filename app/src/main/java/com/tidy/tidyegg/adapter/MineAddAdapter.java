package com.tidy.tidyegg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.COBaseAdapter;
import com.tidy.tidyegg.bean.MineAddBean;
import com.tidy.tidyegg.callback.ListViewItemButtonClickListener;

import java.util.List;

/**
 * Created by DOU on 2016/10/11.
 * 我的收货地址adapter
 */
public class MineAddAdapter extends COBaseAdapter<MineAddBean>{
    private Context mContext;
    private List<MineAddBean> dataList;
    private ListViewItemButtonClickListener callBack;
    public MineAddAdapter(Context mContext, List<MineAddBean> dataList,ListViewItemButtonClickListener callBack) {
        super(dataList);
        this.dataList = dataList;
        this.mContext = mContext;
        this.callBack = callBack;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mineadd, parent, false);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_item_mineaddname);
            holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_item_mineaddphone);
            holder.tv_add = (TextView) convertView.findViewById(R.id.tv_item_mineadd);
            holder.tv_moren = (TextView) convertView.findViewById(R.id.tv_item_mineaddmoren);
            holder.tv_addmodify = (LinearLayout) convertView.findViewById(R.id.tv_addmodify);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MineAddBean addBean = dataList.get(position);
        if (addBean!=null){
            holder.tv_name.setText(addBean.getName());
            holder.tv_phone.setText(addBean.getPhone());
            holder.tv_add.setText(addBean.getAdd());

            if (addBean.getMo().equals("yes")){
                holder.tv_moren.setVisibility(View.VISIBLE);
            }else{
                holder.tv_moren.setVisibility(View.INVISIBLE);
            }
        }

        final View view = convertView;
        final int p = position;
        final int id = holder.tv_addmodify.getId();
        holder.tv_addmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onClick(view, parent, p, id);
            }
        });
        return convertView;
    }

    static final class ViewHolder {
        TextView tv_name;//名字
        TextView tv_phone;//电话
        TextView tv_add;//地址
        TextView tv_moren;//是否默认
        LinearLayout tv_addmodify;//修改按钮
    }
}
