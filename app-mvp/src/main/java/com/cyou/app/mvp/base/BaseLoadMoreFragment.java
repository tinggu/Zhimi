package com.cyou.app.mvp.base;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cyou.app.mvp.lce.MvpLceFragment;
import com.cyou.app.mvp.loadmore.LoadMoreView;
import com.cyou.quick.mvp.MvpPresenter; 

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjja
 * Date       : 2016/1/26
 **/
public abstract class BaseLoadMoreFragment<CV extends View, M, V extends LoadMoreView<M>, P extends MvpPresenter<V>>
        extends MvpLceFragment<CV, M, V, P> implements LoadMoreView<M> {
    public static final String TAG = "LoadMore";

   
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData(false);
    }

    @Override
    public void showContent() {
        super.showContent();
        Log.d(TAG, "showContent");
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        Log.d(TAG, "showError");
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        Log.d(TAG, "showLoading");
    }

    @Override
    public void showLoadMoreError(Throwable e) {
//        showLightError(e.toString());
        Log.d(TAG, "showLoadMoreError");
    }

    @Override
    public void showMoreLoading() {
        Log.d(TAG, "showMoreLoading");
    }


    /**
     * Get the data that has been set before in {@link #setData(Object)}
     *
     * @return The data
     */
    public abstract M getData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if (realm != null) {
//            realm.close();
//        }
    }

    protected void toast(String str) {
        Toast.makeText(this.getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int id) {
        Toast.makeText(this.getActivity(), id, Toast.LENGTH_SHORT).show();
    }
}
