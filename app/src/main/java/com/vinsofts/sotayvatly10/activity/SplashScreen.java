package com.vinsofts.sotayvatly10.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vinsofts.sotayvatly10.BuildConfig;
import com.vinsofts.sotayvatly10.R;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;

/**
 * Created by Administrator on 25/04/2016.
 */
public class SplashScreen extends BaseActivity {
    protected boolean _active = true;
    protected int _splashTime = 2000;
    private String EXTENSION =".zip";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sourceZipFilePath = Environment.getExternalStorageDirectory().toString() + BuildConfig.PATH+EXTENSION;
                String extractedZipFilePath = Environment.getExternalStorageDirectory().toString() +"/"+BuildConfig.NAME_FOLDER;
                final File file = new File(sourceZipFilePath);
                try {
                    unpack(sourceZipFilePath,extractedZipFilePath);
                } catch (ZipException e) {
                    e.printStackTrace();
                }
            }
        });
        File file = new  File(Environment.getExternalStorageDirectory().toString() + "/" + BuildConfig.NAME_FOLDER);
        if (file.isDirectory()==false){
            downloadFromDropBoxUrl();
        }else {
            Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1000 : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startDownloading();
                } else {
                    Toast.makeText(this, "Permission denied ...", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    void startDownloading() {
        File file = new  File(Environment.getExternalStorageDirectory().toString() + "/" + BuildConfig.NAME_FOLDER);
        if (!file.exists()) {
            file.mkdir();
        }
        DownloadManager.Request  request = new DownloadManager.Request(Uri.parse(BuildConfig.BASE_URL));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Dowloading file...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(BuildConfig.NAME_FOLDER,BuildConfig.NAME_FILE_DOWNLOAD);
        DownloadManager  manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }


    public void unpack(String sourceZipFilePath, String extractedZipFilePath) throws ZipException, net.lingala.zip4j.exception.ZipException {
        ZipFile zipFile = new ZipFile(sourceZipFilePath);
        System.out.println("---------------"+sourceZipFilePath);
        if (zipFile.isEncrypted())
        {
            zipFile.setPassword(BuildConfig.PASSWORD_ZIP);
        }
        zipFile.extractAll(extractedZipFilePath);
    }

    void downloadFromDropBoxUrl() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1000);
            } else {
                startDownloading();
            }
        } else {
            startDownloading();
        }
    }
}
