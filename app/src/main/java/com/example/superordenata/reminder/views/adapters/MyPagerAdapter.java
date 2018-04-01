package com.example.superordenata.reminder.views.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.superordenata.reminder.views.fragments.GroupFragment;
import com.example.superordenata.reminder.views.fragments.RecyclerViewNoteFragment;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;
    private Fragment currentFragment;

    public MyPagerAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.numberOfTabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                currentFragment = new RecyclerViewNoteFragment();
                return currentFragment;
            case 1:
                currentFragment = new GroupFragment();
                return currentFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}