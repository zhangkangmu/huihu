package com.huihu.uilib.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author ouyangjianfeng
 * @time 2019/2/21  11:32
 * @desc
 */
public class CommonViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] mFragments;

    public CommonViewPagerAdapter(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments[i];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
}
