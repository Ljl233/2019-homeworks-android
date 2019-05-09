package com.mini.homeworks.MainActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mini.homeworks.R;
import com.mini.homeworks.Utils.GetDate;
import com.mini.homeworks.net.bean.TasksBean;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private Context mContext;
    private List<TasksBean.AssignListBean> mDates;
    private TaskAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(TaskAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener onRecyclerViewItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnRecyclerViewItemClickListener(OnItemClickListener onItemClickListener) {
        this.onRecyclerViewItemClickListener = onItemClickListener;
    }

    public TaskAdapter(List<TasksBean.AssignListBean> mDates){
        this.mDates = mDates;
    }

    @Override
    public TaskAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = View.inflate(mContext, R.layout.tasks_item, null);
        return new TaskAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder (TaskAdapter.MyViewHolder holder, final int position){
        TasksBean.AssignListBean dataBean = mDates.get(position);
        String begintime = GetDate.TimeStampToDate(""+dataBean.getBeginTime(), "yyyy-MM-dd HH:mm:ss").substring(0,9);
        String endtime = GetDate.TimeStampToDate(""+dataBean.getEndTime(), "yyyy-MM-dd HH:mm:ss").substring(0,9);
        holder.tv_begin.setText(begintime+" "+GetDate.DateToWeek(begintime));
        holder.tv_ddl.setText(endtime+" "+GetDate.DateToWeek(endtime));
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
