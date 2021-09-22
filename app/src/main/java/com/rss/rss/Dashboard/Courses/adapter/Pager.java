package com.rss.rss.Dashboard.Courses.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rss.rss.Dashboard.Courses.LiveCourseFragment;
import com.rss.rss.Dashboard.Courses.VideoCoursesFragment;

public class Pager extends FragmentStatePagerAdapter {

    // tab titles
    private String[] tabTitles = new String[]{"Video", "Live"};

    public Pager(FragmentManager fm) {
        super(fm);
    }

    // overriding getPageTitle()
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new VideoCoursesFragment();
            case 1:
                return new LiveCourseFragment();
            default:
                throw new RuntimeException("Invalid tab position");
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

}