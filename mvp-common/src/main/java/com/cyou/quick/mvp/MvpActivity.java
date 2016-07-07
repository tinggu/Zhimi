/*
 * Copyright 2015 tinggu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cyou.quick.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cyou.quick.QuickActivity;

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends QuickActivity
        implements IMvp<V, P>, MvpView {
 
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getPresenter() == null){
            setPresenter(createPresenter());
        }
        getPresenter().attachView(getMvpView());
    }

    /**
     * Instantiate a presenter instance
     *
     * @return The {@link MvpPresenter} for this view
     */
    public abstract P createPresenter();

    
    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public boolean isRetainingInstance() {
        return false;
    }
}
