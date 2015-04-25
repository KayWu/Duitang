package com.kay.duitang.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.kay.duitang.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Kay on 15/4/23.
 */
public class PersonActivity extends Activity implements ScrollNotifyView.OnScrollViewListener {


    @InjectView(R.id.scroll_view)
    ScrollNotifyView mScrollView;

    ActionBar mActionBar;
    private ColorDrawable actionBarBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.inject(this);
        Animation alphaShow = AnimationUtils.loadAnimation(this, R.anim.alpha_show);
        mScrollView.startAnimation(alphaShow);
        setActionBar();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_person, menu);
        return true;
    }

    private void initView() {
        mScrollView.setOnScrollViewListener(this);
    }

    private void setActionBar() {
        mActionBar = getActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        actionBarBackground = new ColorDrawable(getResources().getColor(R.color.dark_actionbar));
        mActionBar.setBackgroundDrawable(actionBarBackground);
        mActionBar.setIcon(new ColorDrawable(getResources().getColor(R.color.transparent)));
        mActionBar.setTitle("用户");
        setActionBarAlpha(0);
    }


    private TextView getActionBarTitleView() {
        int id = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
        return (TextView) findViewById(id);
    }

    private void setActionBarAlpha(float alpha) {
        actionBarBackground.setAlpha((int) (alpha * 255));
        getActionBarTitleView().setAlpha(alpha);
    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        setActionBarAlpha(calculateAlpha(t));
    }

    private float calculateAlpha(int scrollY) {
        int minDist = 0;
        int maxDist = 300;
        if (scrollY > maxDist) return 1;
        else if (scrollY < minDist) return 0;
        else {
            return (scrollY / (float) maxDist);
        }

    }


}
