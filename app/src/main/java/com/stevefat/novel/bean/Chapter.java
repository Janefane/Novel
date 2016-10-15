package com.stevefat.novel.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: ngh
 * date: 2016/10/12
 */

public class Chapter implements Parcelable {
    private Long id;
    private String title;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
    }



    public static final Parcelable.Creator<Chapter> CREATOR = new Parcelable.Creator<Chapter>() {
        @Override
        public Chapter createFromParcel(Parcel in) {
            Chapter chapter = new Chapter();
            chapter.id = (Long) in.readValue(Long.class.getClassLoader());
            chapter.title = in.readString();
            chapter.content = in.readString();
            return chapter;
        }

        @Override
        public Chapter[] newArray(int size) {
            return new Chapter[size];
        }
    };
}
