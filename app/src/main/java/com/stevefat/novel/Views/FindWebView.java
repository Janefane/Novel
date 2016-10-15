package com.stevefat.novel.Views;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.orhanobut.logger.Logger;

/**
 * Author: ngh
 * date: 2016/10/13
 */
public class FindWebView extends WebView {
    public Callback callback;
    private OnScrollChangedListener mOnScrollChangedListener;


    @SuppressWarnings("deprecation")
    public FindWebView(Context context, AttributeSet attrs, int defStyle,
                       boolean privateBrowsing) {
        super(context, attrs, defStyle, privateBrowsing);
        init();
    }


    public FindWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    public FindWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public FindWebView(Context context) {
        super(context);
        init();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        WebSettings setting = getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        addJavascriptInterface(new SelectedText(), "search");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //支持webview调试
            WebView.setWebContentsDebuggingEnabled(true);
        }


    }

    @Override
    public ActionMode startActionMode(Callback callback) {
        return super.startActionMode(callback);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public class CustomizedSelectActionModeCallback implements ActionMode.Callback {
        private Callback callback;


        public CustomizedSelectActionModeCallback(Callback callback) {
            this.callback = callback;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return callback.onCreateActionMode(mode, menu);
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return callback.onPrepareActionMode(mode, menu);
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item == null || TextUtils.isEmpty(item.getTitle())) {
                return callback.onActionItemClicked(mode, item);
            }
            if (!item.getTitle().toString().contains("搜索")
                    && !item.getTitle().toString().contains("search")) {
                return callback.onActionItemClicked(mode, item);
            }
            loadUrl("javascript:window.search.show(window.getSelection().toString());");
            clearFocus();
            return true;
        }


        @Override
        public void onDestroyActionMode(ActionMode mode) {
            callback.onDestroyActionMode(mode);
        }
    }
    public class SelectedText {
        @JavascriptInterface
        public void show(String data) {
            Logger.e(data);

//            Intent intent = new Intent(getContext(), SearchActivity.class);
//            intent.putExtra(SearchActivity.TAG_SEARCH, data);
//            getContext().startActivity(intent);
        }
    }
    private int dY;


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);


        int dy = t - oldt;
        dY += dy;
        if (mOnScrollChangedListener != null && Math.abs(dY) > 10) {
            dY = 0;
            mOnScrollChangedListener.onScroll(l, t, oldl, oldt);
        }
    }


    public interface OnScrollChangedListener {
        public void onScroll(int l, int t, int oldl, int oldt);
    }


    public void setOnScrollChangedListener(OnScrollChangedListener mOnScrollChangedListener) {
        this.mOnScrollChangedListener = mOnScrollChangedListener;
    }
}
