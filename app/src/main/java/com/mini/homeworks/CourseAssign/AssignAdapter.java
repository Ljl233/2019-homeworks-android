package com.mini.homeworks.CourseAssign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mini.homeworks.R;

import java.util.List;

public class AssignAdapter extends RecyclerView.Adapter<AssignAdapter.MyViewHolder> {

    private Context mContext;
    private List<TasksBean.DataBean> mDates;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    public AssignAdapter(List<TasksBean.DataBean> mDates){

        this.mDates = mDates;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = View.inflate(mContext, R.layout.tasks_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder, final int position){
        TasksBean.DataBean dataBean = mDates.get(position);
        holder.tv_begin.setText(dataBean.getBeginTime());
        holder.tv_ddl.setText(dataBean.getEndTime());
        holder.tv_assignName.setText(dataBean.getAssignName());
    }
    @Override
    public int getItemCount(){
        return mDates.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_ddl;
        TextView tv_begin;
        TextView tv_assignName;
        RelativeLayout itemLayout;

        public MyViewHolder(View view){
            super (view);
            tv_ddl = view.findViewById(R.id.tv_ddl);
            tv_assignName = view.findViewById(R.id.tv_assignName);
            tv_begin = view.findViewById(R.id.tv_begin);

            itemLayout = view.findViewById(R.id.tasks_item);
        }
    }
}