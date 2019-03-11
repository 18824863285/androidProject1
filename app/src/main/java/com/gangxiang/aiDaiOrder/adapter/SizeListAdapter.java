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
import com.gangxiang.aiDaiOrder.bean.Product;
import com.gangxiang.aiDaiOrder.util.EmptyCheck;
import com.gangxiang.aiDaiOrder.util.MessageManager;
import com.gangxiang.aiDaiOrder.util.ToastUtils;

public class SizeListAdapter extends BaseQuickAdapter<Product.DataBean.ColorBean.CupBean.SizeBean>{

    private OnClickListen mOnClickListen;

    public SizeListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.item_size_list;
    }

    public void setOnClickListen(OnClickListen onClickListen) {
        this.mOnClickListen = onClickListen;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final Product.DataBean.ColorBean.CupBean.SizeBean item) {
       switch (item.getPSSize()){
           case "70":
               holder.setText(R.id.tv_sice,"70/S");
               break;
           case "75":
               holder.setText(R.id.tv_sice,"75/M");
               break;
           case "80":
               holder.setText(R.id.tv_sice,"80/L");
               break;
           case "85":
               holder.setText(R.id.tv_sice,"85/XL");
               break;
           case "90":
               holder.setText(R.id.tv_sice,"90/XXL");
               break;
           case "95":
               holder.setText(R.id.tv_sice,"95/XXXL");
               break;
           default:
               break;
       }

       holder.setOnClickListener(R.id.jian, new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int num = Integer.valueOf(item.getPSNumber());
               if(num >= 1){
                   item.setPSNumber(String.valueOf(num - 1));
                   holder.setText(R.id.sp_num,item.getPSNumber());
                   if(Integer.valueOf(item.getPSNumber()) == 0 ){
                       holder.setTextColor(R.id.sp_num, Color.parseColor("#666666"));
                   }else {
                       holder.setTextColor(R.id.sp_num, Color.parseColor("#DAB866"));
                   }

                   if(mOnClickListen != null){
                       mOnClickListen.del();
                   }

               }
           }
       });

        holder.setOnClickListener(R.id.jia, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.valueOf(item.getPSNumber());
                item.setPSNumber(String.valueOf(++num));
                holder.setText(R.id.sp_num,item.getPSNumber());
                holder.setTextColor(R.id.sp_num, Color.parseColor("#DAB866"));
                //MessageManager.getInstance().sendMessage(MessageManager.MessageType_update_number_jia);
                if(mOnClickListen != null){
                    mOnClickListen.add();
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
                        mOnClickListen.change(Integer.valueOf(num));
                    }
                }

                if(Integer.valueOf(item.getPSNumber()) == 0 ){
                    holder.setTextColor(R.id.sp_num, Color.parseColor("#666666"));
                }else {
                    holder.setTextColor(R.id.sp_num, Color.parseColor("#DAB866"));
                }
            }
        });

       holder.setText(R.id.sp_num,item.getPSNumber());
       if(Integer.valueOf(item.getPSNumber()) == 0 ){
           holder.setTextColor(R.id.sp_num, Color.parseColor("#666666"));
       }else {
           holder.setTextColor(R.id.sp_num, Color.parseColor("#DAB866"));
       }
    }

    public interface OnClickListen{
        public void add();
        public void del();
        public void change(int num);
    }

}
