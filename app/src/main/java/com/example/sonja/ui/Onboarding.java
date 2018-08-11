package com.example.sonja.ui;


import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Das Onboarding, also Screens, die einem die Funktionsweise der App beim ersten Öffnen der App erklären.
 */
public class Onboarding extends Activity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_onboarding, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * First View of Tabbed Activity
     */
    public static class Onboarding1 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public Onboarding1() {
        }

        /**
         * First Page of Onbaording Slides
         * number.
         */
        public static Onboarding1 newInstance(int sectionNumber) {

            System.out.println("Onbaording1");
            Onboarding1 fragment = new Onboarding1();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_onboarding1, container, false);
            return rootView;
        }
    }

    /**
     * Second Page of Onboarding Slides
      */
    public static class Onboarding2 extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_onboarding2, container, false);
            return rootView;
        }

    }

    /**
     * Third Page of Onboarding Slides
      */
    public static class Onboarding3 extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_onboarding3, container, false);
            return rootView;
        }

    }

    /**
     * Fourth Page of Onboarding Slides
      */
    public static class Onboarding4 extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_onboarding4, container, false);
            return rootView;

        }
    }

    /**
     * Fifth and final Page of Onboarding Slides
      */
    public static class Onboarding5 extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_onboarding5, container, false);
            return rootView;

        }
    }


    /**
     * At the end of the last onboarding slide this method is called
      */
    public void onClickGetStarted (View v) {
            System.out.println("onClick_GetStarted aufgerufen");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
    }

    /** Any time you press back in the onboarding process you get redirected to the starting screen.
     */
    public void onBackPressed(){
        System.out.println("Onboarding onBackPressed() aufgerufen.");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    Onboarding1 tab1 = new Onboarding1();
                    return tab1;
                case 1:
                    Onboarding2 tab2 = new Onboarding2();
                    return tab2;
                case 2:
                    Onboarding3 tab3 = new Onboarding3();
                    return tab3;
                case 3:
                    Onboarding4 tab4 = new Onboarding4();
                    return tab4;
                case 4:
                    Onboarding5 tab5 = new Onboarding5();
                    return tab5;
                default:
                    return null;
            }
        }

        /**
         * Total number of pages = 5.
         * @return
         */
        @Override
        public int getCount() {
            return 5;
        }

        /**
         * Get different sections of the slides.
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
                case 4:
                    return "SECTION 5";
            }
            return null;
        }
    }
}
