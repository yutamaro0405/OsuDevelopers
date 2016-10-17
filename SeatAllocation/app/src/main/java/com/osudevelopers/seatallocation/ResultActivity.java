package com.osudevelopers.seatallocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private int mNumCar = 5;
    private ArrayList<CarCar> listCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_resultActivity);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        ArrayList<CarCar> listCar = (ArrayList<CarCar>) intent.getSerializableExtra(CarSelectionActivity.EXTRA_LISTCAR);
        this.listCar = listCar;

        if (listCar != null) {
            mNumCar = listCar.size();
        }

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        int i = 0;
        for (CarCar c : listCar) {
            i++;
            adapter.addFrag(new ResultFragment(), i, c);
        }

        viewPager.setAdapter(adapter);
    }

    public void changeHandle(View view) {
        PagerAdapter cvc = mViewPager.getAdapter();
        System.out.println(mViewPager.getAdapter());
        ((ResultFragment)(cvc.instantiateItem(mViewPager,mViewPager.getCurrentItem()))).changeDriver();
        System.out.println(mViewPager.getAdapter());

    }
}
