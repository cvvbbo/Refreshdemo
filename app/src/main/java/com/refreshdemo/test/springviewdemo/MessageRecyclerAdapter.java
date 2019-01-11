package com.refreshdemo.test.springviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.refreshdemo.test.R;
import java.util.ArrayList;
import java.util.List;

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.MessageHolder> {
    public Context mContext;

    private boolean all_read=false;

    public boolean getAll_read() {
        return all_read;
    }

    List<MessageBean> datas1=new ArrayList<>();


    // 刷新或者头一次进来的时候
    public void setdatas(List<MessageBean> datas1){
        this.datas1 = datas1;
        notifyDataSetChanged();
    }


    //加载更多的逻辑
    public void adddatas(List<MessageBean> datas1){
        this.datas1.addAll(datas1);
        notifyDataSetChanged();

    }

    public void setAll_read(boolean all_read) {
        this.all_read = all_read;
    }

    public MessageRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_adapter_view, parent, false);
        return new MessageHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(final MessageHolder holder, int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int position = holder.getLayoutPosition();
//                    //设置已读取消小红点
//                    holder.badgeviewim.setVisibility(View.GONE);
//                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }

         holder.messagetitle.setText(datas1.get(position).getMesName());
        holder.content.setText(datas1.get(position).getMesContent()+position);


        // 0 是未查看
        if (datas1.get(position).getMark()!=0){
            holder.badgeviewim.setVisibility(View.GONE);
        }else {
            holder.badgeviewim.setVisibility(View.VISIBLE);

        }

        //开始的时候都是未读
        if (all_read){
            holder.badgeviewim.setVisibility(View.GONE);
        }
    }



    @Override
    public int getItemCount() {
        return datas1.size();
    }

    class MessageHolder extends RecyclerView.ViewHolder{

        public ImageView into_im;
        public ImageView badgeviewim;
        public TextView messagetitle;
        public TextView content;

        public MessageHolder(View itemView) {
            super(itemView);
            into_im = (ImageView) itemView.findViewById(R.id.into_im);
            badgeviewim = (ImageView) itemView.findViewById(R.id.badgeview);
            messagetitle = (TextView) itemView.findViewById(R.id.title1);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }


}
