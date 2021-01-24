package com.example.macpas;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class TutorialFragment extends Fragment {

    private static final int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tutorial, container, false);

        viewPager = root.findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this.getActivity());
        ((ScreenSlidePagerAdapter) pagerAdapter).addFragment(new TutorialPage1());
        ((ScreenSlidePagerAdapter) pagerAdapter).addFragment(new TutorialPage2());




        viewPager.setAdapter(pagerAdapter);

        return root;
    }



    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        private ArrayList<Fragment> arrayList = new ArrayList<>();
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }
        public void addFragment(Fragment fragment) {
            arrayList.add(fragment);
        }
        @Override
        public Fragment createFragment(int position) {
            return arrayList.get(position);
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}