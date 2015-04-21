package com.kay.duitang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kay.duitang.R;
import com.kay.duitang.bean.GalleryItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mac on 15/4/21.
 */
public class GalleryItemAdapter extends BaseAdapter {

    Context mContext;
    List<GalleryItem> itemList;
    private int[] imageSource = {R.drawable.gallery0, R.drawable.gallery1, R.drawable.gallery2, R.drawable.gallery3,
            R.drawable.gallery4, R.drawable.gallery5};

    private String[] imgDescription = {"权利的游戏", "风吹的风景", "插画背景", "美食君", "吃", "你好四月"};

    public GalleryItemAdapter(Context context) {
        mContext = context;
        initData();
    }

    private void initData() {
        itemList = new ArrayList<GalleryItem>();
        for (int i = 0; i < 6; i++) {
            GalleryItem item = new GalleryItem();
            item.setImageSource(imageSource[i]);
            item.setDescription(imgDescription[i]);
            itemList.add(item);
        }
    }

    @Override
    public int getCount() {
        return imageSource.length;
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gallery_item, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mContext).load(itemList.get(position).getImageSource()).into(vh.galleryImg);
        vh.galleryDescription.setText(itemList.get(position).getDescription());

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.gallery_img)
        ImageView galleryImg;

        @InjectView(R.id.gallery_tv)
        TextView galleryDescription;


        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
