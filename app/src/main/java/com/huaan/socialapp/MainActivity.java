package com.huaan.socialapp;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.huaan.socialapp.adapter.MyCardAdapter;
import com.huaan.socialapp.javabean.CardDataItem;
import com.huaan.socialapp.utils.RondomUtils;
import com.library.titlebarlibrary.TitleBar;
import com.stone.card.library.CardSlidePanel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private TitleBar mTitleBar;
    private DrawerLayout mDrawerLayout;
    private LinearLayout mDrawerLayoutStart;
    private  CardSlidePanel.CardSwitchListener cardSwitchListener;
    private  String imgList[] ={"file:///android_asset/image1.jpeg",
            "file:///android_asset/image2.jpeg", "file:///android_asset/image3.jpeg",
            "file:///android_asset/image4.jpeg", "file:///android_asset/image5.jpeg",
            "file:///android_asset/image6.jpeg", "file:///android_asset/image7.jpeg",
            "file:///android_asset/image8.jpeg", "file:///android_asset/image9.jpeg",
            "file:///android_asset/image10.jpeg", "file:///android_asset/image11.jpeg",
            "file:///android_asset/image12.jpeg", "file:///android_asset/image13.jpeg",
            "file:///android_asset/image14.jpeg", "file:///android_asset/image15.jpeg",
            "file:///android_asset/image16.jpeg", "file:///android_asset/image17.jpeg",
            "file:///android_asset/image18.jpeg"};

    /**
     * DrawerLayout 状态监听
     * onDrawerSlide 滑动
     */

    private DrawerLayout.DrawerListener drawerListener = new DrawerLayout.SimpleDrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
//            super.onDrawerSlide(drawerView, slideOffset);
            //getChildAt(0) 获取子布局第一个，即内容布局
            //v,表示DrawerLayout移动的偏移量，全部关闭时为0，全部打开时为1
            //getMeasuredWidth获取侧滑菜单的原始大小
            //通过计算view的移动距离，确定content布局的移动距离，使两者同时移动（view.getMeasuredWidth()*v）表示偏移距离
            Log.i("slideOffset", "滑动距离" + slideOffset + "滑动view" + drawerView);
            View content = mDrawerLayout.getChildAt(0);
            float offset = 0;
            if (drawerView == mDrawerLayoutStart) {
                offset = drawerView.getMeasuredWidth() * slideOffset;
            }
//            else if(drawerView == mDrawerLayoutEnd){
//                offset = - drawerView.getMeasuredWidth() * slideOffset;
//            }
            content.setTranslationX(offset);
        }
    };

    private ImageView mImageHead;
    private TextView mTextNickname;
    private CardSlidePanel mCardSlidePanel; //卡片布局
    private List<CardDataItem> dataLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ininData();
    }

    public void initView() {
        mCardSlidePanel = (CardSlidePanel) findViewById(R.id.cardSlidePanel);
        mTextNickname = (TextView) findViewById(R.id.text_nickname);
        mImageHead = (ImageView) findViewById(R.id.image_head);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayoutStart = (LinearLayout) findViewById(R.id.drawerLayout_start);
//        mDrawerLayoutEnd = (FrameLayout) findViewById(R.id.drawerLayout_end);
        mDrawerLayout.addDrawerListener(drawerListener);
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        mTitleBar.startImmersive(this, mDrawerLayout);
        mTitleBar.setOnTitleBarClickListener(new TitleBar.OnTitleBarClickListener() {
            @Override
            public void onLeftBtnClick(View view) {
                mDrawerLayout.openDrawer(Gravity.START);
            }

            @Override
            public void onRightBtnClick(View view) {

            }

            @Override
            public void onTitleClick(View view) {

            }
        });
    }

    public void ininData() {
        initDrawerStart();
        initDrawerContent();
    }

    private void initDrawerStart() {
        //设置图片圆角角度
        Glide.with(getApplicationContext())
                .load(getResources().getDrawable(R.drawable.head_img))
                .apply(RequestOptions.circleCropTransform()) //图片设置为圆
                .into(mImageHead);
        mTextNickname.setText("小阔爱");
    }

    private void initDrawerContent() {
        // 1. 左右滑动监听
        cardSwitchListener = new CardSlidePanel.CardSwitchListener() {

            @Override
            public void onShow(int index) {
                Log.i("Card", "正在显示-" + dataLists.get(index).userName);
            }

            @Override
            public void onCardVanish(int index, int type) {
                Log.i("Card", "正在消失-" + dataLists.get(index).userName + " 消失type=" + type);
            }
        };
        mCardSlidePanel.setCardSwitchListener(cardSwitchListener);
        // 2. 绑定Adapter
        mCardSlidePanel.setAdapter(new MyCardAdapter(getApplicationContext(), dataLists));


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              creatData();
                mCardSlidePanel.getAdapter().notifyDataSetChanged();
            }
        }, 500);
    }


//    //生产数据
    private void creatData(){
        long start = System.currentTimeMillis();
        for (int j=0;j<100;j++) {
            for (int i = 0; i < imgList.length; i++) {
                CardDataItem dataItem = new CardDataItem();
                dataItem.imagePath = imgList[i];
                dataItem.likeNum = (int) (Math.random() * 10);
                dataItem.imageNum = (int) (Math.random() * 10);
                dataItem.userName = RondomUtils.getRandomName();
                dataLists.add(dataItem);
            }
        }
        Log.i("prepareDataList", "data size"+dataLists.size()+"prepareDataList: time=" + (System.currentTimeMillis() - start));
    }

}
