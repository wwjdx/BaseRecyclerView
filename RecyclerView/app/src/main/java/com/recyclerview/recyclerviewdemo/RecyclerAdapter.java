package com.diit.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private Context mContext;//上下文
    private List<String> stringList;//数据集
    private OnItemClickLis onItemClickLis;//点击/长按监听

    public RecyclerAdapter(Context mContext, List<String> stringList) {
        this.mContext = mContext;
        this.stringList = stringList;
    }
    public void removeData(int pos){
        stringList.remove(pos);
        notifyItemRemoved(pos);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_rv_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.tv.setText(stringList.get(i));
        if (onItemClickLis!=null){
           myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int pos=myViewHolder.getLayoutPosition();
                   onItemClickLis.onItemClickLis(v, pos);
               }
           });
           myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   int pos=myViewHolder.getLayoutPosition();
                   onItemClickLis.onItemLongClickLis(v, pos);
                   return false;
               }
           });
        }
    }
    @Override
    public int getItemCount() {
        return stringList!=null?stringList.size():0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHolder(View v) {
            super(v);
            tv = v.findViewById(R.id.tv_item);
        }
    }
    public void setOnItemClickLis(OnItemClickLis onItemClickLis) {
        this.onItemClickLis = onItemClickLis;
    }

    public interface OnItemClickLis {
        void onItemClickLis(View view,int pos);
        void onItemLongClickLis(View view,int pos);
    }
}
