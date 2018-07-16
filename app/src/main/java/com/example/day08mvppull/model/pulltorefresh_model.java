package com.example.day08mvppull.model;

import android.util.Log;

import com.example.day08mvppull.pojo.GoodsBean;
import com.example.day08mvppull.utils.HttpUtils;
import com.google.gson.Gson;

public class pulltorefresh_model implements interface_pulltorefresh_model {
    String api = "http://www.zhaoapi.cn/product/getProducts?pscid=";
    private GoodsBean goodsBean;

    @Override
    public void onGetdata(int page, final SetData setData) {
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.getUrl(api+page, new HttpUtils.HttpGetData() {
            @Override
            public void onSuccess(String data) {
                Log.i("ss",data);
                Gson gson = new Gson();
                goodsBean = gson.fromJson(data,GoodsBean.class);
                setData.onGetdata(goodsBean.getData());
            }
        });
    }
}
