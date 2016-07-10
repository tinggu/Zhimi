package com.linjin.zhimi.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cyou.app.mvp.BaseMvpFragment;
import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.account.register.RegisterPresenter;
import com.linjin.zhimi.account.register.RegisterStep0Fragment;
import com.linjin.zhimi.utils.IntentStarter;

import butterknife.BindView;
import butterknife.OnClick;
 
public class SelectFragment extends BaseMvpFragment {
    
    private static final String TAG = "SelectFragment";
    
    public static SelectFragment newInstance() {
        Bundle args = new Bundle();
        Log.i(TAG, "newInstance: SelectFragment");
        SelectFragment fragment = new SelectFragment();
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

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        Log.i(TAG, "onViewCreated: selectFragment");
//    }

    @OnClick({R.id.btn_register, R.id.btn_login})
    void onClick(View view) {
        Log.i(TAG, "onClick: " + view.getId());
        int id = view.getId();
        if (id == R.id.btn_register) { 
            start(RegisterStep0Fragment.newInstance());
        } else if (id == R.id.btn_login) {
//            start(LoginFragment.newInstance());
            IntentStarter.showMain(getActivity());
        }

    }

}
