package com.mini.homeworks.MyAssign;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mini.homeworks.R;
import com.mini.homeworks.Utils.GetDate;

import java.time.Instant;
import java.util.List;



public class AssignAdapter extends RecyclerView.Adapter<AssignAdapter.MyViewHolder> {

    private Context mContext;
    private List<AssignBean> data;
    private AssignAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(AssignAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private AssignAdapter.OnItemClickListener onRecyclerViewItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnRecyclerViewItemClickListener(AssignAdapter.OnItemClickListener onItemClickListener) {
        this.onRecyclerViewItemClickListener = onItemClickListener;
    }

    public AssignAdapter(List<AssignBean> mDates){
        this.data = data;
    }

    @NonNull
    @Override
    public AssignAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if ( viewType == 0 ) {
            View view = View.inflate(mContext,R.layout.my_assignment_item,null);
            return new AssignAdapter.MyViewHolder(view);
        } else {
            View view = View.inflate(mContext, R.layout.my_assignment_item, null);
            return new AssignAdapter.MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder (final AssignAdapter.MyViewHolder holder, final int position){
        AssignBean dataBean = data.get(position);
        String begintime = GetDate.TimeStampToDate(""+dataBean.getBeginTime(), "yyyy-MM-dd HH:mm:ss").substring(0,9);
        String endtime = GetDate.TimeStampToDate(""+dataBean.getEndTime(), "yyyy-MM-dd HH:mm:ss").substring(0,9);
        holder.tv_begintime.setText(begintime+" "+GetDate.DateToWeek(begintime));
        holder.tv_endtime.setText(endtime+" "+GetDate.DateToWeek(endtime));
        holder.tv_assignName.setText(dataBean.getAssignName());
        if(dataBean.getDatatype()==1) {
            holder.iv_promotion.setVisibility(View.INVISIBLE);
            holder.iv_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.iv_menu.setImageResource(R.drawable.pull_up);
                    ShowPopupMenu.showPopupMenu(v,1,mContext);
                }
            });
        } else {
            holder.iv_promotion.setVisibility(View.VISIBLE);
            holder.iv_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.iv_menu.setImageResource(R.drawable.pull_up);
                    ShowPopupMenu.showPopupMenu(v,0,mContext);
                }
            });
        }
        long now = Instant.now().getEpochSecond();
        if ( now > dataBean.getEndTime() ) {
            holder.iv_status.setImageResource(R.drawable.cross);
            holder.tv_status.setText("已逾期");
        } else if ( dataBean.getStatus() == 1 || dataBean.getStatus() == 3 ) {
            holder.iv_status.setImageResource(R.drawable.tick);
            holder.tv_status.setText("已完成");
        } else if ( dataBean.getStatus() == 0 || dataBean.getStatus() == 2 ) {
            holder.iv_status.setImageResource(R.drawable.circle);
            holder.tv_status.setText("进行中");
        }
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_endtime;
        TextView tv_begintime;
        TextView tv_assignName;
        TextView tv_status;
        ImageView iv_status;
        ImageView iv_promotion;
        ImageView iv_menu;

        public MyViewHolder(View view){
            super (view);
            iv_menu = view.findViewById(R.id.iv_menu);
            tv_status = view.findViewById(R.id.tv_myassign_status);
            iv_status = view.findViewById(R.id.iv_myassign_stutas);
            iv_promotion = view.findViewById(R.id.iv_myassign_promotion);
            tv_endtime = view.findViewById(R.id.tv_myassign_endtime);
            tv_assignName = view.findViewById(R.id.tv_myassign_name);
            tv_begintime = view.findViewById(R.id.tv_myassign_begintime);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getDatatype();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

}
