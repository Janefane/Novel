package com.stevefat.novel.activitys;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.stevefat.novel.AppContext;
import com.stevefat.novel.R;
import com.stevefat.novel.Views.FindWebView;
import com.stevefat.novel.activitys.adapter.RecycleAdapter;
import com.stevefat.novel.bean.Catalog;
import com.stevefat.novel.bean.Chapter;
import com.stevefat.novel.interfaces.RecycleClick;
import com.stevefat.novel.mvp.presenter.MainPresenter;
import com.stevefat.novel.mvp.view.MainView;
import com.stevefat.novel.utils.DialogUtils;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.recycle)
    RecyclerView mRecyclerView;
    @BindView(R.id.content)
    FindWebView mWebView;

    RecycleAdapter adapter;

    private MainPresenter presenter;

    List<Catalog> mCatalogs;
    private DialogUtils mDialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        presenter = new MainPresenter(this);
        presenter.initData();
    }

    private void initView() {
        mDialogUtils = new DialogUtils(this, "数据加载中。。。");
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(manager);


    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void showProgress() {
        mDialogUtils.show();
    }

    @Override
    public void hideProgress() {
        mDialogUtils.dismiss();

    }

    @Override
    public void onSuccess(Object object) {

        if (object instanceof Chapter) {
            Chapter c = (Chapter) object;
            toolbar.setTitle(c.getTitle());
//            Logger.e(c.getContent());
//            tv.setText(Html.fromHtml(c.getStr()));
//            tv.setTextSize(20);
//            tv.setMovementMethod(LinkMovementMethod.getInstance());
            mWebView.loadDataWithBaseURL(null, c.getContent(), "", "UTF-8", "");
        } else {
            mCatalogs = (List<Catalog>) object;
            setAdapter();
        }
//

    }

    private void setAdapter() {
        Collections.reverse(mCatalogs);
        adapter = new RecycleAdapter();
        adapter.setDatas(mCatalogs);
        mRecyclerView.setAdapter(adapter);
        adapter.setClick(new RecycleClick() {
            @Override
            public void onClick(View v, int position) {
                String url = mCatalogs.get(position).getUrl();
                presenter.initChapter(url);
                onBackPressed();
            }
        });

    }

    @Override
    public void onError(String msg) {
        Logger.e(msg);
    }

}
