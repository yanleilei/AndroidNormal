package com.leilei.androidnormal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leilei.androidnormal.net.AbstractNetTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leilei on 2017/11/27.
 */

public abstract class BaseFragment extends Fragment {
    protected List<AbstractNetTask> mNetTasks;
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), null);
            mNetTasks = new ArrayList<>();
            initView();
            initData();
        } else {
            ViewGroup parent = (ViewGroup) mView.getParent();
            parent.removeAllViews();
        }

        return mView;
    }

    private void initData() {

    }

    private void initView() {

    }

    protected abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (AbstractNetTask task : mNetTasks) {
            task.cancel();
        }
        mNetTasks.clear();
        mNetTasks = null;
    }
}
