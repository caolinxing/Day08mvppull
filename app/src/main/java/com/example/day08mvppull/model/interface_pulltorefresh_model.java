package com.example.day08mvppull.model;

import com.example.day08mvppull.pojo.GoodsBean;

import java.util.List;

public interface interface_pulltorefresh_model {
    interface SetData{
        void onGetdata(List<GoodsBean.DataBean> list);
    }
    void onGetdata(int page,SetData setData);

}
