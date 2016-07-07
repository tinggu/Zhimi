package com.cyou.app.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpFragment;
import com.cyou.quick.mvp.MvpView;
import com.cyou.quick.mvp.delegate.FragmentMvpDelegate;
import com.cyou.quick.mvp.delegate.MvpDelegateCallback;
import com.linjin.zhimi.base.delegate.BaseFragamentDelegateImpl;
import com.linjin.zhimi.utils.TrackUtils;

import io.realm.Realm;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/1/28 11:47
 */
public abstract class BaseMvpFragment<V extends MvpView, P extends MvpBasePresenter<V>>
        extends MvpFragment<V, P> {

    protected Realm realm;
    
    @Override
    public void onResume() {
        super.onResume();
        TrackUtils.getInstance().onPageStart(this.getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        TrackUtils.getInstance().onPageEnd(this.getClass().getSimpleName());
    }
    
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (realm != null) {
            realm.close();
        }
    }

    protected void toast(String str) {
        Toast.makeText(this.getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int id) {
        Toast.makeText(this.getActivity(), id, Toast.LENGTH_SHORT).show();
    }
    

}
