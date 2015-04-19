package com.kay.duitang.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mac on 15/4/11.
 */
public class PagerFragment extends Fragment {

    public static PagerFragment newInstance(String number) {
        Bundle bundle = new Bundle();
        bundle.putString("tv", number);
        PagerFragment fragment = new PagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        TextView tv = new TextView(getActivity());
        if (bundle != null) {
            String number = bundle.getString("tv");
            tv.setText(number);
        }
        return tv;
    }
}
