package com.kay.duitang.adapter;

import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kay.duitang.R;
import com.kay.duitang.bean.TopItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by mac on 15/4/18.
 */
public class TopItemAdapter extends android.support.v4.view.PagerAdapter {

    private Context mContext;

    private List<TopItem> imageSource;

    public final float[] matrix = new float[]{
            1, 0, 0, 0, -30,
            0, 1, 0, 0, -30,
            0, 0, 1, 0, -30,
            0, 0, 0, 1, 0
    };

    public TopItemAdapter(Context context) {
        mContext = context;
        initData();
    }

    private void initData() {
        imageSource = new ArrayList<TopItem>();
        imageSource.add(new TopItem(R.drawable.top1, "我所经历的生活", "4月18日 周六"));
        imageSource.add(new TopItem(R.drawable.top2, "橡皮擦初心", "4月18日 周六"));
        imageSource.add(new TopItem(R.drawable.top3, "一只喵的幸福生活", "4月17日 周五"));
        imageSource.add(new TopItem(R.drawable.top4, "手绘电影场景", "4月16日 周四"));
    }


    @Override
    public int getCount() {
        return imageSource.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.top_image_item, container, false);
        container.addView(view);
        ImageView topImageView = ButterKnife.findById(view, R.id.top_image);
        TextView time = ButterKnife.findById(view, R.id.time);
        TextView title = ButterKnife.findById(view, R.id.title);
        topImageView.setColorFilter(new ColorMatrixColorFilter(matrix));
        TopItem mTopItem = imageSource.get(position);
        Picasso.with(mContext).load(mTopItem.getImageSource()).into(topImageView);
        time.setText(mTopItem.getTime());
        title.setText(mTopItem.getTitle());
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
