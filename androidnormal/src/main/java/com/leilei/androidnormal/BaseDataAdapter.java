package com.leilei.androidnormal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leilei on 2017/11/27.
 */

public abstract class BaseDataAdapter<T> extends BaseAdapter {
    protected List<T> mList;
    protected Context mContext;

    public BaseDataAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = newView(position, mList.get(position));
        }
        bindView(position, convertView, mList.get(position));

        return convertView;
    }

    protected abstract View newView(int position, T t);

    protected abstract void bindView(int position, View view, T t);

    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> list) {
        mList = list;
    }

    public void addAll(List<T> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void add(T t) {
        mList.add(t);
        notifyDataSetChanged();
    }

    public void remove(T t) {
        mList.remove(t);
        notifyDataSetChanged();
    }
}
