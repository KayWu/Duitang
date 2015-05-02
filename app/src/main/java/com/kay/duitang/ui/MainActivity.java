package com.kay.duitang.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;

import com.astuetz.PagerSlidingTabStrip;
import com.kay.duitang.R;
import com.kay.duitang.adapter.FragmentAdapter;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends Activity {

    @InjectView(R.id.pager)
    ViewPager mViewPager;

    @InjectView(R.id.indicator)
    PagerSlidingTabStrip mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setActionbar();
        FragmentAdapter mAdapter = new FragmentAdapter(getFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Intent i = new Intent(this, PersonActivity.class);
                startActivity(i);
                break;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setActionbar() {
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.user_default_face);
        ActionBar mActionbar = getActionBar();
        mActionbar.setIcon(new CircleImageDrawble(mBitmap));
        mActionbar.setTitle("用户");
        mActionbar.setHomeButtonEnabled(true);
        setOverflowShowingAlways();
    }

    // 设置显示Overflow按钮，即使有物理Menu按钮
    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
