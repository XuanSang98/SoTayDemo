package com.vinsofts.sotayvatly10.object;

/**
 * Created by HongChien on 26/04/2016.
 */
public class BookmarkObject {
    private int id;
    private int storyId;
    private int pageDetailId;
    private String description;

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

    public int getPageDetailId() {
        return pageDetailId;
    }

    public void setPageDetailId(int pageDetailId) {
        this.pageDetailId = pageDetailId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
