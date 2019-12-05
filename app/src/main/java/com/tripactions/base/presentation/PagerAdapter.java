package com.tripactions.base.presentation;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 *
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private Fragment[] mFragments;

    public PagerAdapter(FragmentManager fm, Fragment... fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (position >= 0 && position < mFragments.length) {
            return mFragments[position];
        }
        return null;
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
}
