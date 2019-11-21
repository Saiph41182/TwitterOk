package com.example.twitterok.realm;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmConfiguration.Builder;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmService {

    private Realm realm;

    public RealmService() {
        this.realm = Realm.getDefaultInstance();
    }

    public <T extends RealmObject> Observable<T> getService(T object, Class<T> clazz){
        return Observable.just(object)
                .doOnSubscribe(t -> realm.beginTransaction())
                .doOnDispose(realm::commitTransaction)
                .doOnDispose(realm::close);
    }

}
