package com.gangxiang.aiDaiOrder.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Created by Administrator on 2017/5/3.
 */

public class PermissionUtil {
     public static boolean isPermissionGranted(String permissionName, int questCode, Activity activity){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        //判断是否需要请求允许权限
        int hasPermision = activity.checkSelfPermission(permissionName);
        if (hasPermision != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[] { permissionName }, questCode);
            return false;
        }
        return true;
    }
}
