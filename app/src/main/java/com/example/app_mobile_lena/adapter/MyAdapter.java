package com.example.app_mobile_lena.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends FragmentStateAdapter {
    private List<Fragment> fragments = new ArrayList<>();


    public MyAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Fragment> fragments) {
        super(fragmentManager, lifecycle);
        this.fragments = fragments;

    }
    public MyAdapter(Fragment activity) {
        super(activity);
        this.fragments.add(activity);
    }

    public MyAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

            return fragments.get(position);

    }


    @Override
    public int getItemCount() {
        return fragments.size();
    }

}
