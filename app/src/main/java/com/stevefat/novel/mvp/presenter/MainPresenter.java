package com.stevefat.novel.mvp.presenter;

import com.orhanobut.logger.Logger;
import com.stevefat.novel.bean.Catalog;
import com.stevefat.novel.bean.Chapter;
import com.stevefat.novel.interfaces.ResultRequest;
import com.stevefat.novel.mvp.model.MainModel;
import com.stevefat.novel.mvp.view.MainView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Author: ngh
 * date: 2016/9/26
 */

public class MainPresenter implements ResultRequest<ResponseBody> {
    private MainModel mainModel;
    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        this.mainModel = new MainModel(this);
    }

    public void initData() {
        mainView.showProgress();
        mainModel.getCatalog();
    }

    public void initChapter(String url) {
        mainView.showProgress();
        mainModel.getChapter(url);

    }

    @Override
    public void onSuccess(ResponseBody responseBody) {
        mainView.hideProgress();
        //解析数据
        try {
            byte[] bytes = responseBody.bytes();
            String str = new String(bytes,"gbk");
            Document doc = Jsoup.parse(str);
            Element content = doc.getElementById("content");

            if (content != null) {
                Chapter chapter = new Chapter();
                String name =doc.select(".bname_content").get(0).text();
                String con = content.toString();
                chapter.setTitle(name);
                chapter.setContent(con);
                mainView.onSuccess(chapter);
                return;
            }
            Elements elements = doc.select(".dccss > a");
            List<Catalog> catalogs = new ArrayList<>();
            for (int i = 0; i < elements.size(); i++) {

                Element element = elements.get(i);
                String href = element.attr("href");
                String text = element.text();
                Logger.e(href + "----" + text);
                Catalog c = new Catalog(Long.valueOf(0), text, href, "", "");
                catalogs.add(c);
            }

            mainView.onSuccess(catalogs);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onError(Throwable e) {
        mainView.hideProgress();
        mainView.onError(e.toString());
    }
}
