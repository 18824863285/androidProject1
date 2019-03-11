package com.gangxiang.aiDaiOrder.zoomabledrawee;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import com.gangxiang.aiDaiOrder.R;

/**
 * Created by sss on 2016/7/24.
 */
public class ShowImgAdapter extends PagerAdapter {


    private String[] imgs;

    private Context mContext;


    public ShowImgAdapter(String[] imgs, Context mContext) {
        this.imgs = imgs;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if(imgs == null){
            return 0;
        }
        return imgs.length;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        final PhotoDraweeView mPhotoDraweeView = (PhotoDraweeView) LayoutInflater.from(mContext).inflate(R.layout.img_item,collection,false);
        // 需要使用 ControllerBuilder 方式请求图片
        PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
        controller.setUri(Uri.parse(imgs[position]));
        controller.setOldController(mPhotoDraweeView.getController());
        // 需要设置 ControllerListener，获取图片大小后，传递给 PhotoDraweeView 更新图片长宽
        controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                if (imageInfo == null || mPhotoDraweeView == null) {
                    return;
                }
                mPhotoDraweeView.update(imageInfo.getWidth(), imageInfo.getHeight());
            }
        });
        mPhotoDraweeView.setController(controller.build());

        mPhotoDraweeView.setOnViewTapListener(new OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                ((Activity)mContext).finish();
            }
        });
        collection.addView(mPhotoDraweeView);

        return mPhotoDraweeView;

    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

}
