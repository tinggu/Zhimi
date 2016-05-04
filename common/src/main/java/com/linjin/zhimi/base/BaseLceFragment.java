package com.linjin.zhimi.base;

import android.widget.Toast;

import com.linjin.zhimi.base.delegate.BaseFragamentDelegateImpl;
import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpFragment;
import com.cyou.quick.mvp.MvpView;
import com.cyou.quick.mvp.delegate.FragmentMvpDelegate;

import io.realm.Realm;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/1/28 11:47
 */
public abstract class BaseLceFragment<V extends MvpView, P extends MvpBasePresenter<V>>
        extends MvpFragment<V, P> {

    protected Realm realm;

    protected FragmentMvpDelegate<V, P> getMvpDelegate() {
        if (this.mvpDelegate == null) {
            this.mvpDelegate = new BaseFragamentDelegateImpl<>(this);
        }
        return this.mvpDelegate;
    }

    public boolean onBackPressed() {
        return false;
    }


    protected boolean filterException(Exception e) {
        if (e != null) {
            toast(e.getMessage());
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
