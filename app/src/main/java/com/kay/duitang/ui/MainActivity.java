package com.kay.duitang.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.kay.duitang.adapter.FragmentAdapter;
import com.kay.duitang.R;

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Toast.makeText(this, "homebutton clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setActionbar() {
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.action_bar_icon_user);
        ActionBar mActionbar = getActionBar();
        mActionbar.setIcon(new CircleImageDrawble(mBitmap));
        mActionbar.setTitle("用户");
        mActionbar.setHomeButtonEnabled(true);
    }
}
