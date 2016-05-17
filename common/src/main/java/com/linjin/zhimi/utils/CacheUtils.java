package com.linjin.zhimi.utils;

import com.linjin.zhimi.api.CardApi;
import com.linjin.zhimi.api.GroupApi;
import com.zhimi.model.card.GroupCardList;
import com.zhimi.model.group.GroupList;
import com.linjin.zhimi.rest.RestUtils;
import com.tinggu.common.utils.LogUtils;
import com.cyou.quick.QuickApplication;

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

    public static void init(String userid) {
        LogUtils.i(TAG, "initRealm " + userid);
            RealmConfiguration config = new RealmConfiguration.Builder(QuickApplication.getInstance())
                    .name("db_" + userid + ".realm").build();
            Realm.setDefaultConfiguration(config);
    }


//    private Realm realm;

    public static void refreshCache() {
//        //TODO 通过增量接口同步数据无需删除本地缓存
//        Realm realm = Realm.getDefaultInstance();
//        clearTable(realm, Group.class);
//        clearTable(realm, GroupCard.class);
//        realm.close();
        updateGroupList();
        updateGroupCard();
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

    public static <E extends RealmObject> void deleteAll(Realm realm, RealmResults<E> objects) {
        realm.beginTransaction();
        objects.clear();
        realm.commitTransaction();
    }

    public static <E extends RealmObject> void clearTable(Realm realm, Class<E> clazz) {
        realm.beginTransaction();
        realm.clear(clazz);
        realm.commitTransaction();
    }

    public static <E extends RealmObject> void delete(Realm realm, E object) {
        realm.beginTransaction();
        object.removeFromRealm();
        realm.commitTransaction();
    }

    public static void updateGroupList() {
        GroupApi groupApi = RestUtils.createApi(GroupApi.class);
//        Observable<GroupList> observable = groupApi.requestGroupList(0);
        Observable<GroupList> observable = applyScheduler(groupApi.requestGroupList(0));
        observable.subscribe(new Subscriber<GroupList>() {
            @Override
            public void onCompleted() {
                LogUtils.i(TAG, "updateGroupList..... onCompleted ");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                LogUtils.i(TAG, "updateGroupList..... onError " + e.toString());
            }

            @Override
            public void onNext(GroupList groupList) {
                LogUtils.i(TAG, "updateGroupList..... onNext " + groupList.list.size());
                insertOrUpdates(groupList.list);
            }
        });
//        observable.map(new Func1<GroupList, List<Group>>() {
//            @Override
//            public List<Group> call(GroupList groupList) {
//                insertOrUpdates(groupList.list);
//                return groupList.list;
//            }
//        });
    }

    public static void updateGroupCard() {

        CardApi cardApi = RestUtils.createApi(CardApi.class);
//        Observable<GroupCardList> observable = cardApi.requestGroupCardList();
        Observable<GroupCardList> observable = applyScheduler(cardApi.requestGroupCardList());
        observable.subscribe(new Subscriber<GroupCardList>() {
            @Override
            public void onCompleted() {
                LogUtils.i(TAG, "updateGroupList..... onCompleted ");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                LogUtils.i(TAG, "updateGroupList..... onError ");
            }

            @Override
            public void onNext(GroupCardList cardList) {
                LogUtils.i(TAG, "updateGroupList..... onNext list size : " + cardList.list.size());
                insertOrUpdates(cardList.list);
            }
        });
    }

    private static <E> Observable<E> applyScheduler(Observable<E> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
