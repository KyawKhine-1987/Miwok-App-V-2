/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.freelance.android.miwok.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.freelance.android.miwok.fragments.ColorsFragment;
import net.freelance.android.miwok.fragments.FamilyFragment;
import net.freelance.android.miwok.fragments.NumbersFragment;
import net.freelance.android.miwok.fragments.PhrasesFragment;
import net.freelance.android.miwok.model.Word;

/**
 * {@link CategoryAdapter or TabLayourt Adapter} is a {@link FragmentPagerAdapter} that can provide the layout for
 * each list item based on a data source which is a list of {@link Word} objects.
 */
public class CategoryAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles [] = new String[] {"NUMBERS", "FAMILY", "COLORS", "PHRASES"};
    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1) {
            return new FamilyFragment();
        } else if (position == 2) {
            return new ColorsFragment();
        } else {
            return new PhrasesFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    /**
     * Get the TabLayout of each Page Title.
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
