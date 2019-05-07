package com.mini.homeworks.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class VPAdapter extends FragmentStatePagerAdapter {
    private int nNumOfTabs;
    private CourseFragment tab1 = null;
    private TaskFragment tab2 = null;

    public VPAdapter(FragmentManager fm, int nNumOfTabs) {
        super(fm);
        tab1 = new CourseFragment();
        tab2 = new TaskFragment();
        this.nNumOfTabs = nNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = tab1;
                break;
            case 1:
                fragment = tab2;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return nNumOfTabs;
    }
}