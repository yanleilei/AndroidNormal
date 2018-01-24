package com.leilei.androidframeworkdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.leilei.androidnormal.BaseActivity;
import com.leilei.androidnormal.net.SendMessage;

public class MainActivity extends BaseActivity {
    private TextView mUserId, mUserName;
    private Button mLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showProgress();

        TestNetTask task = new TestNetTask(this);
        SendMessage message = SendMessage.create().url("https://www.baidu.com");
        message.param("a", "aaa");
        message.param("b", "bbb");
        task.load(message);
        mNetTasks.add(task);


        mUserId = findViewById(R.id.userId);
        mUserName = findViewById(R.id.username);
        mLogin = findViewById(R.id.login);

        if (App.mUserManager.isLogin()) {
            mUserId.setVisibility(View.VISIBLE);
            mUserName.setVisibility(View.VISIBLE);
            mUserId.setText(App.mUserManager.getUserModel().getId());
            mUserName.setText(App.mUserManager.getUserModel().getName());
            mLogin.setText("退出");
        } else {
            mUserId.setVisibility(View.GONE);
            mUserName.setVisibility(View.GONE);
            mLogin.setText("登陆");
        }
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (App.mUserManager.isLogin()) {
                    App.mUserManager.logout();
                } else {
                    UserModel userModel = new UserModel();
                    userModel.setId("12");
                    userModel.setName("Adfads");
                    App.mUserManager.login(userModel);
                }
            }
        });

    }

    @Override
    public void onSuccess(int tag, String result) {
        super.onSuccess(tag, result);
        closeProgress();
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }
}
