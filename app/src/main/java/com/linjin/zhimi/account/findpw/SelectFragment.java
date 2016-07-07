package com.linjin.zhimi.account.findpw;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cyou.app.mvp.BaseMvpFragment;
import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.account.register.RegisterPresenter;
import com.linjin.zhimi.account.register.RegisterStep0Fragment;
import com.linjin.zhimi.utils.IntentStarter;

import butterknife.OnClick;
 
public class SelectFragment extends BaseMvpFragment {
    
    private static final String TAG = "SelectFragment";
    private RegisterPresenter regPresenter;

    public static SelectFragment newInstance(RegisterPresenter regPresenter) {
        Bundle args = new Bundle();
        SelectFragment fragment = new SelectFragment();
        fragment.regPresenter = regPresenter;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_select;
    }

    @Override
    public MvpPresenter createPresenter() {
        return new MvpBasePresenter();
    }
    
    @OnClick({R.id.btn_register, R.id.btn_login})
    void onClick(View view) {
        Log.i(TAG, "onClick: " + view.getId());
        int id = view.getId();
        if (id == R.id.btn_register) { 
            start(RegisterStep0Fragment.newInstance(regPresenter));
        } else if (id == R.id.btn_login) {
//            start(LoginFragment.newInstance());
            IntentStarter.showMain(getActivity());
        }

    }

}
