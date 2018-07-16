package com.example.demo02.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo02.R;
import com.example.demo02.view.MainActivity;
import com.example.pojo.GoodsBean;

import java.util.List;

public class MyAdater extends RecyclerView.Adapter<MyAdater.ViewHoder1> implements View.OnClickListener {

    private Context context;
    private List<GoodsBean.DataBean> list;
    //自定义点击监听接口
    private OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener = null;
    //将接口加入到构造函数中
    public MyAdater(Context context, List<GoodsBean.DataBean> list, OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener) {
        this.context = context;
        this.list = list;
        this.mOnRecyclerviewItemClickListener = mOnRecyclerviewItemClickListener;
    }

    @Override
    public ViewHoder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_bean,null,false);
        //这里可以拿到itemview对象，所以在这里点击监听
        view.setOnClickListener(this);
        ViewHoder1 holder = new ViewHoder1(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHoder1 holder, int position) {
        holder.tv_delPrice.setText(list.get(position).getBargainPrice()+"");
        holder.tv_Price.setText(list.get(position).getPrice());
        holder.tv_title.setText(list.get(position).getTitle());
        String imgpath = list.get(position).getImages().split("\\|")[0];
        Glide.with(context).load(imgpath).into(holder.img);
        ////给view设置tag以作为参数传递到监听回调方法中
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
       mOnRecyclerviewItemClickListener.onItemClickListener(v, (int) v.getTag());
    }

    public class ViewHoder1 extends RecyclerView.ViewHolder {
        
        private ImageView img;
        private TextView tv_title;
        private TextView tv_Price;
        private TextView tv_delPrice;

        public ViewHoder1(View view) {
            super(view);
            img = view.findViewById(R.id.iv_img);
            tv_delPrice = view.findViewById(R.id.tv_delPrice);
            tv_Price = view.findViewById(R.id.tv_Price);
            tv_title = view.findViewById(R.id.tv_title);
        }
    }

    public interface OnRecyclerviewItemClickListener {
        void onItemClickListener(View v, int position);
    }
}
