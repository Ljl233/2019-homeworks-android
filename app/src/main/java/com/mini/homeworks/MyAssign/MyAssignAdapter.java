package com.mini.homeworks.MyAssign;

import android.animation.ValueAnimator;
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



public class MyAssignAdapter extends RecyclerView.Adapter<MyAssignAdapter.MyViewHolder> {

    private Context mContext;
    private List<AssignmentBean> data;

    private MyAssignAdapter.OnItemClickListener onRecyclerViewItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnRecyclerViewItemClickListener(MyAssignAdapter.OnItemClickListener onItemClickListener) {
        this.onRecyclerViewItemClickListener = onItemClickListener;
    }

    public MyAssignAdapter(List<AssignmentBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyAssignAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        mContext = parent.getContext();
        View view = View.inflate(parent.getContext(),R.layout.my_assignment_item,null);
        return new MyAssignAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder (final MyAssignAdapter.MyViewHolder holder, final int position) {
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
        holder.tv_endtime.setTextColor(color_back.get(dataBean.getColor()));
        holder.tv_begintime.setTextColor(color_back.get(dataBean.getColor()));
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
        if ( now > dataBean.getEndTime() && ( dataBean.getStatus() == 0 || dataBean.getStatus() == 2 ) ) {
            holder.iv_status.setImageResource(R.drawable.cross);
            holder.tv_status.setText("已逾期");
        } else if ( dataBean.getStatus() == 1 || dataBean.getStatus() == 3 ) {
            holder.iv_status.setImageResource(R.drawable.tick);
            holder.tv_status.setText("已完成");
        } else if ( now < dataBean.getEndTime() && ( dataBean.getStatus() == 0 || dataBean.getStatus() == 2 ) ) {
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
                            listener.onUpdateListener();
                        }
                        break;
                    }
                    case 1 : { // 1：普通变色（color = 变化的颜色，若无变化color = -1）
                        if( color != -1 ) {
                            holder.tv_endtime.setTextColor(color);
                            holder.tv_begintime.setTextColor(color);
                            holder.ll_myassign_item_in.setBackgroundResource(color_back.get(color));
                            listener.onUpdateListener();
                        }
                        break;
                    }
                    case 2 : { // 2：删除顶置（color = -1）
                        listener.onUpdateListener();
                        break;
                    }
                    case 3 : { // 3：删除普通（color = -1）
                        listener.onUpdateListener();
                        break;
                    }
                    case 4 : { // 4：顶置（color = -1）
                        listener.onUpdateListener();
                        break;
                    }
                    case 5 : { // 5：取消顶置（color = -1）
                        listener.onUpdateListener();
                        break;
                    }
                }
            }
        });
        if (onRecyclerViewItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerViewItemClickListener.onClick(position);
                }
            });
        }

    }

    private UpdateListener listener;//更新列表

    public interface UpdateListener {
        void onUpdateListener();
    }

    public void setOnUpdateListener(UpdateListener listener) {
        this.listener = listener;
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