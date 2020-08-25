package com.vinsofts.sotayvatly10.fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

import com.vinsofts.sotayvatly10.R;

/**
 * Created by MyPC on 15/04/2017.
 */

public class BaseFragment extends Fragment {

    protected void openAppOnStore() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);

            intent.setData(Uri.parse("market://developer?id=VINSOFTS+.,+JSC"));
            startActivity(intent);
        } catch (Exception e) { //google play app is not installed
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=VINSOFTS+.,+JSC"));
            startActivity(intent);
        }
    }

    protected void shareApp() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareBody = getString(R.string.share_body);
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)));
    }
}
