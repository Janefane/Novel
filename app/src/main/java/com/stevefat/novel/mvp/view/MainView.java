package com.stevefat.novel.mvp.view;

/**
 * Author: ngh
 * date: 2016/9/26
 */

public interface MainView {
    void showProgress();

    void hideProgress();

    void onSuccess(Object object);

    void onError(String msg);
}
