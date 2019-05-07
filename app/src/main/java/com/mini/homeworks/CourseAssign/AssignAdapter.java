package com.mini.homeworks.CourseAssign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mini.homeworks.R;
import com.mini.homeworks.Utils.GetDate;

import java.time.Instant;
import java.util.List;

import static com.mini.homeworks.R.drawable.rounded_rectangle_bcbcbc;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder (MyViewHolder holder, final int position){
        TasksBean.DataBean dataBean = mDates.get(position);
        String begintime = GetDate.TimeStampToDate(""+dataBean.getBeginTime(), "yyyy-MM-dd HH:mm:ss").substring(0,9);
        String endtime = GetDate.TimeStampToDate(""+dataBean.getEndTime(), "yyyy-MM-dd HH:mm:ss").substring(0,9);
        holder.tv_begin.setText("开始时间："+begintime+" "+GetDate.DateToWeek(begintime));
        holder.tv_ddl.setText("截止时间："+endtime+" "+GetDate.DateToWeek(endtime));
        holder.tv_assignName.setText(dataBean.getAssignName());
        long now = Instant.now().getEpochSecond();
        if ( now > dataBean.getEndTime() ) {
            holder.itemLayout.setBackgroundResource(R.drawable.rounded_rectangle_bcbcbc);
            holder.iv_status.setImageResource(R.drawable.cross);
            holder.tv_status.setText("已逾期");
            holder.tv_ddl.setTextColor(Color.parseColor("#BCBCBC"));
        } else if ( dataBean.getStatus() == 1 || dataBean.getStatus() == 3 ) {
            holder.itemLayout.setBackgroundResource(R.drawable.rounded_rectangle_3f51b5);
            holder.iv_status.setImageResource(R.drawable.tick);
            holder.tv_status.setText("已完成");
            holder.tv_ddl.setTextColor(Color.parseColor("#3F51B5"));
        } else if ( dataBean.getStatus() == 0 || dataBean.getStatus() == 2 ) {
            holder.itemLayout.setBackgroundResource(R.drawable.rounded_rectangle_039be5);
            holder.iv_status.setImageResource(R.drawable.circle);
            holder.tv_status.setText("进行中");
            holder.tv_ddl.setTextColor(Color.parseColor("#039BE5"));
        }
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
        ImageView iv_status;
        TextView tv_status;

        public MyViewHolder(View view){
            super (view);
            iv_status = view.findViewById(R.id.iv_courseassign_stutas);
            tv_status = view.findViewById(R.id.tv_courseassign_status);
            tv_ddl = view.findViewById(R.id.tv_ddl);
            tv_assignName = view.findViewById(R.id.tv_assignName);
            tv_begin = view.findViewById(R.id.tv_begin);
            itemLayout = view.findViewById(R.id.tasks_item);
        }
    }
}