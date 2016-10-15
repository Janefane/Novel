package com.stevefat.novel.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author: ngh
 * date: 2016/9/24
 */

@Entity
public class Catalog {
    @Id
    private Long id;
    private String name;
    private String url;
    private String str;
    private String b;

    @Generated(hash = 1204010410)
    public Catalog(Long id, String name, String url, String str, String b) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.str = str;
        this.b = b;
    }

    @Generated(hash = 861357461)
    public Catalog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
