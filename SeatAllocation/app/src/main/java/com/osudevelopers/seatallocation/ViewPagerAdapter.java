package com.osudevelopers.seatallocation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuta on 2016/04/23.
 */
class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, int i, CarCar c) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(MyApplication.getContext().getString(R.string.enNumOfCar) + i + MyApplication.getContext().getString(R.string.jaNumOfCar));

        Bundle args = new Bundle();
        args.putInt("num", i);
        args.putSerializable("car", c);
        fragment.setArguments(args);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

}
