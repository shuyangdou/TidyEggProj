package com.tidy.tidyegg.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.tidy.tidyegg.callback.OnItemClickListener;
import com.tidy.tidyegg.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiang on 000010/10/10.
 * RecyclerView的Adapter封装
 */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> mDatas = new ArrayList<>();
    protected Context mContext;
    protected LayoutInflater inflater;
    protected OnItemClickListener mOnItemClickListener;

    protected int width;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public BaseRecyclerAdapter(List<T> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        width = SpUtils.getSharePreInt(mContext, "mobileInfo", "width");
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final T item = getItem(position);
        if (item != null)
            bindItemData(holder, item, position);
        setupOnItemClick(holder, position);
    }

    protected abstract void bindItemData(VH viewHolder, T data, int position);

    protected void setupOnItemClick(final VH viewHolder, final int position) {
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(viewHolder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public T getItem(int position) {
        position = Math.max(0, position);
        return mDatas.get(position);
    }

    public List<T> getDataList() {
        return mDatas;
    }

    public void addData(List<T> newItems) {
        if (newItems != null) {
            mDatas.addAll(newItems);
            notifyDataSetChanged();
        }
    }

    public void setDataList(List<T> lists) {
        mDatas = lists;
        notifyDataSetChanged();
    }

}
