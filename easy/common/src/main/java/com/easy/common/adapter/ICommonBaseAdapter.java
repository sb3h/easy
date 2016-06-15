package com.easy.common.adapter;

public interface ICommonBaseAdapter<T> {

    void createConvertView(ViewHolder holder, T data, int position);

}
