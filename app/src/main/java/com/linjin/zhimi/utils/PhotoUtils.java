package com.linjin.zhimi.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import com.linjin.zhimi.AppConstants;

import java.io.File;
import java.io.IOException;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/22
 **/

public class PhotoUtils {

    public static final int HEAD_PIC_WIDTH = 150;
    public static final int HEAD_PIC_HEIGHT = HEAD_PIC_WIDTH;
    public static final int COVER_PIC_WIDTH = 230;
    public static final int COVER_PIC_HEIGHT = COVER_PIC_WIDTH;

    public static final int HEAD_PIC = 1;
    public static final int COVER_PIC = 2;
    public static final int NOTICE_PIC = 3;


    private Uri cameraOutPutUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp_camera.jpg"));
    private Uri cropOutPutUri;

    /**
     * 系统相册
     */
    public static final int REQUEST_CODE_CHOOSE_PHOTO_KITKAT_LESS = 10;
    /**
     * 系统照相机
     */
    public static final int REQUEST_CODE_CAMERA = 11;
    public static final int REQUEST_CODE_CROP = 12;
    public static final int REQUEST_CODE_CHOOSE_PHOTO_KITKAT_ABOVE = 13;

    /**
     * 启动相册
     */
    public static void startGallery(final Activity act, int type) {
        Intent intent = new Intent();
        intent.setType("image/*");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            intent.setAction(Intent.ACTION_GET_CONTENT);
            act.startActivityForResult(intent, REQUEST_CODE_CHOOSE_PHOTO_KITKAT_LESS);
        } else {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            act.startActivityForResult(intent, REQUEST_CODE_CHOOSE_PHOTO_KITKAT_ABOVE);
        }
    }

    /**
     * 启动照相机
     */
    public void startCamera(final Activity act, int type) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        LogUtils.i("startCamera", "startCamera " + cameraOutPutUri.toString());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraOutPutUri);
        act.startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    /**
     * 裁剪
     *
     * @param act
     */
    public void startCrop(Activity act, Uri formUri, Uri toUri, int type) {
        cropOutPutUri = getTempUri();
        LogUtils.i("startCrop", "cropOutPutUri= " + cropOutPutUri.toString());
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(formUri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);//输出是X方向的比例
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        if (HEAD_PIC == type) {
            intent.putExtra("outputX", HEAD_PIC_WIDTH);//输出X方向的像素
            intent.putExtra("outputY", HEAD_PIC_HEIGHT);
        } else {
            intent.putExtra("outputX", COVER_PIC_WIDTH);//输出X方向的像素
            intent.putExtra("outputY", COVER_PIC_HEIGHT);
        }
        intent.putExtra("return-data", false);//设置为不返回数据
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropOutPutUri);
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        act.startActivityForResult(intent, REQUEST_CODE_CROP);
    }

    public void setBitmapFromResult(Activity act, RefreshImage refreshImage, int requestCode, Intent data, int type) {
        if (requestCode == REQUEST_CODE_CAMERA) {
            if (cameraOutPutUri != null) {
                startCrop(act, cameraOutPutUri, cropOutPutUri, type);
            }
        } else if (requestCode == REQUEST_CODE_CHOOSE_PHOTO_KITKAT_LESS) {
            if (data != null) {
                Uri uri = data.getData();
                startCrop(act, uri, cropOutPutUri, type);
            }
        } else if (requestCode == REQUEST_CODE_CHOOSE_PHOTO_KITKAT_ABOVE) {
            if (data != null) {
                Uri uri = data.getData();
                String thePath = FileUtils.getPath(act, uri);
                if (thePath != null)
                    uri = Uri.fromFile(new File(thePath));
                startCrop(act, uri, cropOutPutUri, type);
            }
        } else if (requestCode == REQUEST_CODE_CROP) {
            refreshImage.refresh(cropOutPutUri);
//            if (HEAD_PIC == type) {
//                ((RefreshImage) act).refresh(cropOutPutUri);
////            } else if (COVER_PIC == type) {
////                ((GroupCreateActivity) act).refreshGroupCover(cropOutPutUri);
////            } else if (NOTICE_PIC == type) {
////                ((NoticeCreateActivity) act).refreshNoticePic(cropOutPutUri);
//            }
        }
    }


    /**
     * 获得临时uri
     *
     * @return
     */
    public Uri getTempUri() {
        String strPath = Environment.getExternalStorageDirectory().toString();
        String fileName = System.currentTimeMillis() + ".jpg";
        String filePath = strPath + AppConstants.IMAGE_PATH;
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        deleteAllFiles(dir);
        File file = new File(filePath, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LogUtils.i("TempUri", "filePath= " + filePath + "  fileName= " + fileName);
        return Uri.fromFile(file);
    }

    public void deleteAllFiles(File dir) {
        File count[] = dir.listFiles();
        if (count.length > 30) {
            for (int i = 0; i < count.length; i++) {
                count[i].delete();
            }
        }
    }

    public interface RefreshImage {

        void refresh(Uri uri);
    }
}
