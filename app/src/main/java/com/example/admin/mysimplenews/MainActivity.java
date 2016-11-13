package com.example.admin.mysimplenews;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.jaeger.library.StatusBarUtil;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private android.support.v7.widget.Toolbar toolbar;
    private android.widget.FrameLayout fragmentGroups;
    private android.support.design.widget.NavigationView navigationView;
    private android.support.v4.widget.DrawerLayout activitymain;
    private FragmentManager fm;
    private TuPianFragment tuPianFragment;
    private NewsFragment newsFragment;
    private TianQiFragment tianQiFragment;
    private SheZhiFragment sheZhiFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        StatusBar.setColor(this,getResources().getColor(R.color.colorAccent));

        fm = getSupportFragmentManager();
        if (savedInstanceState == null) {
            tuPianFragment = new TuPianFragment();
            newsFragment = new NewsFragment();
            tianQiFragment = new TianQiFragment();
            sheZhiFragment = new SheZhiFragment();
            fm.beginTransaction().replace(R.id.fragmentGroups, newsFragment, NewsFragment.class.getSimpleName()).commit();
        }
//activitymain.setStatusBarBackgroundColor();
        initView();

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
//            StatusBar.setColor(this, getResources().getColor(R.color.colorAccent));
            StatusBarUtil.setColorForDrawerLayout(this, activitymain, getResources().getColor(R.color.colorAccent));
//                navigationView.setClipToPadding(true);
        }


    }

    private void initView() {
        this.activitymain = (DrawerLayout) findViewById(R.id.activity_main);
        this.navigationView = (NavigationView) findViewById(R.id.navigationView);
        this.fragmentGroups = (FrameLayout) findViewById(R.id.fragmentGroups);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, activitymain, toolbar, R.string.open, R.string.close);
        activitymain.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.inflateMenu(R.menu.tool);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("SimpleNews");
        navigationView.setNavigationItemSelectedListener(this);
//        StatusBarUtil.setColor(this,getResources().getColor(R.color.colorAccent));
//        StatusBarUtil.setColorForDrawerLayout(this, activitymain,getResources().getColor(R.color.colorAccent));


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        activitymain.closeDrawer(Gravity.LEFT);
        toolbar.setTitle(item.getTitle());
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (item.getItemId()) {
            case R.id.newsItem:
                fragmentTransaction.replace(R.id.fragmentGroups, newsFragment, NewsFragment.class.getSimpleName());

                break;
            case R.id.picItem:
                fragmentTransaction.replace(R.id.fragmentGroups, tuPianFragment, TuPianFragment.class.getSimpleName());

                break;
            case R.id.wheatherItem:
                fragmentTransaction.replace(R.id.fragmentGroups, tianQiFragment, TianQiFragment.class.getSimpleName());
                break;
            case R.id.settingItem:
                fragmentTransaction.replace(R.id.fragmentGroups, sheZhiFragment, SheZhiFragment.class.getSimpleName());

                break;
        }
        fragmentTransaction.commit();
        return true;
    }
}
