package com.mini.homeworks.MainActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mini.homeworks.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<Course> mCourseList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View courseView;
        ImageView iv_course_image;
        TextView tv_course_name;

        public void bind(Course course) {
            course.getImageId();
        }

        public ViewHolder(View view) {
            super(view);
            courseView = view;
            iv_course_image = view.findViewById(R.id.iv_course_image);
            tv_course_name = view.findViewById(R.id.tv_course_name);
        }
    }

    public CourseAdapter(List<Course> CourseList) {
        mCourseList = CourseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mCourseList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //绑定数据优化并强转
        //CourseAdapter.ViewHolder viewHolder = holder;
        //重点在这里判空然后做接口的绑定
        if (onRecyclerViewItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerViewItemClickListener.onClick(position);
                }
            });
        }
        Course course = mCourseList.get(position);
        holder.iv_course_image.setImageResource(course.getImageId());
        holder.tv_course_name.setText(course.getName());
        holder.bind(mCourseList.get(position));
    }


    private OnItemClickListener onRecyclerViewItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnRecyclerViewItemClickListener(OnItemClickListener onItemClickListener) {
        this.onRecyclerViewItemClickListener = onItemClickListener;
    }
}
