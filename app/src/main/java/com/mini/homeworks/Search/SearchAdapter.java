package com.mini.homeworks.Search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mini.homeworks.R;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchAdapter extends RecyclerView.Adapter {
    private SearchBean searchBean;
    private String key;
    private Context mContext;
    int type1 = 1;
    int type2 = 2;
    int type3 = 3;

    public SearchAdapter(SearchBean searchBean, String key) {
        this.searchBean = searchBean;
        this.key = key;
    }

    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
    }

    //int viewType：item的类型，可以根据viewType来创建不同的ViewHolder，来加载不同的类型的item

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder VH, int position) {
        ViewHolder viewHolder = (ViewHolder) VH;
        List<SearchBean.AssignDataBean> mAssign = searchBean.getAssignData();
//设置颜色
        viewHolder.textView_1.setText(StringFormatUtil(mAssign.get(position).getAssignName(), key, R.color.colorAccent));
        viewHolder.textView_2.setText(StringFormatUtil(mAssign.get(position).getCourseName(), key, R.color.colorAccent));

    }

    //    @Override
//    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);

//        if (position <= searchBean.getAssignData().size()) {
//            return type1;
//        } else if (position > searchBean.getAssignData().size() && position <= searchBean.getContentData().size()) {
//            return type2;
//        } else return type3;
    }

    @Override
    public int getItemCount() {
        return searchBean.getAssignData().size() + searchBean.getContentData().size() + searchBean.getCourseData().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_1;
        public TextView textView_2;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_1 = itemView.findViewById(R.id.search_course_name);
            textView_2 = itemView.findViewById(R.id.search_task_name);
        }


    }

    public static SpannableString StringFormatUtil(String data, String key, int color) {
        SpannableString s = new SpannableString(data);
        if (data.contains(key) && !TextUtils.isEmpty(key)) {

            //正则表达式
            Pattern p = Pattern.compile(key);
            Matcher m = p.matcher(s);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                s.setSpan(new ForegroundColorSpan(color), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

            }

        }
        return s;
    }
}

