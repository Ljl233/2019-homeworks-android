package com.mini.homeworks.MainActivity;

import com.mini.homeworks.net.bean.TasksBean;

import java.util.Comparator;

public class TaskBeginComparator implements Comparator<TasksBean.AssignListBean> {
    @Override
    public int compare(TasksBean.AssignListBean o1, TasksBean.AssignListBean o2) {
        if ( o1.getBeginTime() > o2.getBeginTime() )
            return -1;
        else return 1;
    }
}