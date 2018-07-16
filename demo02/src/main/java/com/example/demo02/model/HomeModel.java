package com.example.demo02.model;

import android.util.Log;

import com.example.pojo.GoodsBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeModel implements IhomeModel {

    private OkHttpClient okHttpClient;

    @Override
    public void setUrl(String api, int page, final GetDataStateListener dataStateListener) {
        //1.初始化okhttp对象
        okHttpClient = new OkHttpClient();
        //2.获取requestBody
        FormBody requestBody = new FormBody
                .Builder()
                .add("pscid", String.valueOf(page))
                .build();
        //3.获取请求对象并且把requestBody放进
        final Request request = new Request
                .Builder()
                .url(api)
                .post(requestBody)
                .build();
        //4 okHttpClient发起请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            public final String TAG = "HomeModel";

            @Override
            public void onFailure(Call call, IOException e) {
                dataStateListener.onGetadataError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               final String str =  response.body().string();
                //5.获取response对象
                Log.i(TAG, "onResponse: "+str);
                //gson解析数据
                Gson gson = new Gson();
                GoodsBean goodsBean = gson.fromJson(str, GoodsBean.class);
                dataStateListener.onGetadataSuccess(goodsBean.getData());
            }
        });

    }
}
