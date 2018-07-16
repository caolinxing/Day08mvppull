package com.example.demo02.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.demo02.R;
import com.example.demo02.adapter.MyAdater;
import com.example.demo02.presenter.HomePersentor;
import com.example.demo02.zdy_view.Scearch_view;
import com.example.pojo.GoodsBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IhomeView {

    private PullToRefreshScrollView scrollView;
    private HomePersentor ihomePersentor;
    private String api = "https://www.zhaoapi.cn/product/getProducts";
    private int page = 1;
    private List<GoodsBean.DataBean> goodList = new ArrayList<>();
    private MyAdater adapter;
    private RecyclerView recycler_view;
    private String TAG = "main";
    private Scearch_view zdy_view;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //持有perenter
        ihomePersentor = new HomePersentor(this);
        //创建适配器
        adapter = new MyAdater(this,goodList,onRecyclerviewItemClickListener);
        recycler_view.setAdapter(adapter);
        //获取页面数据数据
        LoadPageData(api,page);
        //刷新加载监听
        setPullListener();
        //搜索
        zdy_view.setBackOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zdy_view.setSearchOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了搜索按钮", Toast.LENGTH_SHORT).show();
            }
        });
        zdy_view.setTitleContent("代码 ko");
    }
    private MyAdater.OnRecyclerviewItemClickListener onRecyclerviewItemClickListener = new MyAdater.OnRecyclerviewItemClickListener() {
        @Override
        public void onItemClickListener(View v, int position) {
            //这里的view就是我们点击的view  position就是点击的position
            Intent intent =  new Intent(MainActivity.this,WebViewActivity.class);
            intent.putExtra("url",goodList.get(position).getDetailUrl());
            startActivity(intent);
        }
    };
    //1.初始化控件
    private void initView() {
        scrollView = (PullToRefreshScrollView) findViewById(R.id.pull_listView);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        zdy_view = findViewById(R.id.zdy_view);
    }
    
    //2.刷新加载监听
    private void setPullListener() {
        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                if (goodList!=null){
                    goodList.clear();
                }
                LoadPageData(api,1);
                adapter.notifyDataSetChanged();
                scrollView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                if (page<2){
                    page++;
                }
                LoadPageData(api,page);
                scrollView.onRefreshComplete();
            }
        });
    }
    //4.设置请求url
    private void LoadPageData(String api, int page) {
        ihomePersentor.GeetdataFromModel(api, page);
    }

    //5.成功
    @Override
    public void getDataSuccess(List<GoodsBean.DataBean> list) {
        goodList.addAll(list);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    //6.失败
    @Override
    public void getDataError(String error_code) {
        Log.i(TAG, "getDataError: "+error_code);
    }

    //7.解绑
    @Override
    protected void onDestroy() {
        ihomePersentor.onDestorys();
        super.onDestroy();
    }
}
