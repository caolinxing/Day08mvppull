package com.example.day08mvppull.view;

import com.example.day08mvppull.pojo.GoodsBean;

import java.util.List;

public interface pulltorefrash_View {
    //获取数据
    void getData(List<GoodsBean.DataBean> list);
    //设置adapter
    void setAdapter();
    //下拉刷新
    void onPull();
    //上拉加载
    void onPush();
}
