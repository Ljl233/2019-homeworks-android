package com.mini.homeworks.Search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mini.homeworks.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter {
    private SearchBean searchBean;

    public SearchAdapter(SearchBean searchBean) {
        this.searchBean = searchBean;
    }

    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_1;
        public TextView textView_2;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_1= itemView.findViewById(R.id.search_course_name);
            textView_2= itemView.findViewById(R.id.search_task_name);
        }

    }
//    @Override
//    public int getCount() {
//        int count = 0;
//        if(searchBean.getAssignData() != null)count+=
//
//        return count;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
//    }


}
