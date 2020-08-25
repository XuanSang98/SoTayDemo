package com.vinsofts.sotayvatly10.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.common.Constant;

/**
 * Created by HongChien on 29/04/2016.
 */
public class PrimacyActivity extends BaseActivity {
    private WebView wvPrimacy;
    private ImageView imgBackPrivacy;
    private TextView tvHeaderWebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primacy_layout);

        wvPrimacy = (WebView) findViewById(R.id.wvPrivacy);
        imgBackPrivacy = (ImageView) findViewById(R.id.imgBackPrivacy);
        tvHeaderWebview = (TextView) findViewById(R.id.tvHeaderWebview);
        imgBackPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Intent intent = getIntent();

        if (intent.getStringExtra("LinkContent") == null) {
            //Click vào primacy
            tvHeaderWebview.setText(getString(R.string.privacy));
            wvPrimacy.loadUrl("http://vinsofts.com/app/protected/privacy.html");
            wvPrimacy.getSettings().setJavaScriptEnabled(false);
        } else {
            //Click vào item favorite
            tvHeaderWebview.setText(intent.getStringExtra("TenHeader"));
            wvPrimacy.loadUrl(Constant.PATH_ASSEST + intent.getStringExtra("LinkContent"));
            wvPrimacy.getSettings().setJavaScriptEnabled(false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
