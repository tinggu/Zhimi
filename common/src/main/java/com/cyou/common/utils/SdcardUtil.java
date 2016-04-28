package com.cyou.common.utils;

import android.os.Environment;

import java.io.File;

public class SdcardUtil {

    public static final String mExternalDir = "group";

    public static final String PHOTO_CACHE_DIR = "photo";

    public static boolean isValidExternalStorage() {

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            try {
                String sdcardPath = FileUtils.addSeparator(Environment.getExternalStorageDirectory().getPath())
                        + mExternalDir;
                sdcardPath = FileUtils.addSeparator(sdcardPath);
                File dir = new File(sdcardPath);
                if (dir != null) {
                    if (dir.exists() && dir.canWrite())
                        return true;
                    else {
                        boolean _ret = dir.mkdir();
                        if (false == _ret)
                            return false;

                        if (dir.exists() && dir.canWrite())
                            return true;
                    }
                }
            } catch (Exception e) {
            }
        }

        return false;
    }

    public static String getPhotoCacheDir() {
        if (!isValidExternalStorage()) {
            return null;
        }
        String dirUrl = Environment.getExternalStorageDirectory() +
                File.separator + mExternalDir + File.separator +
                PHOTO_CACHE_DIR + File.separator;
        File dir = new File(dirUrl);
        if (dir != null) {
            if (dir.exists() && dir.canWrite())
                return dirUrl;
            else {
                boolean _ret = dir.mkdir();
                if (dir.exists() && dir.canWrite())
                    return dirUrl;
            }
        }
        return getExternalStoragePath();
    }


    public static String getExternalStoragePath() {
        if (!isValidExternalStorage()) {
            return null;
        }
        return FileUtils.addSeparator(Environment.getExternalStorageDirectory().getPath());
    }

}
