package com.leilei.androidframeworkdemo;

import com.leilei.androidnormal.net.AbstractNetTask;
import com.leilei.androidnormal.net.NetWorkCallback;
import com.leilei.androidnormal.net.SendMessage;

/**
 * Created by leilei on 2017/11/27.
 */

public class TestNetTask extends AbstractNetTask {


    public TestNetTask(NetWorkCallback callback) {
        super(callback);
    }

    @Override
    public void load(SendMessage message) {
        get(message);
    }
}
