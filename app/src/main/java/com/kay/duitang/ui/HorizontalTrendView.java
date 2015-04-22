package com.kay.duitang.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kay.duitang.R;
import com.kay.duitang.bean.TrendItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by mac on 15/4/22.
 */
public class HorizontalTrendView extends LinearLayout {

    private int[] imageSource = {R.drawable.gallery0, R.drawable.gallery1, R.drawable.gallery2, R.drawable.gallery3,
            R.drawable.gallery4, R.drawable.gallery5};

    private String[] imgDescription = {"权利的游戏", "风吹的风景", "插画背景", "美食君", "吃", "你好四月"};


    private List<TrendItem> trendList;

    public HorizontalTrendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
        addTrendView();
    }

    private void initData() {
        trendList = new ArrayList<TrendItem>();
        for (int i = 0; i < imageSource.length; i++) {
            TrendItem item = new TrendItem();
            item.setImageSource(imageSource[i]);
            item.setDescription(imgDescription[i]);
            trendList.add(item);
        }
    }

    private void addTrendView() {
        for (int i = 0; i < trendList.size(); i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.trend_item, this, false);
            ImageView imageView = ButterKnife.findById(view, R.id.trend_img);
            TextView textView = ButterKnife.findById(view, R.id.trend_tv);
            Picasso.with(getContext()).load(trendList.get(i).getImageSource()).into(imageView);
            textView.setText(trendList.get(i).getDescription());
            addView(view);
        }
    }
}
