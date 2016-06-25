package com.linjin.zhimi.utils;

import com.cyou.quick.QuickApplication;
import com.linjin.zhimi.api.GroupApi;
import com.linjin.zhimi.model.group.GroupList;
import com.linjin.zhimi.rest.RestUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/3/9 16:56
 */
public class CacheUtils {

    public static final String TAG = "CacheUtils";

    public static void init() {
        LogUtils.i(TAG, "initRealm");
        RealmConfiguration config = new RealmConfiguration.Builder(QuickApplication.getInstance())
                .name("db_zm.realm").build();
        Realm.setDefaultConfiguration(config);
    }


//    private Realm realm;

    public static void refreshCache() {
//        //TODO 通过增量接口同步数据无需删除本地缓存
    }

//    private void close() {
//        if (realm != null) {
//            realm.close();
//        }
//    }

    //<E extends RealmObject> RealmQuery<E> where(Class<E> clazz) 
    public static <E extends RealmObject> RealmResults<E> findAll(Realm realm, Class<E> clazz) {
        RealmResults<E> results = realm.where(clazz).findAll();
        LogUtils.i(TAG, "findAll size : " + results.size());
        return results;
    }

    public static <E extends RealmObject> void insertOrUpdate(E object) {
        LogUtils.i(TAG, "insertOrUpdate");
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(object);
        realm.commitTransaction();
        realm.close();
    }

    public static <E extends RealmObject> void insertOrUpdates(List<E> objects) {
        LogUtils.i(TAG, "insertOrUpdates..... size " + objects.size());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for (E obj : objects) {
            LogUtils.i(TAG, "insertOrUpdates obj " + obj.toString());
            realm.copyToRealmOrUpdate(obj);
        }
        realm.commitTransaction();
        realm.close();
    }

    public static <E extends RealmObject> void delete(Realm realm, E object) {
        realm.beginTransaction();
        object.deleteFromRealm();
        realm.commitTransaction();
    }
    
}
