package com.vinsofts.sotayvatly10.object;

/**
 * Created by HongChien on 22/04/2016.
 */
public class PageDetailObject {
    private int mId;
    private int mStoryId;
    private String mContent;
    private int mPageIndex;
    private int mFavorite;
    private float mComplete;

    private int mIconNote;

    public int getmIconNote() {
        return mIconNote;
    }

    public void setmIconNote(int mIconNote) {
        this.mIconNote = mIconNote;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public int getStoryId() {
        return mStoryId;
    }

    public void setStoryId(int mStoryId) {
        this.mStoryId = mStoryId;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public int getPageIndex() {
        return mPageIndex;
    }

    public void setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
    }

    public int getFavorite() {
        return mFavorite;
    }

    public void setFavorite(int mFavorite) {
        this.mFavorite = mFavorite;
    }

    public float getComplete() {
        return mComplete;
    }

    public void setComplete(float mComplete) {
        this.mComplete = mComplete;
    }
}
