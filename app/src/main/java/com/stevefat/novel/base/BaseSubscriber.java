package com.stevefat.novel.base;

import android.util.Log;

import com.orhanobut.logger.Logger;
import com.stevefat.novel.AppContext;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * Author: ngh
 * date: 2016/9/26
 */

public abstract class BaseSubscriber extends Subscriber<ResponseBody> {

    @Override
    public void onCompleted() {
        AppContext.endTime = System.currentTimeMillis();

        Logger.e("onCompleted----------------"+AppContext.endTime);

        Logger.e("一共用了："+(AppContext.endTime-AppContext.startTime));
    }

    @Override
    public void onStart() {
        super.onStart();
        AppContext.startTime = System.currentTimeMillis();
        Logger.e("onStart----------------"+AppContext.startTime );
    }
}
