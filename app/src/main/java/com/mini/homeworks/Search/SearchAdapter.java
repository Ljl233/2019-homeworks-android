package com.mini.homeworks.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mini.homeworks.R;

import java.util.List;

public class SearchAdapter extends ArrayAdapter<SearchBean> {
    private int resourceId;
//构造器
    public SearchAdapter(Context context, int textViewResourceId, List<SearchBean> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchBean searchBean= getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView courseName = view.findViewById(R.id.search_course_name);
        TextView assignName = view.findViewById(R.id.search_task_name);
   //     courseName.setText(searchBean.getCourseData());
     //   assignName.setText(searchBean.getAssignData());

        return view;
    }

}
