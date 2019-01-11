package com.huaan.socialapp.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huaan.socialapp.javabean.CardDataItem;
import com.huaan.socialapp.R;
import com.stone.card.library.CardAdapter;

import java.util.List;

public class MyCardAdapter extends CardAdapter {

    private Context context;
    private List<CardDataItem> dataList;

    public MyCardAdapter(Context context, List<CardDataItem> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_card_item;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public void bindView(View view, int index) {
        Object tag = view.getTag();
        ViewHolder viewHolder;
        if (null != tag) {
            viewHolder = (ViewHolder) tag;
        } else {
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        viewHolder.bindData(dataList.get(index));
    }

    @Override
    public Object getItem(int index) {
        return dataList.get(index);
    }

    @Override
    public Rect obtainDraggableArea(View view) {
        // 可滑动区域定制，该函数只会调用一次
        View contentView = view.findViewById(R.id.card_item_content);
        View topLayout = view.findViewById(R.id.card_top_layout);
        View bottomLayout = view.findViewById(R.id.card_bottom_layout);
        int left = view.getLeft() + contentView.getPaddingLeft() + topLayout.getPaddingLeft();
        int right = view.getRight() - contentView.getPaddingRight() - topLayout.getPaddingRight();
        int top = view.getTop() + contentView.getPaddingTop() + topLayout.getPaddingTop();
        int bottom = view.getBottom() - contentView.getPaddingBottom() - bottomLayout.getPaddingBottom();
        return new Rect(left, top, right, bottom);
    }

    class ViewHolder {

        ImageView imageView;
        View maskView;
        TextView userNameTv;
        TextView imageNumTv;
        TextView likeNumTv;
        LinearLayout card_content;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.card_image_view);
            maskView = view.findViewById(R.id.maskView);
            userNameTv = (TextView) view.findViewById(R.id.card_user_name);
            imageNumTv = (TextView) view.findViewById(R.id.card_pic_num);
            likeNumTv = (TextView) view.findViewById(R.id.card_like);
            card_content = view.findViewById(R.id.card_item_content);
        }

        public void bindData(final CardDataItem itemData) {
            Glide.with(context).load(itemData.imagePath).into(imageView);
            userNameTv.setText(itemData.userName);
            imageNumTv.setText(itemData.imageNum + "");
            likeNumTv.setText(itemData.likeNum + "");
            card_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("MyCardAdapter","点击了" + itemData.userName);
                }
            });
        }
    }
}
