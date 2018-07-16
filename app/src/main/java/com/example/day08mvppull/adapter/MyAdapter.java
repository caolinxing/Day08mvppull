package com.example.day08mvppull.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day08mvppull.R;
import com.example.day08mvppull.pojo.GoodsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends BaseAdapter{
    private Context context;
    private List<GoodsBean.DataBean> list;

    public MyAdapter(Context context, List<GoodsBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView==null){
            convertView = View.inflate(context, R.layout.item_bean,null);
            holder = new ViewHolder();
            holder.tilte = convertView.findViewById(R.id.tv_title);
            holder.bargain_price = convertView.findViewById(R.id.tv_delPrice);
            holder.price = convertView.findViewById(R.id.tv_Price);
            holder.img = convertView.findViewById(R.id.iv_img);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        Picasso.get().load("http://m.360buyimg.com//n0//jfs//t7441//10//64242474//419246//adb30a7d//598e95fbNd989ba0a.jpg!q70.jpg").into(holder.img);
        holder.price.setText(list.get(position).getPrice()+"");
        holder.bargain_price.setText(list.get(position).getBargainPrice()+"");
        if (!TextUtils.isEmpty(list.get(position).getTitle())){
            holder.tilte.setText(list.get(position).getTitle());
        }
        return convertView;
    }
    class ViewHolder{
        TextView bargain_price;
        TextView price;
        TextView tilte;
        ImageView img;
    }
}
