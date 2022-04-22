package com.sabkokura.sahayekhelath.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sabkokura.sahayekhelath.Fragments.BMIForKidsFragment;
import com.sabkokura.sahayekhelath.Fragments.BMIForMenFragment;
import com.sabkokura.sahayekhelath.Fragments.BMIForWomenFragment;

public class BMIPageAdapter extends FragmentStateAdapter {
    public static final String[] BMI_TITLES = {"KIDS","MEN","WOMEN"};
    Context context;
    public BMIPageAdapter(@NonNull FragmentActivity fragmentActivity, Context context) {
        super(fragmentActivity);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new BMIForKidsFragment();
            case 1:
                return new BMIForMenFragment();
            case 2:
                return new BMIForWomenFragment();
        }
        return new BMIForKidsFragment();
    }

    @Override
    public int getItemCount() {
        return BMI_TITLES.length;
    }
}
