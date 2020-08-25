package com.vinsofts.sotayvatly10.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vinsofts.sotayvatly10.common.Constant;
import com.vinsofts.sotayvatly10.fragment.PageDetailFragment;
import com.vinsofts.sotayvatly10.object.PageDetailObject;

import java.util.List;

/**
 * Created by HongChien on 25/04/2016.
 */
public class PageDetailPagerAdapter extends FragmentPagerAdapter {
    private List<PageDetailObject> listPageDetail;

    public PageDetailPagerAdapter(FragmentManager fm, List<PageDetailObject> listPageDetail) {
        super(fm);
        this.listPageDetail = listPageDetail;
    }

    @Override
    public Fragment getItem(int position) {
        PageDetailFragment pageFragment = new PageDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.PASS_CONTENT_PG,listPageDetail.get(position).getContent());
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public int getCount() {
        return listPageDetail.size();
    }
}
