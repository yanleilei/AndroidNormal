package com.leilei.androidframeworkdemo;

import android.app.Application;


import com.leilei.androidlib.UncaughtExceptionHandler;
import com.leilei.androidlib.Utils;
import com.leilei.androidnormal.user.UserManager;

/**
 * Created by leilei on 2017/12/14.
 */

public class App extends Application {
    public static UserManager<UserModel> mUserManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this, BuildConfig.DEBUG);
        UncaughtExceptionHandler.init(this, "");
        mUserManager = new UserManager<>(UserModel.class);
    }
}
