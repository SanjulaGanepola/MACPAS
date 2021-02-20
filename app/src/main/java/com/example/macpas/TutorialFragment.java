package com.example.macpas;

import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Locale;

public class TutorialFragment extends Fragment {

    private static final int NUM_PAGES = 7;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;

    private TextToSpeech mTTSTutorial;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tutorial, container, false);

        viewPager = root.findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this.getActivity());
        ((ScreenSlidePagerAdapter) pagerAdapter).addFragment(new TutorialPage1());
        ((ScreenSlidePagerAdapter) pagerAdapter).addFragment(new TutorialPage2());
        ((ScreenSlidePagerAdapter) pagerAdapter).addFragment(new TutorialPage3());
        ((ScreenSlidePagerAdapter) pagerAdapter).addFragment(new TutorialPage4());
        ((ScreenSlidePagerAdapter) pagerAdapter).addFragment(new TutorialPage5());
        ((ScreenSlidePagerAdapter) pagerAdapter).addFragment(new TutorialPage6());
        ((ScreenSlidePagerAdapter) pagerAdapter).addFragment(new TutorialPage7());

        viewPager.setAdapter(pagerAdapter);

        mTTSTutorial = ((MainActivity) getActivity()).mTTS;
        ViewPager2.OnPageChangeCallback pageChangeListener = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTTSTutorial.stop();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        viewPager.registerOnPageChangeCallback(pageChangeListener);


        /*
        mTTSTutorial = ((MainActivity) getActivity()).mTTS;
        ViewPager2.OnPageChangeCallback pageChangeListener = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position) {
                    case 0:
                        //mTTSTutorial.speak("Home Screen Scanning. Tap the screen to start scanning. Observe scanning and listen to the audio cues. Tap the screen again to select the highlighted button.", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case 1:
                        //mTTSTutorial.speak("Features Overview. Space, Inserts a space. Backspace, Removes a character. Abbreviations, Expands phrase. Example TY* = THANK YOU. Numbers and Punctuations, Opens its subscreen, Clear, Clears message window.", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case 2:
                        mTTSTutorial.speak("Subscreen Scanning. Tap the screen to start scanning. Observe scanning and listen to the audio cues. Tap the screen again to select the highlighted button.", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case 3:
                        mTTSTutorial.speak("Text-to-Speech. Adjust phone volume to appropriate levels. Tap the message window to activate speech.", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case 4:
                        mTTSTutorial.speak("Side Panel. Swipe from the left to open the side panel. Navigate to the home screen. View the tutorial screen. Adjust the app settings.", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case 5:
                        mTTSTutorial.speak("Settings Screen, Speed and Theme. Adjust scanning speed using the slider. Select the desired colour theme.", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case 6:
                        mTTSTutorial.speak("Settings Screen, Abbreviations. Toggle between adding and removing abbreviations. Add abbreviations by typing acronym and phrase. Remove abbreviations by typing acronym. Press save to apply changes. View all current abbreviations.", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        viewPager.registerOnPageChangeCallback(pageChangeListener);
        */

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