package com.shanghai.agentwebjs.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.shanghai.agentwebjs.R;
import com.shanghai.agentwebjs.adapter.MainViewPagerAdapter;
import com.shanghai.agentwebjs.widget.navigation.BottomNavigation;
import com.shanghai.agentwebjs.widget.navigation.BottomNavigationAdapter;
import com.shanghai.agentwebjs.widget.navigation.BottomNavigationViewPager;

public class Main4Activity extends AppCompatActivity {
    // UI
    BottomNavigationViewPager viewPager;
    BottomNavigation bottomNavigation;
    LinearLayout mLinearLayout;
    // Adapter
    private MainViewPagerAdapter adapter;
    public static boolean isForeground = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        viewPager=findViewById(R.id.view_pager);
        bottomNavigation=findViewById(R.id.bottom_navigation);
        mLinearLayout=findViewById(R.id.ll_dow);
        // 隐藏导航栏Items
        BottomNavigationAdapter navigationAdapter = new BottomNavigationAdapter(this, R.menu.menu_bottom_navigation);
        navigationAdapter.setupWithBottomNavigation(bottomNavigation);
        bottomNavigation.setBehaviorTranslationEnabled(false);

        // 隐藏导航栏标题
        bottomNavigation.setTitleState(BottomNavigation.TitleState.ALWAYS_SHOW);
        // 导航点击事件
        bottomNavigation.setOnTabSelectedListener(new BottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                viewPager.setCurrentItem(position, false);

                return true;
            }
        });

        viewPager.setOffscreenPageLimit(3);
        adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
    @Override
    protected void onResume() {

        isForeground = true;
        super.onResume();

    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();

    }
}
