package com.gangxiang.aiDaiOrder.widght;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.gangxiang.aiDaiOrder.R;
import java.util.List;

public class FragmentFrameLayout extends FrameLayout{

    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private int currIndex;

    public FragmentFrameLayout(@NonNull Context context) {
        this(context,null);
        //this.addView(View.inflate(context, R.layout.view_flamayout,null));
    }

    public FragmentFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public FragmentFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    private void dismissAllFragment(){
        if(fragmentList != null && fragmentList.size() > 0){
            for (int i = 0 ; i < fragmentList.size() ;i ++ ){
                fragmentTransaction.hide(fragmentList.get(i));
            }
        }
    }

    public void select(int index){
        fragmentTransaction = fragmentManager.beginTransaction();
        dismissAllFragment();
        fragmentTransaction.show(fragmentList.get(index));
    }

}
