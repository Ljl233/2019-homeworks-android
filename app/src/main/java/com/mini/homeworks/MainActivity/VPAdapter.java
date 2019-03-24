package com.mini.homeworks.MainActivity;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class VPAdapter  extends PagerAdapter {

    private List<String> titleList;
    private List<View> viewList;
    public VPAdapter(List<String> titleList, List<View> viewList) {
        this.titleList = titleList;
        this.viewList = viewList;
    }
    @Override
    public int getCount() {
        return viewList.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}