package com.gangxiang.aiDaiOrder.widght;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.gangxiang.aiDaiOrder.R;
import com.gangxiang.aiDaiOrder.util.DensityUtils;

import java.util.List;

public class AutoGalleryConfig {

   private AutoGallery autoGallery;
   private int selectIndex = 4 * 1000000;
   private int duration = 2000;//毫秒
   private boolean isAutoScroll = true;
   private int length;
   private PageGuide pageGuide;
   private List<String> dateList;
   private Context context;
   private AutoGalleryAdapter autoGalleryAdapter;
   private int indicationOn = R.drawable.shape_qiehuanyuan_pre;
   private int indicationOff = R.drawable.shape_qiehuanyuan_pnor;
   private int indicationW_H_size;
   private int roundConner = 0;
   private OnItemClickListen onItemClickListen;

   public static class Builder{
      private AutoGalleryConfig autoGalleryConfig;

      public Builder() {
         this.autoGalleryConfig = new AutoGalleryConfig();
         this.autoGalleryConfig.autoGalleryAdapter = new AutoGalleryAdapter();
      }

      public Builder setAutoGallery(AutoGallery autoGallery){
         autoGalleryConfig.autoGallery = autoGallery;
         return  this;
      }

      public Builder setSelectIndex(int selectIndex){
         autoGalleryConfig.selectIndex = selectIndex;
         return  this;
      }

      public Builder setDuration(int duration){
         autoGalleryConfig.duration = duration;
         return  this;
      }

      public Builder setIsAutoScroll(boolean isAutoScroll){
         autoGalleryConfig.isAutoScroll = isAutoScroll;
         return this;
      }

      public Builder setPageGuide(PageGuide pageGuide){
         autoGalleryConfig.pageGuide = pageGuide;
         return this;
      }

      public Builder setDateList(List<String> dateList){
         autoGalleryConfig.dateList = dateList;
         return  this;
      }

      public Builder setContext(Context context){
         autoGalleryConfig.context = context;
         return  this;
      }

      public Builder setIndicationOn(int resOn){
         autoGalleryConfig.indicationOn = resOn;
         return this;
      }

      public Builder setIndicationOff(int resOff){
         autoGalleryConfig.indicationOff = resOff;
         return this;
      }

      public Builder setIndicationW_H_size(int dp){
         autoGalleryConfig.indicationW_H_size =  DensityUtils.dp2px(autoGalleryConfig.context,dp);
         return this;
      }

      public Builder setRoundConner(int roundConner){
         autoGalleryConfig.roundConner = roundConner;
         return this;
      }

      public Builder setOnItemClickListen(OnItemClickListen onItemClickListen){
         autoGalleryConfig.onItemClickListen = onItemClickListen;
         return this;
      }

      public  AutoGalleryConfig start(){

         if(autoGalleryConfig.autoGallery == null){
            return null;
         }

         if(autoGalleryConfig.context == null){
            return null;
         }

         if(autoGalleryConfig.dateList == null || autoGalleryConfig.dateList.size() == 0){
            return null;
         }

         autoGalleryConfig.autoGalleryAdapter.setContext(autoGalleryConfig.context);
         autoGalleryConfig.autoGalleryAdapter.setDateList(autoGalleryConfig.dateList);
         autoGalleryConfig.autoGalleryAdapter.setRoundConner(autoGalleryConfig.roundConner);
         autoGalleryConfig.autoGallery.setAdapter( this.autoGalleryConfig.autoGalleryAdapter);
         autoGalleryConfig.autoGallery.setLength(autoGalleryConfig.dateList.size());
         autoGalleryConfig.autoGallery.setDuration(autoGalleryConfig.duration);
         if( autoGalleryConfig.isAutoScroll){
            autoGalleryConfig.autoGallery.setAutoScroll();
         }

         if(autoGalleryConfig.pageGuide != null){

            autoGalleryConfig.pageGuide.setRes(autoGalleryConfig.indicationOn,
                    autoGalleryConfig.indicationOff);

            autoGalleryConfig.pageGuide.setParams(autoGalleryConfig.dateList.size(),
                    autoGalleryConfig.indicationW_H_size,
                    autoGalleryConfig.indicationW_H_size);

            autoGalleryConfig.autoGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  if(autoGalleryConfig.dateList.size() > 0){
                     autoGalleryConfig.pageGuide.setSelect(position % autoGalleryConfig.dateList.size());
                  }
               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
            });

            autoGalleryConfig.autoGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   if(autoGalleryConfig.onItemClickListen != null){
                       int index =  position % autoGalleryConfig.dateList.size();
                       autoGalleryConfig.onItemClickListen.onItemClick(index);
                   }
               }
            });
         }


         return autoGalleryConfig;
      }

   }

   public interface OnItemClickListen{
      public void onItemClick(int position);
   }

}
