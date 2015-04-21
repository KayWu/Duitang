package com.kay.duitang.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kay.duitang.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mac on 15/4/20.
 */
public class DiscoverFragment extends Fragment {

    @InjectView(R.id.scroll_layout)
    LinearLayout scrollLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    private void initView() {
        for (int i = 0; i < 6; i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.gallery_item, scrollLayout, true);
        }
    }
}
