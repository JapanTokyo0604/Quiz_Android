package com.elephant.examplequiza;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentStatePagerAdapter extends FragmentPagerAdapter {

    public MyFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch(i){
            case 0:
                return new SelectionFragment();
            case 1:
                return new HistoryFragment();
            default:
                return new SettingFragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "問題選択";
            case 1:
                return "履歴";
            default:
                return "設定";

        }
    }

}