package com.gangxiang.aiDaiOrder.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 引用项目:https://github.com/openproject/LessCode
 * 不习惯$符号的方法名,所以改了下；
 */
public class SharedUtils {

    public static final String SHARED_NAME = "shared_name";
    public static final String SHARED_NAME_LOGIN_RESULT = "login_result";
    public static final String SHARED_NAME_IS_CREATE_TEAM = "isCreateTeam";
    public static final String RONGYUN_TOKEN = "rongyun_token";

    private static SharedPreferences getSharedPreferences() {
        return Configuration.getInstance().getAppContext().getSharedPreferences(SHARED_NAME_LOGIN_RESULT,Context.MODE_PRIVATE);
    }

    public static SharedPreferences getSharedPreferences(String SharedPreferencesFileName) {
        return Configuration.getInstance().getAppContext().getSharedPreferences(SharedPreferencesFileName,Context.MODE_PRIVATE);
    }

    public static String getMemberId(){
        return get("Id","")+"";
    }

    public static String getToken(){
        return get("Token",0+"")+"";
    }

    public static String getMobile(){
        return get("Mobile",0+"")+"";
    }

    public static String getLoginName(){
        return get("UserName",0+"")+"";
    }

    public static boolean isLogin() {
        return !TextUtils.isEmpty(get("Id",""));
    }

    public static void clear(Context context ,String fileName)
    {
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    public static void logout(Context context){
        clear(context,SHARED_NAME_LOGIN_RESULT);
    }

    public static <T> void put(String key, T value) {
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();

        if (value == null) {
            // if value is null, just handler it as a String
            editor.putString(key, null);
        } else {
            if (value.getClass() == Boolean.class) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value.getClass() == Float.class) {
                editor.putFloat(key, (Float) value);
            } else if (value.getClass() == Integer.class) {
                editor.putInt(key, (Integer) value);
            } else if (value.getClass() == Long.class) {
                editor.putLong(key, (Long) value);
            } else if (value.getClass() == String.class) {
                editor.putString(key, (String) value);
            } else {
                throw new RuntimeException("the put value type can't support.");
            }
        }
        SharedPreferencesCompat.apply(editor);
    }

    public static String get(String key, String defaultValue) {
        SharedPreferences sp = getSharedPreferences();
        return sp.getString(key, defaultValue);
    }

    public static boolean get(String key, boolean defaultValue) {
        SharedPreferences sp = getSharedPreferences();
        return sp.getBoolean(key, defaultValue);
    }

    public static float get(String key, float defaultValue) {
        SharedPreferences sp = getSharedPreferences();
        return sp.getFloat(key, defaultValue);
    }

    public static int get(String key, int defaultValue) {
        SharedPreferences sp = getSharedPreferences();
        return sp.getInt(key, defaultValue);
    }

    public static long get(String key, long defaultValue) {
        SharedPreferences sp = getSharedPreferences();
        return sp.getLong(key, defaultValue);
    }


    public static Map<String, ?> getAll() {
        return getSharedPreferences().getAll();
    }

    public static void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);
    }

    public static void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getSharedPreferences().unregisterOnSharedPreferenceChangeListener(listener);
    }

    public static boolean contains(String key) {
        return getSharedPreferences().contains(key);
    }

    public static void remove(String key) {
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    public static void clear() {
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }


    /**
     * *********************************************************************************************
     * Unlike commit(), which writes its preferences out to persistent storage synchronously,
     * apply() commits its changes to the in-memory SharedPreferencesimmediately
     * but starts an asynchronous commit to disk and you won't be notified of any failures.
     * If another editor on this SharedPreferences does a regularcommit() while a apply() is still outstanding,
     * the commit() will block until all async commits are completed as well as the commit itself.
     * *********************************************************************************************
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * check apply mthod by reflect
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * if it has apply(), use apply() first;
         * else just use the commit().
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }


}
