package com.tidy.tidyegg.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tidy.tidyegg.utils.ValueUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiang on 2016/10/8.
 * Adapter的基类
 */
public abstract class COBaseAdapter<T> extends BaseAdapter {
    public List<T> dataList;

    public COBaseAdapter(List<T> dataList) {
        if (ValueUtils.isNotEmpty(dataList)) {
            this.dataList = dataList;
        } else {
            this.dataList = new ArrayList<T>();
        }
    }

    public int getCount() {
        return dataList.size();
    }

    public Object getItem(int position) {
        return dataList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public abstract View getView(int position, View convertView, ViewGroup parent);

    public void notifyDataSetChanged(List<T> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> list) {
        dataList = list;
    }

    public T getData(int index) {
        return dataList.get(index);
    }

}
