package com.njz.letsgoappguides.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.njz.letsgoappguides.base.BaseFragment;

import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    //    这个是viewpager的填充视图
    private List<BaseFragment> fragments;

    public FragmentAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int id) {
        return fragments.get(id);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
