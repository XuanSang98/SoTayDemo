package com.vinsofts.sotayvatly10.object;

import java.util.Comparator;

/**
 * Created by AT on 4/20/2016.
 */
public class CategoryObject{
    private int id;
    private String name;
    private int parentId;
    private int percentComplete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getPercentComplete() {
        return percentComplete;
    }

    public void setPercentComplete(int percentComplete) {
        this.percentComplete = percentComplete;
    }


    //Tang len
    public static Comparator<CategoryObject> ascendingPercent = new Comparator<CategoryObject>() {
        @Override
        public int compare(CategoryObject lhs, CategoryObject rhs) {
            int percent1 = lhs.getPercentComplete();
            int percent2 = rhs.getPercentComplete();
            return percent1-percent2;
        }
    };

    //Giam di
    public static Comparator<CategoryObject> descendingPercent = new Comparator<CategoryObject>() {
        @Override
        public int compare(CategoryObject lhs, CategoryObject rhs) {
            int percent1 = lhs.getPercentComplete();
            int percent2 = rhs.getPercentComplete();
            return percent2-percent1;
        }
    };

    public static Comparator<CategoryObject> compareID = new Comparator<CategoryObject>() {
        @Override
        public int compare(CategoryObject lhs, CategoryObject rhs) {
            int id1 = lhs.getId();
            int id2 = rhs.getId();
            return id1-id2;
        }
    };

}
