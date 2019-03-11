package com.gangxiang.aiDaiOrder.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/20.
 */

public class TimeUtil {

    public static String getCurrentDate(){
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String time=format.format(date);
        return time;
    }

    public static String getCurrentDate1(){
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        return time;
    }

    public static long getTimeStamp(String time){
        System.out.println("====>time:"+time);
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
             date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String getDate(String time){
       String result = null;
       long timeStamp =  getTimeStamp(time);
       return getDateToString(timeStamp,"MM月dd日 HH:mm");
    }

    public static String getDate1(String time){
        String result = null;
        long timeStamp =  getTimeStamp(time);
        return getDateToString(timeStamp,"MM.dd HH:mm:ss");
    }

    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static long getTimeStamp1(String time){
        System.out.println("====>time:"+time);
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static  long dateDiff(String startTime, String endTime, String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min
                    + "分钟" + sec + "秒。");
            return day;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getAfterDate(String currentDate,int afterDay, String format){
        String result = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        SimpleDateFormat format1 =  new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format1.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, afterDay);
        result = sdf.format(c.getTime());
        return result;
    };
}
