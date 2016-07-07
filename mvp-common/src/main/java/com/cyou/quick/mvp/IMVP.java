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
 

 
public interface IMvp<V extends MvpView, P extends MvpPresenter<V>> {

    /**
     * Creates the presenter instance
     *
     * @return the created presenter instance
     */
    P createPresenter();

    /**
     * Get the presenter. If null is returned, then a internally a new presenter instance gets
     * created
     * by calling {@link #createPresenter()}
     *
     * @return the presenter instance. can be null.
     */
    P getPresenter();

    /**
     * Sets the presenter instance
     *
     * @param presenter The presenter instance
     */
    void setPresenter(P presenter);

    /**
     * Get the MvpView for the presenter
     *
     * @return The view associated with the presenter
     */
    V getMvpView();

    /**
     * Is the view retaining? This boolean flag is used for {@link MvpPresenter#detachView(boolean)}
     * as parameter.
     *
     * @return true if the view is retaining, hence the presenter should be retaining as well.
     */
    boolean isRetainingInstance();
}
