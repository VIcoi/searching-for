package com.example.jbt.placeofzeze.model;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.jbt.placeofzeze.frags.FragFavorites;
import com.example.jbt.placeofzeze.frags.ListFragment;
import com.example.jbt.placeofzeze.frags.MapsActivity;

import java.util.ArrayList;

public class FragAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private ArrayList<String> titles = new ArrayList<>();


    public FragAdapter(FragmentManager fm,boolean bool) {
        super(fm);

        if (bool){//adding the frags and titles by tablet or phone using bool set by screen size

            fragments.add(new ListFragment());
            fragments.add(new FragFavorites());

            titles.add("User Search");
            titles.add("Favorites");
        }else {

            fragments.add(new ListFragment());
            fragments.add(new MapsActivity());
            fragments.add(new FragFavorites());

            titles.add("User Search");
            titles.add("Map");
            titles.add("Favorites");

        }


    }


    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);//position of frags
    }

    @Override
    public int getCount() {
        return fragments.size();//nums of frags
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);//position of titles

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//in case of orientation save the adapter position

        Object o = super.instantiateItem(container, position);
        fragments.set(position, (Fragment) o);

        return o;
    }

}
