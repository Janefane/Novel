package com.stevefat.novel.utils;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.Observers;
import rx.schedulers.Schedulers;

/**
 * Author: ngh
 * date: 2016/9/26
 */

public class RxSubUtils {
    public static void toSuber(Observable observable, Subscriber subscriber){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);





    }
}
