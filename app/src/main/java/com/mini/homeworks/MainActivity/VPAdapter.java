package com.mini.homeworks.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class VPAdapter extends FragmentStatePagerAdapter {
    int nNumOfTabs;
    public VPAdapter(FragmentManager fm,int nNumOfTabs) {
        super(fm);
        this.nNumOfTabs=nNumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                CourseFragment tab1 =new CourseFragment();
                return tab1;
            case 1:
                TaskFragment tab2 = new TaskFragment();
                return tab2;
        }
        return null;
    }

    @Override
    public int getCount() {
        return nNumOfTabs;
    }
}