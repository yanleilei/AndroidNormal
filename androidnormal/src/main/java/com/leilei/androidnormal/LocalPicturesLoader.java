package com.leilei.androidnormal;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leilei on 2017/10/16.
 */

public class LocalPicturesLoader {

    private static final String ALL_NAME = "all";
    private Map<String, List<String>> mLocalFolderList;

    public void load(final Context context, final LoadImageCallback callback) {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (mLocalFolderList == null) {
                    mLocalFolderList = new LinkedHashMap<>();
                } else {
                    mLocalFolderList.clear();
                }

                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

                ContentResolver mContentResolver = context
                        .getContentResolver();

                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);

                if (mCursor == null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callback != null) {
                                callback.onLoadError();
                            }
                        }
                    });

                    return;
                }


                List<String> allList = new ArrayList<>();
                mLocalFolderList.put(ALL_NAME, allList);
                while (mCursor.moveToNext()) {
                    if (MediaStore.Images.Media.DATA.length() == 0) {
                        break;
                    }
                    // 获取图片的路径
                    int index = mCursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    if (index < 0) {
                        continue;
                    }
                    String path = mCursor.getString(index);
                    if (path == null || path.length() == 0) {
                        continue;
                    }
                    allList.add(0, path);
                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    if (mLocalFolderList.get(parentPath) == null) {
                        List<String> list = new ArrayList<>();
                        list.add(0, path);
                        mLocalFolderList.put(parentPath, list);
                    } else {
                        mLocalFolderList.get(parentPath).add(0, path);
                    }
                }
                mCursor.close();
                // 通知Handler扫描图片完成
                if (callback != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onLoadSuccess(mLocalFolderList.get(ALL_NAME));
                        }
                    });

                }
            }
        }).start();
    }

    public interface LoadImageCallback {
        void onLoadSuccess(List<String> images);

        void onLoadError();
    }


}
