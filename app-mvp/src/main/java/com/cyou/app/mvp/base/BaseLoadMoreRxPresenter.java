package com.cyou.app.mvp.base;

import android.text.TextUtils;
import android.widget.Toast;

import com.cyou.app.mvp.loadmore.LoadMoreRxPresenter;
import com.cyou.app.mvp.loadmore.LoadMoreView;
import com.cyou.common.entity.RESTResult;
import com.cyou.common.utils.RetrofitErrorFilter;
import com.cyou.quick.QuickApplication;
import com.linjin.zhimi.DataCenter;
import com.linjin.zhimi.rest.ApiCode;

public class BaseLoadMoreRxPresenter<V extends LoadMoreView<M>, M> extends LoadMoreRxPresenter<V, M> {

    @Override
    protected void onCompleted() {
        this.unsubscribe();
    }

    @Override
    protected void onError(Throwable e, boolean pullToRefresh) {
        e = RetrofitErrorFilter.filterError(e);
        super.onError(e, pullToRefresh);
        String message = e.getMessage();
        if (!TextUtils.isEmpty(message)) {
//            ToastView.showCustomToast(message, ToastView.ToastType.FAIL);
        }
    }

    @Override
    protected void onLoadMoreError(Throwable e) {
        super.onLoadMoreError(e);
    }


    @Override
    protected void onLoadMoreNext(M data) {
        if ((data instanceof RESTResult) && ((RESTResult) data).getCode() == ApiCode.FAILED_CODE_TOKEN_INVALID) {
        
            DataCenter.getInstance().removeUser();
            String tip = ((RESTResult) data).message;
            if (!TextUtils.isEmpty(tip)) {
                Toast.makeText(QuickApplication.getInstance(), tip, Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onLoadMoreNext(data);
    }

    @Override
    protected void onNext(M data) {
        if ((data instanceof RESTResult) && ((RESTResult) data).getCode() == ApiCode.FAILED_CODE_TOKEN_INVALID) {
            DataCenter.getInstance().removeUser();
            String tip = ((RESTResult) data).message;
            if (!TextUtils.isEmpty(tip)) {
                Toast.makeText(QuickApplication.getInstance(), tip, Toast.LENGTH_SHORT).show();
            }
            return;
        }
        //兼容搜索stateCode=2的情况
        if ((data instanceof RESTResult) && 
                (((RESTResult) data).getCode() == ApiCode.SUCCESS_CODE ||
                        ((RESTResult) data).getCode() == ApiCode.NO_DATA_CODE) && isViewAttached()) {
            getView().showContent();
        }
        super.onNext(data);
    }
}