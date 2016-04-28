package com.cyou.ui.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.cyou.ui.PhotoDraweeView;
import com.cyou.ui.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lzw on 14-9-21.
 */
public class PhotoViewActivity extends Activity {

    private PhotoDraweeView myPhotoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photoview_activity);
        myPhotoView = (PhotoDraweeView) findViewById(R.id.photoView);

        final int widthPixels = getResources().getDisplayMetrics().widthPixels;
        final int heightPixels = getResources().getDisplayMetrics().heightPixels;

        try {
            InputStream open = getAssets().open("aa.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(open);
//            myPhotoView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            myPhotoView.setImageBitmap(bitmap);
            myPhotoView.setImageUri("http://img5.duitang.com/uploads/item/201511/04/20151104214718_FfnST.jpeg"
                    , null);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
