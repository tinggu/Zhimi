package com.linjin.zhimi.base;

import android.text.TextUtils;
import android.widget.Toast;

import com.cyou.quick.QuickApplication;
import com.cyou.quick.mvp.loadmore.LoadMoreRxPresenter;
import com.cyou.quick.mvp.loadmore.LoadMoreView;
import com.linjin.zhimi.DataCenter;
import com.linjin.zhimi.model.BaseModel;
import com.linjin.zhimi.rest.ApiCode;
import com.linjin.zhimi.utils.DialogUtils;
import com.linjin.zhimi.utils.IntentStarter;
import com.tinggu.common.utils.RetrofitErrorFilter;

public class BaseLoadMoreRxPresenter<V extends LoadMoreView<M>, M> extends LoadMoreRxPresenter<V, M> {

    public DialogUtils loadingDialog = new DialogUtils();

    @Override
    protected void onCompleted() {
        this.unsubscribe();
        loadingDialog.hideLoading();
    }

    @Override
    protected void onError(Throwable e, boolean pullToRefresh) {
        e = RetrofitErrorFilter.filterError(e);
        loadingDialog.hideLoading();
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
        if ((data instanceof BaseModel) && ((BaseModel) data).getCode() == ApiCode.FAILED_CODE_TOKEN_INVALID) {
            IntentStarter.showLogin(null);
            DataCenter.getInstance().removeUser();
            String tip = ((BaseModel) data).getMsg();
            if (!TextUtils.isEmpty(tip)) {
                Toast.makeText(QuickApplication.getInstance(), tip, Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onLoadMoreNext(data);
    }

    @Override
    protected void onNext(M data) {
        if ((data instanceof BaseModel) && ((BaseModel) data).getCode() == ApiCode.FAILED_CODE_TOKEN_INVALID) {
            IntentStarter.showLogin(null);
            DataCenter.getInstance().removeUser();
            String tip = ((BaseModel) data).getMsg();
            if (!TextUtils.isEmpty(tip)) {
                Toast.makeText(QuickApplication.getInstance(), tip, Toast.LENGTH_SHORT).show();
            }
            return;
        }
        //兼容搜索stateCode=2的情况
        if ((data instanceof BaseModel) && 
                (((BaseModel) data).getCode() == ApiCode.SUCCESS_CODE ||
                        ((BaseModel) data).getCode() == ApiCode.NO_DATA_CODE) && isViewAttached()) {
            getView().showContent();
        }
        super.onNext(data);
    }
}