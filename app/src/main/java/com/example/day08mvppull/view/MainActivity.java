package com.example.day08mvppull.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.day08mvppull.R;
import com.example.day08mvppull.adapter.MyAdapter;
import com.example.day08mvppull.persenter.inteface_persenter;
import com.example.day08mvppull.persenter.persenter;
import com.example.day08mvppull.pojo.GoodsBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements pulltorefrash_View {

    private PullToRefreshListView pull_listview;
    private inteface_persenter persenter;
    int page = 1;
    private List<GoodsBean.DataBean> goodsList= new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pull_listview = findViewById(R.id.pull_listView);
        //持有persenter
        persenter = new persenter(this);
        //设置适配器
        setAdapter();
        //初始化加载
        onPull();
        pull_listview.setMode(PullToRefreshBase.Mode.BOTH);
        pull_listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                onPull();
                pull_listview.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                onPush();
                pull_listview.onRefreshComplete();
            }
        });
    }


    @Override
    public void getData(List<GoodsBean.DataBean> list) {
        goodsList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setAdapter() {
        adapter = new MyAdapter(MainActivity.this,goodsList);
        pull_listview.setAdapter(adapter);
    }

    @Override
    public void onPull() {
        persenter.getPage(page);
    }

    @Override
    public void onPush() {
        if (page<3){
            page++;
        }
        persenter.getPage(page);
    }

    @Override
    protected void onDestroy() {
        persenter.onDestorys();
        super.onDestroy();
    }
}
