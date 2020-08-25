package com.vinsofts.sotayvatly10.fragment;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by HongChien on 04/05/2016.
 */
public class MyRefreshListView implements SwipeRefreshLayout.OnRefreshListener {
    private Context mContext;
    private SwipeRefreshLayout swipeContainer;

    public MyRefreshListView(Context mContext, SwipeRefreshLayout swipeContainer) {
        this.mContext = mContext;
        this.swipeContainer = swipeContainer;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeContainer.setRefreshing(false);
            }
        }, 1000);
    }
}
