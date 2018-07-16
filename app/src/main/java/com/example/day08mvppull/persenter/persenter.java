package com.example.day08mvppull.persenter;

import android.util.Log;

import com.example.day08mvppull.model.interface_pulltorefresh_model;
import com.example.day08mvppull.model.pulltorefresh_model;
import com.example.day08mvppull.pojo.GoodsBean;
import com.example.day08mvppull.view.pulltorefrash_View;

import java.util.List;

public class persenter implements inteface_persenter,interface_pulltorefresh_model.SetData {

    private  pulltorefrash_View pull_view;
    private interface_pulltorefresh_model model;

    public persenter(pulltorefrash_View pull_view) {
        this.pull_view = pull_view;
        model = new pulltorefresh_model();
    }

    @Override
    public void getPage(int page) {
        model.onGetdata(page,this);
    }

    @Override
    public void onDestorys() {
        pull_view=null;
    }

    @Override
    public void onGetdata(List<GoodsBean.DataBean> list) {
        Log.i("aaa",list.toString());
        pull_view.getData(list);
    }
}
