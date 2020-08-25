package com.vinsofts.sotayvatly10.common;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;


/**
 * Created by HongChien on 22/04/2016.
 */
public class SwipeMenuCreatorListView implements SwipeMenuCreator {

    private Context mContext;
    int icon;

    public SwipeMenuCreatorListView(Context mContext, int icon) {
        this.mContext = mContext;
        this.icon = icon;
    }

    @Override
    public void create(SwipeMenu menu) {
        SwipeMenuItem favoriteItem = new SwipeMenuItem(
                mContext);
        // set item background
        favoriteItem.setBackground(new ColorDrawable(Color.argb(180,0xF9,
                0x3F, 0x25)));
        // set item width
        favoriteItem.setWidth(dp2px(65));
        // set a icon
        favoriteItem.setIcon(icon);
        // add to menu
        menu.addMenuItem(favoriteItem);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }
}
