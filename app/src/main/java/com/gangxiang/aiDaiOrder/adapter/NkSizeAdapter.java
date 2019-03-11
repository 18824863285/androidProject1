package com.gangxiang.aiDaiOrder.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.gangxiang.aiDaiOrder.R;
import com.gangxiang.aiDaiOrder.bean.NkProduct;
import com.gangxiang.aiDaiOrder.util.EmptyCheck;
import com.gangxiang.aiDaiOrder.util.ToastUtils;

public class NkSizeAdapter extends BaseQuickAdapter<NkProduct.DataBean.SizeBean>{

    private OnClickListen mOnClickListen;

    public NkSizeAdapter(Context context) {
        super(context);
    }

    public void setOnClickListen(OnClickListen onClickListen) {
        this.mOnClickListen = onClickListen;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.item_nk_size;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final NkProduct.DataBean.SizeBean item) {
       holder.setText(R.id.sp_num,item.getPSNumber()+"");
       holder.setText(R.id.color,item.getPPColor());
       int buyNum = Integer.valueOf(item.getPSNumber());
       if(buyNum > 0){
           holder.setBackgroundRes(R.id.color,R.drawable.bg_cc1);
           holder.setTextColor(R.id.color, Color.WHITE);
           holder.setTextColor(R.id.sp_num, Color.parseColor("#DAB866"));
       }else {
           holder.setBackgroundRes(R.id.color,R.drawable.bg_cc2);
           holder.setTextColor(R.id.color, Color.parseColor("#313133"));
           holder.setTextColor(R.id.sp_num, Color.parseColor("#313133"));
       }

       holder.setOnClickListener(R.id.jia, new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               item.setPSNumber((Integer.valueOf(item.getPSNumber())+1)+"");
               holder.setText(R.id.sp_num,item.getPSNumber()+"");
               holder.setBackgroundRes(R.id.color,R.drawable.bg_cc1);
               holder.setTextColor(R.id.color, Color.WHITE);
               if(mOnClickListen != null){
                   mOnClickListen.changeNum(item.getId());
               }
           }
       });

        holder.setOnClickListener(R.id.jian, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.valueOf(item.getPSNumber()) > 0){
                    if(Integer.valueOf(item.getPSNumber()) == 1){
                        holder.setBackgroundRes(R.id.color,R.drawable.bg_cc2);
                        holder.setTextColor(R.id.color, Color.parseColor("#313133"));
                    }else {
                        holder.setBackgroundRes(R.id.color,R.drawable.bg_cc1);
                        holder.setTextColor(R.id.color, Color.WHITE);
                    }
                    item.setPSNumber((Integer.valueOf(item.getPSNumber())-1)+"");
                    holder.setText(R.id.sp_num,item.getPSNumber()+"");
                    if(mOnClickListen != null){
                        mOnClickListen.changeNum(item.getId());
                    }
                }
            }
        });


        ((EditText)holder.getView(R.id.sp_num)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String num = ((EditText)holder.getView(R.id.sp_num)).getText().toString();
                if(EmptyCheck.isEmpty(num)){
                    return;
                }
                if(num.length() > 1 && num.startsWith("0")){
                    ToastUtils.showLong(mContext,R.string.skktbkw0);
                    ((EditText)holder.getView(R.id.sp_num)).setText("0");
                    item.setPSNumber(String.valueOf(0));
                }else {
                    if(mOnClickListen != null){
                        item.setPSNumber(num);
                        mOnClickListen.changeBuInput();
                    }
                }

                if(Integer.valueOf(item.getPSNumber()) == 0 ){
                    holder.setTextColor(R.id.sp_num, Color.parseColor("#313133"));
                }else {
                    holder.setTextColor(R.id.sp_num, Color.parseColor("#DAB866"));
                }
            }
        });

    }

    public interface OnClickListen{
        public void changeNum(String id);
        public void changeBuInput();
    }

}
