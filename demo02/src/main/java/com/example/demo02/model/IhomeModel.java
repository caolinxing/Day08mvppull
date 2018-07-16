package com.example.demo02.model;

import com.example.pojo.GoodsBean;

import java.util.List;

/**
 * 数据层
 * @author  少年维特
 * @version 1.0
 * @date 2018/7/13 21:05
 */
public interface IhomeModel {
    //1.监听获取数据的状态
    interface GetDataStateListener{
        //成功
        void onGetadataSuccess(List<GoodsBean.DataBean> list);
        //失败
        void onGetadataError(String error);

    }
    //2.获取网址，解析数据
    void setUrl(String api,int page,GetDataStateListener dataStateListener);
}
