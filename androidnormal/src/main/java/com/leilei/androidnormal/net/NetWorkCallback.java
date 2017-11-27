package com.leilei.androidnormal.net;

/**
 * Created by leilei on 2017/11/9.
 */

public interface NetWorkCallback {
    void onSuccess(int tag, String result);

    void onNetError(int tag, Exception e);
}
