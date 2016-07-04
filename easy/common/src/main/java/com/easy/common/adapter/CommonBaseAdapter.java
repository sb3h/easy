package com.easy.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class CommonBaseAdapter<T> extends BaseAdapter implements ICommonBaseAdapter<T> {

    private Context mContext;
    private ArrayList<T> mDataList;
    private int mLayoutId;

    public CommonBaseAdapter(Context context, ArrayList<T> dataList,
                             int layoutId) {
        mContext = context;
        mDataList = dataList;
        mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.getInstance(mContext, mLayoutId, convertView);
        T data = mDataList.get(position);
        createConvertView(holder, data, position);
        return holder.getConvertView();
    }

}
