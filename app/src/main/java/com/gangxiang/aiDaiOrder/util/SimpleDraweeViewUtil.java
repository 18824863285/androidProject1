package com.gangxiang.aiDaiOrder.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;

import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;

/**
 * Created by Administrator on 2017/2/13.
 */

public class SimpleDraweeViewUtil {

    public static void setNetWorkImage(SimpleDraweeView draweeView, String url){

        if(EmptyCheck.isEmpty(url)){
            return;
        }

        if(draweeView == null){
            return;
        }

        Uri uri = Uri.parse(url);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(300, 300))
                .build();

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView.getController())
                .build();
        draweeView.setController(controller);
    }

    public static void setNetWorkImage(View draweeView, String url){

        if(! (draweeView instanceof SimpleDraweeView)){
            return;
        }

        SimpleDraweeView draweeView1 = (SimpleDraweeView)draweeView;

        if(EmptyCheck.isEmpty(url)){
            return;
        }

        Uri uri = Uri.parse(url);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(300, 300))
                .build();

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView1.getController())
                .build();
        draweeView1.setController(controller);
    }

    public static void setNetWorkImage(SimpleDraweeView draweeView, String url, int width, int high){
        Uri uri = Uri.parse(url);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, high))
                .build();

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView.getController())
                .build();
        draweeView.setController(controller);
    }

    public static  Bitmap returnBitmap(String url) {
        Uri uri = Uri.parse(url);
        Bitmap bitmap = null;
        FileBinaryResource resource = (FileBinaryResource) Fresco.getImagePipelineFactory().getMainDiskStorageCache().getResource(new SimpleCacheKey(uri.toString()));
        if(resource == null){
            return null;
        }
        File file = resource.getFile();
        bitmap = BitmapFactory.decodeFile(file.getPath());
        return bitmap;

    }


    public static  Uri returnLocalBitmapUrl(String url) {
        Uri uri = Uri.parse(url);
        Bitmap bitmap = null;
        FileBinaryResource resource = (FileBinaryResource) Fresco.getImagePipelineFactory().getMainDiskStorageCache().getResource(new SimpleCacheKey(uri.toString()));
        if(resource == null){
            return null;
        }
        File file = resource.getFile();
        return Uri.fromFile(file);

    }



}
