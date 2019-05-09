package com.mini.homeworks.Notification;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.mini.homeworks.MySQLiteOpenHelper;
import com.mini.homeworks.R;
import com.mini.homeworks.net.RetrofitWrapper;
import com.mini.homeworks.net.Service.NotificationService;
import com.mini.homeworks.net.bean.NotificationBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private TextView textView_num;
    private TextView textView_day;
    private TextView textView_hour;
    private Context mContext;
    private List<NotificationBean.NoticeConfigGet.NoticeTimeListBean> noticeTimeLists;
    private int total;
    private String token;
    private MySQLiteOpenHelper openHelper;
    private OnItemClickListener onItemClickListener;


    //构造器
    public NotificationAdapter(NotificationBean.NoticeConfigGet noticeConfigGet) {
        this.total = noticeConfigGet.getTotal();
        noticeTimeLists = noticeConfigGet.getNoticeTimeList();
    }

    interface OnItemClickListener {
        //点击事件
        void onItemClick(View view, int position);

        //长按事件
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.notification_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.bind(noticeTimeLists.get(i));

        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(viewHolder.itemView,i);
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(viewHolder.itemView,i);
                    return true;
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return total;
    }

    public void removeItem(int pos){
        noticeTimeLists.remove(pos);
        notifyItemRemoved(pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_num;
        public TextView textView_day;
        public TextView textView_hour;
        public SwitchButton switchButton;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_day = itemView.findViewById(R.id.notification_day);
            textView_hour = itemView.findViewById(R.id.notification_hour);
            textView_num = itemView.findViewById(R.id.notification_num);
            switchButton = itemView.findViewById(R.id.switch_time);
        }

        public void bind(final NotificationBean.NoticeConfigGet.NoticeTimeListBean noticeTimeListBean) {
            int hour = noticeTimeListBean.getNoticeTime() % 24;
            int day = noticeTimeListBean.getNoticeTime() / 24;
            textView_num.setText(noticeTimeListBean.getNoticeTimeId());
            textView_hour.setText(hour);
            textView_day.setText(day);
            if (noticeTimeListBean.getNoticeTimeStatus() == 1) {
                switchButton.setChecked(true);
            } else {
                switchButton.setChecked(false);
            }

            //switchButton监听器
            switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //改变时间节点启用状态
                    NotificationService notificationService = RetrofitWrapper.getInstance().create(NotificationService.class);
                    Call<NotificationBean.ChangesStatusBean> changesStatusBeanCall = notificationService.getChangesStatusBean(noticeTimeListBean.getNoticeTimeId(), token);
                    changesStatusBeanCall.enqueue(new Callback<NotificationBean.ChangesStatusBean>() {
                        @Override
                        public void onResponse(Call<NotificationBean.ChangesStatusBean> call, Response<NotificationBean.ChangesStatusBean> response) {

                        }

                        @Override
                        public void onFailure(Call<NotificationBean.ChangesStatusBean> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
}
