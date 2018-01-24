package com.leilei.androidnormal.net;

import android.text.TextUtils;

import com.leilei.androidlib.LogUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by leilei on 2017/11/9.
 */

public abstract class AbstractNetTask implements NetTask, Callback {
    protected static final String TAG = "HTTP";
    protected int mTag;
    protected static OkHttpClient mOkhttpClient;
    protected boolean mCanceled;
    protected NetWorkCallback mNetWorkCallback;
    private Call mCall;

    public AbstractNetTask(NetWorkCallback callback) {
        mNetWorkCallback = callback;
        if (mOkhttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.writeTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(10, TimeUnit.SECONDS);
            mOkhttpClient = builder.build();
        }
    }

    protected void postForm(SendMessage message) {
        Request.Builder builder = createNormal(message);
        String url = message.getUrl();
        if (!TextUtils.isEmpty(message.getAction())) {
            url = url + message.getAction();
        }
        builder.url(url);
        LogUtils.i(TAG, "POST URL=" + url);
        if (message.getParam() != null && message.getParam().size() != 0) {
            LogUtils.i(TAG, "POST FORM=" + message.getParam());
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : message.getParam().entrySet()) {
                bodyBuilder.add(entry.getKey(), entry.getValue());
            }
            builder.post(bodyBuilder.build());
        }
        sendReque(builder.build());
    }

    protected void get(SendMessage message) {
        Request.Builder builder = createNormal(message);
        String url = message.getUrl();
        if (!TextUtils.isEmpty(message.getAction())) {
            url = url + message.getAction();
        }
        if (message.getParam() != null && message.getParam().size() != 0) {
            if (!url.contains("?")) {
                url = url + "?";
            } else {
                url = url + "&";
            }
            StringBuilder sb = new StringBuilder(url);
            for (Map.Entry<String, String> value : message.getParam().entrySet()) {
                sb.append(value.getKey() + "=" + value.getValue() + "&");
            }
            sb.deleteCharAt(sb.length() - 1);
            url = sb.toString();
        }

        LogUtils.i(TAG, "GET URL=" + url);

        builder.url(url);
        sendReque(builder.build());
    }

    private void sendReque(Request request) {
        mCall = mOkhttpClient.newCall(request);
        mCall.enqueue(this);
    }

    private Request.Builder createNormal(SendMessage message) {
        Request.Builder builder = new Request.Builder();
        LogUtils.i(TAG, "Header=" + message.getHeader());
        if (message.getHeader() != null && message.getHeader().size() > 0) {
            for (Map.Entry<String, String> header : message.getHeader().entrySet()) {
                builder.header(header.getKey(), header.getValue());
            }
        }
        return builder;
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        int statusCode = response.code();
        if (statusCode == 200) {
            if (!mCanceled && mNetWorkCallback != null) {
                String result = response.body().string();
                LogUtils.i(TAG, result);
                mNetWorkCallback.onSuccess(mTag, result);
            }
        } else {
            String message = "Exception:tag =" + mTag + "  HttpResponseCode=" + statusCode;
            LogUtils.e(TAG, message);
            mNetWorkCallback.onNetError(mTag, new Exception(message));
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {
        if (!mCanceled && mNetWorkCallback != null) {
            mNetWorkCallback.onNetError(mTag, e);
        }
    }

    @Override
    public void cancel() {
        mCanceled = true;
        mNetWorkCallback = null;
    }
}


