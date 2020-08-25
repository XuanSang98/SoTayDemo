package com.vinsofts.sotayvatly10.object;

/**
 * Created by Administrator on 25/04/2016.
 */
public class TipsObject {
    private int icon;
    private String url;
    private String title;

    public TipsObject(){}
    public TipsObject(int icon, String url, String title) {
        this.icon = icon;
        this.url = url;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
