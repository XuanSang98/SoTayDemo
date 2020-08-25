package com.vinsofts.sotayvatly10.object;

/**
 * Created by HongChien on 27/04/2016.
 */
public class TableOfContentObject {
    private int id;
    private int storyId;
    private String name;
    private int pageDetailId;
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageDetailId() {
        return pageDetailId;
    }

    public void setPageDetailId(int pageDetailId) {
        this.pageDetailId = pageDetailId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
