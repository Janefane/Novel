package com.stevefat.novel.interfaces;

import java.util.Objects;

/**
 * Author: ngh
 * date: 2016/9/26
 */

public interface ResultRequest<T> {
    //返回结果
    void onSuccess(T t);
    //错误结果
    void onError(Throwable e);
}
