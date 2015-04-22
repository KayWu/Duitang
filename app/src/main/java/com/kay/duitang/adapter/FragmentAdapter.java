package com.kay.duitang.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.kay.duitang.ui.DiscoverFragment;
import com.kay.duitang.ui.MsgFragment;
import com.kay.duitang.ui.PagerFragment;
import com.kay.duitang.ui.PopularFragment;
import com.kay.duitang.ui.MoveFragment;

/**
 * Created by mac on 15/4/11.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    private String[] pageTitle = {"热门", "发现", "动态", "消息"};

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PopularFragment();
            case 1:
                return new DiscoverFragment();
            case 2:
                return new MoveFragment();
            case 3:
                return new MsgFragment();
            default:
                return PagerFragment.newInstance(position + "");
        }
    }

    @Override
    public int getCount() {
        return pageTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
    }
}
