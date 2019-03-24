package com.mini.homeworks.MainActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mini.homeworks.R;

import java.util.List;

public class TaskButtonAdapter extends RecyclerView.Adapter<TaskButtonAdapter.ViewHolder> {
    private List<String> mTaskButtonList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View taskView;
        Button btn_task_view;

        public ViewHolder(View view) {
            super(view);
            taskView = view;
            btn_task_view = view.findViewById(R.id.btn_task_view);
        }
    }

    public TaskButtonAdapter (List<String> ButtonList) {
        mTaskButtonList = ButtonList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_view_button_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        /*
        holder.taskView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String str = mTaskButtonList.get(position);

            }
        });
        holder.btn_task_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String str = mTaskButtonList.get(position);
            }
        });
        */
        //view.setOnClickListener(this);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mTaskButtonList.size();
    }

    @Override
    public void onBindViewHolder(TaskButtonAdapter.ViewHolder holder, final int position) {
        //绑定数据优化并强转
        //      TaskButtonAdapter.ViewHolder viewHolder = holder;
        //重点在这里判空然后做接口的绑定
    /*    if (onRecyclerViewItemClickListener!=null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerViewItemClickListener.onClick(position);
                }
            });
        }
      */
        String str = mTaskButtonList.get(position);
        holder.btn_task_view.setText(str);
    }


    //private OnItemClickListener onRecyclerViewItemClickListener;

    // public interface OnItemClickListener {
    //    void onClick(int position);
    //  }

    //  public void setOnRecyclerViewItemClickListener(TaskButtonAdapter.OnItemClickListener onItemClickListener) {
    //    this.onRecyclerViewItemClickListener = onItemClickListener;
    //}
}
