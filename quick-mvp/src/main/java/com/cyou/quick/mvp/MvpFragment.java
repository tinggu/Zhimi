/*
 * Copyright 2015 Hannes Dorfmann.
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
import android.view.View;

import com.cyou.quick.QuickFragment;


public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter<V>>
        extends QuickFragment implements IMvp<V, P>, MvpView {

    /**
     * The presenter for this view. Will be instantiated with {@link #createPresenter()}
     */
    protected P presenter;


    /**
     * Creates a new presenter instance, if needed. Will reuse the previous presenter instance if
     * {@link #setRetainInstance(boolean)} is set to true. This method will be called from
     * {@link #onViewCreated(View, Bundle)}
     */
    public abstract P createPresenter();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getPresenter() == null) {
            setPresenter(createPresenter());
        }
        getPresenter().attachView(getMvpView());
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isRetainingInstance() {
        return getRetainInstance();
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }


}

