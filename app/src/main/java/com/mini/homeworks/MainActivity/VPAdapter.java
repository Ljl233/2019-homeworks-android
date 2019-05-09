package com.mini.homeworks.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class VPAdapter extends FragmentPagerAdapter {

    private CourseFragment courseFragment = null;
    private TaskFragment taskFragment = null;

    public VPAdapter(FragmentManager fm) {
        super(fm);
        courseFragment = new CourseFragment();
        taskFragment = new TaskFragment();
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = courseFragment;
                break;
            case 1:
                fragment = taskFragment;
                break;
        }
        return fragment;
    }
}