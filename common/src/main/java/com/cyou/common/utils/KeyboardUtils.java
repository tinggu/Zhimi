/*
 * Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cyou.common.utils;

import android.app.Instrumentation;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtils {

    /**
     * Hides the soft keyboard from screen
     *
     * @param view Usually the EditText, but in dynamically  layouts you should pass the layout
     *             instead of the EditText
     * @return true, if keyboard has been hidden, otherwise false (i.e. the keyboard was not displayed
     * on the screen or no Softkeyboard because device has hardware keyboard)
     */
    public static boolean hideKeyboard(View view) {
        if (view == null) {
            return false;
        }
        try {
            InputMethodManager imm = 
                    (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm == null) {
                return false;
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean showKeyboard(View view) {
        if (view == null) {
            return false;
        }
        try {
            InputMethodManager imm = 
                    (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm == null) {
                return false;
            }
            imm.toggleSoftInput(0, InputMethodManager.SHOW_IMPLICIT);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void callBackKeyClick() {
        //            try{
//                Runtime runtime=Runtime.getRuntime();
//                runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
//            }catch(IOException e){
//                LogUtils.e("Exception when doBack", e.toString());
//            }

        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                } catch (Exception e) {
                    LogUtils.e("Exception when onBack", e.toString());
                }
            }
        }.start();
    }

}