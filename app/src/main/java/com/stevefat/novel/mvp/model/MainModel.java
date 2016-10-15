package com.stevefat.novel.mvp.model;

import com.stevefat.novel.ApiManager.ApiManager;
import com.stevefat.novel.ApiManager.ApiManagerServices;
import com.stevefat.novel.AppContext;
import com.stevefat.novel.base.BaseSubscriber;
import com.stevefat.novel.interfaces.ResultRequest;
import com.stevefat.novel.utils.RxSubUtils;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Author: ngh
 * date: 2016/9/26
 */

public class MainModel {
    private ResultRequest resultRequest;

    public MainModel(ResultRequest resultRequest) {
        this.resultRequest = resultRequest;
    }

    private class MainSub extends BaseSubscriber {

        @Override
        public void onError(Throwable e) {
            resultRequest.onError(e);
        }

        @Override
        public void onNext(ResponseBody responseBody) {
            resultRequest.onSuccess(responseBody);
        }
    }

    public void getCatalog() {
        Observable<ResponseBody> obs = ApiManager.getInstance().getApiManagerServices().getCatalog();

        RxSubUtils.toSuber(obs, new MainSub());
    }
    public void getChapter(String url){

        Observable<ResponseBody> obs = ApiManager.getInstance().getApiManagerServices().getChapter(url);

        RxSubUtils.toSuber(obs, new MainSub());
    }
}
