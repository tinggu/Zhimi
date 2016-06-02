package com.linjin.zhimi.utils;

import android.support.v4.util.LongSparseArray;

import com.linjin.zhimi.api.GroupMemberApi;
import com.linjin.zhimi.model.card.GroupCardList;
import com.linjin.zhimi.rest.RestUtils;
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
public class GroupCacheUtils {

    public static final String TAG = "GroupCache";
    private static GroupCacheUtils groupCacheUtils;

    private LongSparseArray<RealmConfiguration> realmConfigMap;

    private GroupCacheUtils() {
        realmConfigMap = new LongSparseArray<>();
    }

    public static void init() {
        groupCacheUtils = new GroupCacheUtils();
    }

    public static GroupCacheUtils getInstance() {
        if (groupCacheUtils == null) {
            throw new NullPointerException("No groupCacheUtils was initLeanCloud. Call CacheUtils.initLeanCloud() first");
        }
        return groupCacheUtils;
    }

    public Realm getRealmByGroupId(long groupId) {

        RealmConfiguration config = realmConfigMap.get(groupId);
        if (config != null) {
            return Realm.getInstance(config);
        }
        config = createConfig(groupId);
        realmConfigMap.put(groupId, config);
        return Realm.getInstance(config);
    }
   
    private RealmConfiguration createConfig(long groupId){
       return new RealmConfiguration.Builder(QuickApplication.getInstance())
                .name("group_" + groupId + ".realm").build();
    }
    public <E extends RealmObject> RealmResults<E> findAll(Realm realm, Class<E> clazz) {
        RealmResults<E> results = realm.where(clazz).findAll();
        LogUtils.i(TAG, "findAll size : " + results.size());
        return results;
    }

    public <E extends RealmObject> void insertOrUpdate(long groupId, E object) {
        LogUtils.i(TAG, "insertOrUpdate group " + groupId);
        Realm realm = getRealmByGroupId(groupId);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(object);
        realm.commitTransaction();
        realm.close();
    }

    public <E extends RealmObject> void insertOrUpdates(long groupId, List<E> objects) {
        LogUtils.i(TAG, "insertOrUpdates group ." + groupId + "; .... size " + objects.size());
        Realm realm = getRealmByGroupId(groupId);
        realm.beginTransaction();
        for (E obj : objects) {
            LogUtils.i(TAG, "insertOrUpdates obj " + obj.toString());
            realm.copyToRealmOrUpdate(obj);
        }
        realm.commitTransaction();
        realm.close();
    }
    
    public void deleteGroupAllData(long groupId){
        RealmConfiguration  realmConfig = createConfig(groupId);
        Realm.deleteRealm(realmConfig);
//        Realm realm = getRealmByGroupId(groupId);
//        realm.deleteAll();
    }

    public <E extends RealmObject> void deleteAll(Realm realm, RealmResults<E> objects) {
        realm.beginTransaction();
        objects.clear();
        realm.commitTransaction();
    }

    public <E extends RealmObject> void delete(Realm realm, E object) {
        realm.beginTransaction();
        object.removeFromRealm();
        realm.commitTransaction();
    }


    public void deleteGroup(long groupId) {
        RealmConfiguration config = realmConfigMap.get(groupId);
        if (config != null) {
            Realm.deleteRealm(config);
        }
    }

    public void updateGroupMemberList(final long groupId) {
        
        Observable<GroupCardList> observable = RestUtils.createApi(GroupMemberApi.class).getGroupMemberList(groupId);
        applyScheduler(observable).subscribe(new Subscriber<GroupCardList>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GroupCardList groupCardList) {
                insertOrUpdates(groupId, groupCardList.list);
            }
        });
    }
    
    private <E> Observable<E> applyScheduler(Observable<E> observable) {

        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

//        return observable.compose(latest AndroidSchedulerTransformer());
    }

}
