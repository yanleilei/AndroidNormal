package com.leilei.androidnormal.net;

/**
 * Created by leilei on 2017/11/9.
 */

public interface NetWorkCallback {
    void onSuccess(int tag, String result);

    //Http code 不是200的回调
    void onServerError(int tag, int httpCode, String result);

    //Http code正确，但是数据解析失败回调
    void onDataError(int tag, String message);

    //网络失败
    void onNetError(int tag, Exception e);
}
