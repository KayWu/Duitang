package com.kay.duitang.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.kay.duitang.R;
import com.kay.duitang.adapter.StaggerItemAdapter;
import com.kay.duitang.adapter.TopItemAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Kay on 15/4/18.
 */
public class PopularFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    ViewPager imagePager;


    ClumsyIndicator mClumsyIndicator;

    @InjectView(R.id.stagger_view)
    StaggeredGridView mStaggeredGridView;

    @InjectView(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private static Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        ButterKnife.inject(this, view);
        View topView = inflater.inflate(R.layout.top_view, (ViewGroup) view, false);
        imagePager = ButterKnife.findById(topView, R.id.image_pager);
        mClumsyIndicator = ButterKnife.findById(topView, R.id.indicator);
        mStaggeredGridView.addHeaderView(topView);
        initView();
        handler = new Handler();
        return view;
    }

    private void initView() {
        TopItemAdapter adapter = new TopItemAdapter(getActivity());
        imagePager.setAdapter(adapter);
        mClumsyIndicator.setViewPager(imagePager);
        mStaggeredGridView.setAdapter(new StaggerItemAdapter(getActivity()));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.blue);

        // 解决SwipeRefreshLayout和ViewPager的手势冲突
        // 在ViewPager滑动时使SwipeRefreshLayout失效 停止滑动时使其正常
        imagePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            boolean isHandled = false;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (!isHandled && positionOffsetPixels > 0) {
                    mSwipeRefreshLayout.setEnabled(false);
                    isHandled = true;
                }
            }

            @Override
            public void onPageSelected(int position) {
                mClumsyIndicator.setSelectedItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        mSwipeRefreshLayout.setEnabled(true);
                        isHandled = false;
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            int currentItem = savedInstanceState.getInt("pager_current_item");
            imagePager.setCurrentItem(currentItem, false);
        }
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), getString(R.string.refresh_success), Toast.LENGTH_SHORT).show();
            }
        }, 3000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
    }

    // 保存imagePager状态
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pager_current_item", imagePager.getCurrentItem());

    }
}
