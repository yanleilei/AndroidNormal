package com.leilei.androidnormal;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.leilei.androidlib.PermissionHelper;
import com.leilei.androidnormal.net.AbstractNetTask;
import com.leilei.androidnormal.net.NetWorkCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leilei on 2017/11/27.
 */

public abstract class BaseActivity extends AppCompatActivity implements PermissionHelper.PermissionCallback, NetWorkCallback {
    protected List<AbstractNetTask> mNetTasks;
    protected PermissionHelper mPermissionHelper;
    protected Dialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preInit(getIntent());
        mNetTasks = new ArrayList<>();
        mPermissionHelper = new PermissionHelper(this);
        setContentView(getContentId());
        mProgressDialog = new ProgressDialog(this);
    }

    /**
     * 初始化一些数据
     */
    protected void initData() {

    }

    /**
     * 初始化一些View
     */
    protected void initView() {

    }

    public void setProgressDialog(Dialog progressDialog) {
        mProgressDialog = progressDialog;
    }

    /**
     * 处理数据传递
     *
     * @param intent
     */
    protected void preInit(Intent intent) {

    }

    public void showProgress() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void closeProgress() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 设置View
     *
     * @return
     */
    protected abstract int getContentId();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermissionHelper != null) {
            mPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPermissionHelper != null) {
            mPermissionHelper.destory();
            mPermissionHelper = null;
        }
        if (mNetTasks.size() > 0) {
            for (AbstractNetTask task : mNetTasks) {
                task.cancel();
            }
            mNetTasks.clear();
            mNetTasks = null;
        }
    }

    @Override
    public void onPermissionSuccess() {

    }

    @Override
    public void onPermissionRefuse(String[] refusePermissions) {

    }

    @Override
    public void onSuccess(int tag, String result) {

    }

    @Override
    public void onNetError(int tag, Exception e) {

    }

    @Override
    public void onDataError(int tag, String message) {

    }

    @Override
    public void onServerError(int tag, int httpCode, String result) {

    }
}
