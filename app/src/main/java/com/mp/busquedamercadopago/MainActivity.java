package com.mp.busquedamercadopago;


import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mp.busquedamercadopago.activities.Activity;
import com.mp.busquedamercadopago.classes.Constants;
import com.mp.busquedamercadopago.fragments.SearchCategoryFragment;
import com.mp.busquedamercadopago.fragments.SearchProductFragment;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by Leandro on 28/07/2020.
 */

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager =  findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout  tabLayout =  findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());



        adapter.addFrag(new SearchProductFragment(), getString(R.string.main_busqueda_producto));
        adapter.addFrag(new SearchCategoryFragment(), getString(R.string.main_busquedaa_category));


        viewPager.setAdapter(adapter);


    }
}
