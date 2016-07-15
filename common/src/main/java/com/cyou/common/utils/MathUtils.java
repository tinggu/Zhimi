package com.cyou.common.utils;

import android.util.Log;

import java.util.Random;

/**
 * Created by liujianguang on 2015/7/24.
 */
public class MathUtils {

    public static int createRandomValue(int modular) {
        if (modular <= 0) {
            Log.e("Random", MathUtils.class.getName() + " RandomValue modular 必须是正整数");
            return 0;
        }
        Random r = new Random();
        int value = Math.abs(r.nextInt());
        return value % modular;
    }

    public static int createRandomValue() {
        Random r = new Random();
        int value = Math.abs(r.nextInt());
        return value;
    }

}
