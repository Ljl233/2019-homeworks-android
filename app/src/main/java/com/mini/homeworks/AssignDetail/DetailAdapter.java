package com.mini.homeworks.AssignDetail;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mini.homeworks.R;
import com.mini.homeworks.net.bean.DetailBean;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends BaseAdapter {

    private TextView submitName;
    private List<DetailBean.SubmitAttachmentBean> submitAttachmentBeanList;

    public DetailAdapter(Context context, List<DetailBean.SubmitAttachmentBean> submitAttachmentBeanList) {
        this.submitAttachmentBeanList = submitAttachmentBeanList;

    }

    @Override
    public int getCount() {
        return submitAttachmentBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return submitAttachmentBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        //     if (convertView==null){
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false);
        submitName = view.findViewById(R.id.tv_submitAttachmentId);
//        }
//        else{
//            view = convertView;
//            viewHolder = (ViewHolder) view.getTag();
//        }
        Log.d("submitName", String.valueOf(submitName));
        if (submitAttachmentBeanList.get(position).getName() != null)
            submitName.setText(submitAttachmentBeanList.get(position).getName());
        else submitName.setText("无");
        return view;
    }
}
