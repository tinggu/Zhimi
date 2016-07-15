package com.cyou.app.mvp;

import android.widget.Toast;

import com.cyou.quick.QuickApplication;
import com.cyou.quick.mvp.MvpActivity;
import com.cyou.quick.mvp.MvpPresenter;
import com.cyou.quick.mvp.MvpView;
import com.cyou.common.utils.TrackUtils;

//import io.realm.Realm;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia
 * Date       : 2015/12/21
 **/

public abstract class BaseMvpActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends MvpActivity<V, P> {
    
//    protected Realm realm; 
    
    @Override
    public void onResume() {
        super.onResume();
        TrackUtils.getInstance().onResume(this);
        TrackUtils.getInstance().onPageStart(getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        TrackUtils.getInstance().onPause(QuickApplication.getInstance());
        TrackUtils.getInstance().onPageEnd(getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (realm != null) {
//            realm.close();
//        }
    }

    protected void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }

}
