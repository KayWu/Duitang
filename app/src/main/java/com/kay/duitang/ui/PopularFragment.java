package com.kay.duitang.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kay.duitang.R;
import com.kay.duitang.adapter.TopItemAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mac on 15/4/18.
 */
public class PopularFragment extends Fragment {


    @InjectView(R.id.image_pager)
    ViewPager imagePager;

    @InjectView(R.id.image_indicator)
    CirclePageIndicator imageIndicator;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        ButterKnife.inject(this, view);
        initView();

        return view;
    }

    private void initView() {
        TopItemAdapter adapter = new TopItemAdapter(getActivity());
        imagePager.setAdapter(adapter);
        imageIndicator.setViewPager(imagePager);
    }
}
