package com.stevefat.novel.ApiManager;

import com.stevefat.novel.constant.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Author: ngh
 * date: 2016/9/26
 */

public class ApiManager {

    private static ApiManager instance;
    private ApiManagerServices apiManagerServices;

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }


    public ApiManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        this.apiManagerServices = retrofit.create(ApiManagerServices.class);
    }

    public ApiManagerServices getApiManagerServices() {
        return apiManagerServices;
    }
}
