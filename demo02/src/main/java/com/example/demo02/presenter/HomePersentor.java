package com.example.demo02.presenter;

import com.example.demo02.model.HomeModel;
import com.example.demo02.model.IhomeModel;
import com.example.demo02.view.IhomeView;
import com.example.pojo.GoodsBean;

import java.util.List;

public class HomePersentor implements IhomePersentor,IhomeModel.GetDataStateListener {

    private  IhomeView ihomeView;
    private  IhomeModel ihomeModel;
    public HomePersentor(IhomeView ihomeView) {
        this.ihomeView = ihomeView;
        this.ihomeModel = new HomeModel();
    }


    @Override
    public void GeetdataFromModel(String api, int page) {
        ihomeModel.setUrl(api,page,this);
    }

    @Override
    public void onDestorys() {
        if (ihomeView!=null){
            ihomeView=null;
        }
    }

    @Override
    public void onGetadataSuccess(List<GoodsBean.DataBean> list) {
        ihomeView.getDataSuccess(list);
    }

    @Override
    public void onGetadataError(String error) {
        ihomeView.getDataError(error);
    }
}
