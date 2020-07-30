package com.mp.busquedamercadopago.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.mp.busquedamercadopago.classes.Constants;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


/**
 * Superclase con funciones compartidas.
 * Created by Leandro on 28/07/2020.
 */
public class Activity extends AppCompatActivity {

    public static Typeface typeface_roboto_bold;
    public static Typeface typeface_regular;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        typeface_roboto_bold = Typeface.createFromAsset(getAssets(), Constants.TYPEFACE_FONT_OPENSANS_SEMIBOLD);
        typeface_regular = Typeface.createFromAsset(getAssets(), Constants.TYPEFACE_FONT_OPENSANS_REGULAR);

    }

    /**
     * Por si hubiese mas de un fragmentAdapter
     */
    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {


            return mFragmentList.get(position);


        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
