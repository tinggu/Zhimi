package com.cyou.ui;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/31
 **/

public class VideoPlayVIew 
        extends LinearLayout 
        implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    private VideoView videoView;
    private MediaController controller;
    private Context context;
    /**
     * 视频源
     */
    private Uri uri;

    public VideoPlayVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public VideoPlayVIew(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoPlayVIew(Context context) {
        this(context, null);
    }

    private void init() {
        videoView = new VideoView(context);
        controller = new MediaController(context);
        videoView.setMediaController(controller);
    }

    private void play() {
        videoView.setVideoURI(uri);
        videoView.start();
    }

    private void stop() {
        videoView.stopPlayback();
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        //播放完毕,重置标志
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        try {
            if (mp != null)
                mp.reset();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
