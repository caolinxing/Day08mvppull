package com.example.demo02.zdy_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.demo02.R;

public class Scearch_view extends RelativeLayout{
    private  TextView title_tv;
    private ImageView left_btn;
    private ImageView right_search;

    public Scearch_view(Context context) {
        super(context);
        //初始化
        init(context);
    }

    public Scearch_view(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化
        init(context);
    }

    public Scearch_view(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化
        init(context);
    }

    public Scearch_view(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context) {
        // 加载布局
        //LayoutInflater.from(context).inflate(R.layout.zidingyi_layout, this);
        View.inflate(context, R.layout.zidingyi_layout,this);
        title_tv = findViewById(R.id.edi_sea);
        left_btn= findViewById(R.id.left_btn);
        right_search= findViewById(R.id.right_search);
    }
    public void setBackOnClickListener(OnClickListener clickListener){
        left_btn.setOnClickListener(clickListener);
    }
    public void setSearchOnClickListener(OnClickListener clickListener){
        right_search.setOnClickListener(clickListener);
    }
    public void setTitleContent(String title){
        title_tv.setText(title);
    }

}
