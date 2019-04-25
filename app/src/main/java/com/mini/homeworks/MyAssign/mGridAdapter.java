package com.mini.homeworks.MyAssign;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mini.homeworks.R;

import java.util.List;

public class mGridAdapter extends BaseAdapter {

    private Context context;
    private List<ImageView> ColorList;

    public mGridAdapter (Context context, List<ImageView> ColorList) {
        this.ColorList = ColorList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ColorList.size();
    }

    @Override
    public Object getItem(int position) {
        return ColorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.myassign_colorchange_dialog,null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.iv_myassgin_dialog_color);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageView imageView = ColorList.get(position);
        return convertView;

    }

    public class ViewHolder {
        ImageView imageView;
    }
}
