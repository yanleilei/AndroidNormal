package com.leilei.androidframeworkdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.leilei.androidnormal.BaseActivity;
import com.leilei.androidnormal.net.SendMessage;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showProgress();

        TestNetTask task = new TestNetTask(this);
        SendMessage message = SendMessage.create().url("https://www.baidu.com");
        task.load(message);
        mNetTasks.add(task);
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
