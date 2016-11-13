package com.example.admin.mysimplenews;

/**
 * Created by admin on 2016/11/8.
 */

public class NewsItemBean {
    public String imagePath;
    public String title;
    public String desc;
    public String url;

    public NewsItemBean(String imagePath, String title,String desc, String url) {
        this.imagePath = imagePath;
        this.title = title;
        this.desc=desc;
        this.url = url;
    }
}
