package com.leilei.androidnormal.user;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.leilei.androidlib.LogUtils;
import com.leilei.androidlib.SharedPreferenceUtils;

/**
 * Created by leilei on 2017/12/14.
 */

public class UserManager<T> {
    private static final String TAG = "UserManager";
    public static String FILE = "user_info";
    public static String KEY = "user_message";
    public static String IS_LOGIN = "user_is_login";
    private T mModel;

    public UserManager(Class<T> tClass) {
        String user_message = SharedPreferenceUtils.getString(FILE, KEY);
        if (!TextUtils.isEmpty(user_message)) {
            mModel = new Gson().fromJson(user_message, tClass);
        }
    }

    public boolean isLogin() {
        return SharedPreferenceUtils.getBoolean(FILE, IS_LOGIN);
    }

    public T getUserModel() {
        return mModel;
    }

    public void login(T t) {
        if (t == null) {
            LogUtils.e(TAG, "Login Model is Null");
            return;
        }
        if (SharedPreferenceUtils.getBoolean(FILE, IS_LOGIN)) {
            LogUtils.e(TAG, "Can't Login Again");
            return;
        }
        mModel = t;
        String user_message = new Gson().toJson(t);
        SharedPreferenceUtils.saveString(FILE, KEY, user_message);
        SharedPreferenceUtils.saveBoolean(FILE, IS_LOGIN, true);

    }

    public void save(T t) {
        if (t == null) {
            LogUtils.e(TAG, "Login Model is Null");
            return;
        }
        mModel = t;
        String user_message = new Gson().toJson(t);
        SharedPreferenceUtils.saveString(FILE, KEY, user_message);

    }

    public void logout() {
        SharedPreferenceUtils.saveBoolean(FILE, IS_LOGIN, false);
    }


}
