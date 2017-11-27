package com.leilei.androidnormal.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leilei on 2017/11/9.
 */

public class SendMessage {
    private String mUrl;
    private int mTag;
    private Map<String, String> mHeader;
    private Map<String, String> mParam;
    private String mAction;

    public static SendMessage create() {
        SendMessage message = new SendMessage();
        message.mHeader = new HashMap<>();
        message.mParam = new HashMap<>();
        return message;
    }

    public SendMessage url(String url) {
        mUrl = url;
        return this;
    }

    public SendMessage tag(int tag) {
        mTag = tag;
        return this;
    }

    public SendMessage header(String key, String value) {
        mHeader.put(key, value);
        return this;
    }

    public SendMessage param(String key, String value) {
        mParam.put(key, value);
        return this;
    }

    public SendMessage action(String action) {
        mAction = action;
        return this;
    }

    public int getTag() {
        return mTag;
    }

    public Map<String, String> getHeader() {
        return mHeader;
    }

    public Map<String, String> getParam() {
        return mParam;
    }

    public String getAction() {
        return mAction;
    }

    public String getUrl() {
        return mUrl;
    }
}
