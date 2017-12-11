package com.leilei.androidnormal;

import android.os.Environment;

import com.leilei.androidlib.Utils;

import java.io.File;

/**
 * Created by leilei on 2017/12/11.
 */

public class CacheRootDir {
    public static boolean hasSdcardPermission = false;
    private static CacheRootDir mCacheDir = new CacheRootDir();

    private CacheRootDir() {

    }

    public static CacheRootDir getInstance() {
        return mCacheDir;
    }

    public File getRootFile() {
        if (hasSdcardPermission) {
            return Environment.getExternalStorageDirectory();
        } else {
            return Utils.mContext.getCacheDir();
        }
    }


}
