package com.example.app_mobile_lena.Category_section;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class categoryAdapter extends FragmentStateAdapter {
    private String[] titles = new String[] {"Sản Phẩm Từ Len", "Dụng Cụ Len"};
    private wool_product_fragment prodFrag = new wool_product_fragment();
    private wool_tool_fragment toolFrag = new wool_tool_fragment();
    public categoryAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:


                return new wool_product_fragment();
            case 1:
                return new wool_tool_fragment();
            default:
                return new wool_product_fragment();
        }

    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
