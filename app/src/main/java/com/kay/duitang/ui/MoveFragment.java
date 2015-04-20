package com.kay.duitang.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etsy.android.grid.StaggeredGridView;
import com.kay.duitang.R;
import com.kay.duitang.adapter.StaggerItemAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mac on 15/4/19.
 */
public class MoveFragment extends Fragment {

    @InjectView(R.id.stagger_view)
    StaggeredGridView mStaggeredGridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stagger, container, false);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    private void initView() {
        mStaggeredGridView.setAdapter(new StaggerItemAdapter(getActivity(), true));
    }


}
