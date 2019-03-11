package com.gangxiang.aiDaiOrder.widght;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.gangxiang.aiDaiOrder.R;

import java.util.List;

public class AutoGalleryAdapter extends BaseAdapter{

    private Context context;
    private List<String> dateList;
    private int roundConner;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public void setRoundConner(int roundConner) {
        this.roundConner = roundConner;
    }

    @Override
    public int getCount() {
        return dateList == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView =new SimpleDraweeView(context);
        }
        if (position >= dateList.size()) {
            position = position % dateList.size();
        }
        SimpleDraweeView simpleDraweeView = ((SimpleDraweeView) convertView);
        simpleDraweeView.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT, Gallery.LayoutParams.MATCH_PARENT));
        simpleDraweeView.setScaleType(SimpleDraweeView.ScaleType.CENTER_CROP);

        loadImg(simpleDraweeView,dateList.get(position),roundConner);

     //   SimpleDraweeViewUtil.setNetWorkImage(simpleDraweeView,dateList.get(position));

        return convertView;
    }

    private void loadImg(SimpleDraweeView simpleDraweeView,String url,int roundConnor){

        RoundingParams roundingParams = new RoundingParams();
        roundingParams.setCornersRadius(roundConnor);

        GenericDraweeHierarchy genericDraweeHierarchy = GenericDraweeHierarchyBuilder.newInstance(context.getResources())
                .setRoundingParams(roundingParams)
                .setFailureImage(context.getResources().getDrawable(R.drawable.shouye001), ScalingUtils.ScaleType.FIT_XY)
                .build();
        simpleDraweeView.setHierarchy(genericDraweeHierarchy);

        Uri uri = Uri.parse(url);

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(300, 300))
                .build();

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();

        simpleDraweeView.setController(controller);

    }

}
