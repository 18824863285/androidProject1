package com.gangxiang.aiDaiOrder.zoomabledrawee;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.gangxiang.aiDaiOrder.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/18.
 */

public class ZoomImageActivity extends Activity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.pageNum)
    TextView pageNum;

    private String[] imgs;
    private ShowImgAdapter adapter;
    public static final String IAMGE = "img";
    public static final String INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);
        ButterKnife.bind(this);
        imgs = getIntent().getStringArrayExtra(IAMGE);
        int index = getIntent().getIntExtra(INDEX,0);
        adapter = new ShowImgAdapter(imgs,this);
        viewpager.setAdapter(adapter);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageNum.setText((position+1)+"/"+imgs.length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewpager.setCurrentItem(index);
        int page = index+1;
        pageNum.setText(page+"/"+imgs.length);
    }
}
