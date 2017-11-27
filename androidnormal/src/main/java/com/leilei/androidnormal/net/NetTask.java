package com.leilei.androidnormal.net;

/**
 * Created by leilei on 2017/11/9.
 */

public interface NetTask {
    void load(SendMessage message);

    void cancel();
}
