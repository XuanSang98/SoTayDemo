package com.vinsofts.sotayvatly10.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.common.Constant;
import com.vinsofts.sotayvatly10.common.ObservableWebView;
import com.vinsofts.sotayvatly10.utils.GlobalFuncition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by HongChien on 22/04/2016.
 */
public class PageDetailFragment extends Fragment implements View.OnTouchListener, ObservableWebView.OnScrollChangedCallback {
    private ObservableWebView wvContent;
    private RelativeLayout relativeDetail, layout_menu_top, layout_menu_bottom;
    private GestureDetector gs = null;
    private ProgressBar progressPage;
    Runnable mRunnable;
    Handler mHandler = new Handler();
    private BroadcastReceiver receiver;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_page_detail_layout, container, false);

        wvContent = (ObservableWebView) view.findViewById(R.id.wvContent);
        progressPage = (ProgressBar) view.findViewById(R.id.progressPage);

        layout_menu_bottom = (RelativeLayout) getActivity().findViewById(R.id.layout_menu_bottom);
        layout_menu_top = (RelativeLayout) getActivity().findViewById(R.id.layout_menu_top);
        relativeDetail = (RelativeLayout) view.findViewById(R.id.relativeDetail);



        Bundle bundle = getArguments();

        wvContent.getSettings().setJavaScriptEnabled(true);
        wvContent.setWebViewClient(new MyWebViewClient());
        File file = new File(Constant.PATH_ASSEST + bundle.getString(Constant.PASS_CONTENT_PG));
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        String content = contentBuilder.toString();
        wvContent.loadData(content, "text/html", "UTF-8");

        if (!bundle.getBoolean("DHC")) {
            wvContent.setOnTouchListener(this);
            wvContent.setOnScrollChangedCallback(this);
        }

        mRunnable = new Runnable() {
            @Override
            public void run() {

                if (layout_menu_top.getVisibility() == View.VISIBLE) {
                    layout_menu_bottom.setVisibility(View.GONE);
                    layout_menu_top.setVisibility(View.GONE);
                    Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_page_detail_invisible);
                    layout_menu_bottom.startAnimation(anim);
                    layout_menu_top.startAnimation(anim);
                }

            }
        };

        initSetting();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        registerBroadcast();
    }

    @Override
    public void onStop() {
        super.onStop();
        unRegisterBroadcast();
    }

    private void registerBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_CHANGE_SETTINGS);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                initSetting();
            }
        };
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver, intentFilter);
    }

    private void unRegisterBroadcast() {
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(receiver);
    }

    private void initSetting() {
        // size chư
        wvContent.getSettings().setDefaultFontSize(GlobalFuncition.getSizeText(getContext()) + 10);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v.getId() == R.id.wvContent && event.getAction() == MotionEvent.ACTION_DOWN) {
            if (layout_menu_top.getVisibility() == View.GONE) {
                layout_menu_bottom.setVisibility(View.VISIBLE);
                layout_menu_top.setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_page_detail_visible);
                layout_menu_bottom.startAnimation(anim);
                layout_menu_top.startAnimation(anim);
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable, 3000);
            }

        }

        return false;
    }

    @Override
    public void onScroll(int l, int t) {
        if (layout_menu_top.getVisibility() == View.VISIBLE) {
            layout_menu_bottom.setVisibility(View.GONE);
            layout_menu_top.setVisibility(View.GONE);
            Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_page_detail_invisible);
            layout_menu_bottom.startAnimation(anim);
            layout_menu_top.startAnimation(anim);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressPage.setVisibility(View.GONE);
        }
    }
}
