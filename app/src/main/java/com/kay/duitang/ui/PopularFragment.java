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
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Kay on 15/4/18.
 */
public class PopularFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    ViewPager imagePager;

    CirclePageIndicator imageIndicator;

    @InjectView(R.id.stagger_view)
    StaggeredGridView mStaggeredGridView;

    @InjectView(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    static Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        ButterKnife.inject(this, view);
        View topView = inflater.inflate(R.layout.top_view, (ViewGroup) view, false);
        imagePager = ButterKnife.findById(topView, R.id.image_pager);
        imageIndicator = ButterKnife.findById(topView, R.id.image_indicator);
        mStaggeredGridView.addHeaderView(topView);
        initView();
        handler = new Handler();
        return view;
    }

    private void initView() {
        TopItemAdapter adapter = new TopItemAdapter(getActivity());
        imagePager.setAdapter(adapter);
        imageIndicator.setViewPager(imagePager);
        mStaggeredGridView.setAdapter(new StaggerItemAdapter(getActivity()));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.blue);
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
}
