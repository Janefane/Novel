package com.stevefat.novel.ApiManager;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: ngh
 * date: 2016/9/26
 */

public interface ApiManagerServices {
    /**
     * read/131690/  这个是一品少主
     * read/68383/  这个是不灭剑君
     * @return
     */

//    @GET("read/68383/")  //不灭剑君
    @GET("read/131690/")  //一品少主
    Observable<ResponseBody> getCatalog();

    @GET("{url}")
    Observable<ResponseBody> getChapter(@Path("url") String url);
}
