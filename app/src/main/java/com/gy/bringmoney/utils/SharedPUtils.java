package com.gy.bringmoney.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.gy.bringmoney.bean.NoteBean;
import com.gy.bringmoney.bean.UserBean;
import com.google.gson.Gson;

/**
 * Created by zhouas666 on 2017/12/25.
 */
public class SharedPUtils {

    /**
     * 获取当前用户
     */
    public static UserBean getCurrentUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        if (sp != null) {
            Gson gson=new Gson();
            return gson.fromJson(sp.getString("jsonStr", null),UserBean.class);
        }
        return null;
    }

    /**
     * 设置当前用户
     */
    public static void setCurrentUser(Context context,String jsonStr) {
        Gson gson = new Gson();
        UserBean userBean = gson.fromJson(jsonStr, UserBean.class);
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", userBean.getUsername());
        editor.putString("email", userBean.getMail());
        editor.putInt("id", userBean.getId());
        editor.putString("jsonStr", jsonStr);
        editor.commit();
    }

    /**
     * 设置当前用户
     */
    public static void setCurrentUser(Context context,UserBean userBean) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(userBean);
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", userBean.getUsername());
        editor.putString("email", userBean.getMail());
        editor.putInt("id", userBean.getId());
        editor.putString("jsonStr", jsonStr);
        editor.commit();
    }

    /**
     * 获取当前用户账单分类信息
     */
    public static NoteBean getUserNoteBean(Context context) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        if (sp != null) {
            String jsonStr=sp.getString("noteBean", null);
            if (jsonStr!=null){
                Gson gson=new Gson();
                return gson.fromJson(jsonStr,NoteBean.class);
            }
        }
        return null;
    }

    /**
     * 设置当前用户账单分类信息
     */
    public static void setUserNoteBean(Context context,String jsonStr) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("noteBean", jsonStr);
        editor.commit();
    }

    /**
     * 设置当前用户账单分类信息
     */
    public static void setUserNoteBean(Context context,NoteBean noteBean) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(noteBean);
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("noteBean", jsonStr);
        editor.commit();
    }

    /**
     * 获取当前用户头像
     */
    public static String getCurrentTheme(Context context) {
        SharedPreferences sp = context.getSharedPreferences("userSetting", Context.MODE_PRIVATE);
        if (sp != null)
            return sp.getString("theme", "少女红");
        return null;
    }

    /**
     * 设置当前用户头像
     */
    public static void setCurrentTheme(Context context,String theme) {
        SharedPreferences sp = context.getSharedPreferences("userSetting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (editor != null) {
            editor.putString("theme", theme);
            editor.commit();
        }
    }
}
