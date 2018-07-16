package com.example.demo02.view;

import com.example.pojo.GoodsBean;

import java.util.List;

public interface IhomeView {

    void getDataSuccess(List<GoodsBean.DataBean> list);

    void getDataError(String error_code);
}
