package com.mini.homeworks.MyAssign;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mini.homeworks.net.bean.AssignmentBean;
import com.mini.homeworks.R;
import com.mini.homeworks.Utils.GetDate;

import java.time.Instant;
import java.util.List;



public class AssignAdapter extends RecyclerView.Adapter<AssignAdapter.MyViewHolder> {

    private Context mContext;
    private List<AssignmentBean> data;
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

    public AssignAdapter(List<AssignmentBean> mDates){
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
    public void onBindViewHolder (final AssignAdapter.MyViewHolder holder, final int position) {
        final SparseIntArray color_back = new SparseIntArray();
        color_back.put( Color.parseColor("#933DA9"), R.drawable.rounded_rectangle_933da9 );
        color_back.put( Color.parseColor("#3F51B5"), R.drawable.rounded_rectangle_3f51b5 );
        color_back.put( Color.parseColor("#039BE5"), R.drawable.rounded_rectangle_039be5 );
        color_back.put( Color.parseColor("#F37047"), R.drawable.rounded_rectangle_f37047 );
        color_back.put( Color.parseColor("#4DB6AC"), R.drawable.rounded_rectangle_4db6ac );
        color_back.put( Color.parseColor("#E59089"), R.drawable.rounded_rectangle_e59089 );
        color_back.put( Color.parseColor("#DB4437"), R.drawable.rounded_rectangle_db4437 );
        color_back.put( Color.parseColor("#BCBCBC"), R.drawable.rounded_rectangle_bcbcbc );
        color_back.put( Color.parseColor("#8490C8"), R.drawable.rounded_rectangle_8490c8 );
        color_back.put( Color.parseColor("#F6BF26"), R.drawable.rounded_rectangle_f6bf26 );
        color_back.put( Color.parseColor("#CD3278"), R.drawable.rounded_rectangle_cd3278 );
        color_back.put( Color.parseColor("#4CB684"), R.drawable.rounded_rectangle_4cb684 );

        final AssignmentBean dataBean = data.get(position);
        String begintime = GetDate.TimeStampToDate(""+dataBean.getBeginTime(), "yyyy-MM-dd HH:mm:ss").substring(0,10);
        String endtime = GetDate.TimeStampToDate(""+dataBean.getEndTime(), "yyyy-MM-dd HH:mm:ss").substring(0,10);
        holder.tv_begintime.setText("开始时间："+GetDate.DateToWeek(begintime)+" "+begintime);
        holder.tv_endtime.setText("截止时间："+GetDate.DateToWeek(endtime)+" "+endtime);
        holder.tv_assignName.setText(dataBean.getAssignName());
        holder.ll_myassign_item_in.setBackgroundResource(color_back.get(dataBean.getColor()));
        if(dataBean.getType() == 1) {
            holder.iv_promotion.setVisibility(View.INVISIBLE);
            holder.iv_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.iv_menu.setImageResource(R.drawable.pull_up);
                    ShowPopupMenu.showPopupMenu(v,dataBean,mContext);
                }
            });
        } else {
            holder.iv_promotion.setVisibility(View.VISIBLE);
            holder.iv_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.iv_menu.setImageResource(R.drawable.pull_up);
                    ShowPopupMenu.showPopupMenu(v,dataBean,mContext);
                }
            });
        }
        long now = Instant.now().getEpochSecond()*1000;
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

        //找到控制菜单
        ShowPopupMenu showPopupMenu = new ShowPopupMenu();

        showPopupMenu.setOperationListener(new ShowPopupMenu.OperationListener() {
            @Override
            public void onOperationListener(int type, int color) {
                switch (type) {
                    case 0 : { // 0：顶置变色（color = 变化的颜色，若无变化color = -1）
                        if( color != -1 ) {
                            holder.tv_endtime.setTextColor(color);
                            holder.tv_begintime.setTextColor(color);
                            holder.ll_myassign_item_in.setBackgroundResource(color_back.get(color));
                        }
                        break;
                    }
                    case 1 : { // 1：普通变色（color = 变化的颜色，若无变化color = -1）
                        if( color != -1 ) {
                            holder.tv_endtime.setTextColor(color);
                            holder.tv_begintime.setTextColor(color);
                            holder.ll_myassign_item_in.setBackgroundResource(color_back.get(color));
                        }
                        break;
                    }
                    case 2 : { // 2：删除顶置（color = -1）
                        notifyItemRemoved(position);
                        break;
                    }
                    case 3 : { // 3：删除普通（color = -1）
                        notifyItemRemoved(position);
                        break;
                    }
                    case 4 : { // 4：顶置（color = -1）
                        notifyItemRangeChanged(0,position);
                        break;
                    }
                    case 5 : { // 5：取消顶置（color = -1）
                        notifyItemRangeChanged(0,getItemCount());
                        break;
                    }
                }
            }
        });
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
        LinearLayout ll_myassign_item_in;

        public MyViewHolder(View view){
            super (view);
            ll_myassign_item_in = view.findViewById(R.id.ll_myassign_item_in);
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
        return data.get(position).getType();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}