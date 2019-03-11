package com.gangxiang.aiDaiOrder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public class MyFragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private List<Fragment> fragmentsList;


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragmentsList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
