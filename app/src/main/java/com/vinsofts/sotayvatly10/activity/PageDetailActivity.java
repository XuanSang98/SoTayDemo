package com.vinsofts.sotayvatly10.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.adapter.PageDetailPagerAdapter;
import com.vinsofts.sotayvatly10.common.Constant;
import com.vinsofts.sotayvatly10.dialog.RateDialog;
import com.vinsofts.sotayvatly10.fragment.TableOfContentFragment;
import com.vinsofts.sotayvatly10.object.BookmarkObject;
import com.vinsofts.sotayvatly10.object.PageDetailObject;
import com.vinsofts.sotayvatly10.object.TableOfContentObject;
import com.vinsofts.sotayvatly10.sqlite.Database;
import com.vinsofts.sotayvatly10.utils.GlobalFuncition;
import com.vinsofts.sotayvatly10.utils.MySharedPreferences;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongChien on 22/04/2016.
 */
public class PageDetailActivity extends BaseActivity implements ViewPager.OnPageChangeListener,
        View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ViewPager vpPageDetail;
    private List<PageDetailObject> listPageDetail = new ArrayList<>();
    private List<BookmarkObject> listBookmarkNote = new ArrayList<>();
    private List<TableOfContentObject> listTableOfContent = new ArrayList<>();

    private Database database;
    private TextView tvPageNumber;
    private TextView tvTitleDetail;
    private ImageView imgBack, imgNode, imgMenuPageDetail, imgShare, imgBrightness, imgFontSize;
    private ToggleButton tgDone, tgFavorite;
    private int checkPosition = 0;
    private DrawerLayout drawerLayout;
    private FrameLayout containerTreeView;
    private int resultFinal;
    private String contentBookmark;
    private Handler handler = new Handler();
    private int positionLesson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_detail_layout);

        Intent intent = getIntent();
        int idCategory = intent.getIntExtra(Constant.INTENT_ID_CATEGORY_TABLE, -1);
        positionLesson = intent.getIntExtra(Constant.INTENT_POSITION_LESSON, 0);
        //Gán fragment cho framelayout sliding
        FragmentManager frManager = getSupportFragmentManager();
        Fragment fragment = frManager.findFragmentById(R.id.containerTreeView);
        if (fragment == null) {
            fragment = new TableOfContentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("PASS_ID", idCategory);
            ((TableOfContentFragment) fragment).listener = new TableOfContentFragment.TableContentFragmentListener() {
                @Override
                public void onClickItem(int pageId, int storyId) {
                    setTextMenu();
                    for (PageDetailObject temp : listPageDetail) {

                        if (temp.getId() == pageId) {
                            int index = listPageDetail.indexOf(temp);
                            if (index > -1) {
                                vpPageDetail.setCurrentItem(index, false);
                            }
                            break;
                        }
                    }
                }
            };
            fragment.setArguments(bundle);
            frManager.beginTransaction().add(R.id.containerTreeView, fragment).commit();
        }

        initView();
        initSetting();

        drawerLayout.setDrawerListener(new ActionBarDrawerToggle(this, drawerLayout, 0, 0));


        database = new Database(this);
        database.openDatabase();
        listPageDetail = database.listPageDb(Constant.PAGE_DETAIL_TABLE, idCategory);
        database.closeDatabase();

        setTextMenu();

        initAdapter();

        setValueDefault();


    }


    private void setValueDefault() {
        if (listPageDetail.get(positionLesson).getComplete() == 100) {
            tgDone.setChecked(true);
        } else tgDone.setChecked(false);

        if (listPageDetail.get(positionLesson).getFavorite() == 100) {
            tgFavorite.setChecked(true);
        } else tgFavorite.setChecked(false);
    }

    private void initAdapter() {
        PageDetailPagerAdapter adapterPager = new PageDetailPagerAdapter(
                getSupportFragmentManager(),
                listPageDetail
        );
        vpPageDetail.setAdapter(adapterPager);
        vpPageDetail.setCurrentItem(positionLesson);
    }

    private void setTextMenu() {
        tvPageNumber.setText(vpPageDetail.getCurrentItem() + 1 + "/" + listPageDetail.size());
        int category = listPageDetail.get(vpPageDetail.getCurrentItem()).getStoryId();
        int pageId = listPageDetail.get(vpPageDetail.getCurrentItem()).getId();
        tvTitleDetail.setText(database.getNameByCategoryId(category, pageId));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        contentBookmark = "";
        checkPosition = position;
        checkDefaultToggle(listPageDetail.get(position).getComplete(), tgDone);
        checkDefaultToggle(listPageDetail.get(position).getFavorite(), tgFavorite);
        setTextMenu();


        stateBookmark();
        if (listPageDetail.get(position).getmIconNote() == 100) {
            imgNode.setBackgroundResource(R.drawable.ic_note_select);
        } else {
            imgNode.setBackgroundResource(R.drawable.ic_note);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;

            case R.id.imgNode:
                Intent intent = new Intent(PageDetailActivity.this, NoteActivity.class);
                intent.putExtra("ID_PAGEDETAIL", listPageDetail.get(checkPosition).getId());
                startActivity(intent);
                break;

            case R.id.imgMenuPageDetail:
                drawerLayout.openDrawer(containerTreeView);
                break;

            case R.id.imgShare:
                shareApplication();
                break;

            case R.id.imgBrightness:
                showDialogBrightness();
                break;

            case R.id.imgFontSize:
                showDialogFormatText();
                break;
        }
    }

    private void showDialogBrightness() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_brightness, null, false);
        final SeekBar seekbarBrightness = (SeekBar) view.findViewById(R.id.seekbar_brightness);

        int brightness = GlobalFuncition.getBrightness(this);
        if (brightness == -1) {
            seekbarBrightness.setProgress(20);
        }
        seekbarBrightness.setProgress(brightness);
        seekbarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, int i, boolean b) {


                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        GlobalFuncition.setBrightness(PageDetailActivity.this, seekBar.getProgress());
                        initSetting();
                    }
                }, 200);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        CheckBox cbDefault = (CheckBox) view.findViewById(R.id.cb_default);
        boolean brightnessDefault = GlobalFuncition.getBrightnessDefault(this);
        cbDefault.setChecked(brightnessDefault);
        if (brightnessDefault) {
            seekbarBrightness.setEnabled(false);
        }
        cbDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                seekbarBrightness.setEnabled(!b);
                GlobalFuncition.setBrightnessDefault(PageDetailActivity.this, b);
                initSetting();
            }
        });

        Dialog dialog = new Dialog(this);
        dialog.setContentView(view);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();
    }

    private void showDialogFormatText() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_format_text, null, false);
        final TextView tvTextSize = (TextView) view.findViewById(R.id.tv_text_size);
        SeekBar seekbarSizeText = (SeekBar) view.findViewById(R.id.seekbar_size_text);
        int sizeText = GlobalFuncition.getSizeText(PageDetailActivity.this);
        tvTextSize.setText(getString(R.string.size_text, String.valueOf(sizeText + 8)));
        seekbarSizeText.setProgress(sizeText);
        seekbarSizeText.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, int i, boolean b) {
                GlobalFuncition.setSizeText(PageDetailActivity.this, seekBar.getProgress());
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendChangeSettings();
                        tvTextSize.setText(getString(R.string.size_text, String.valueOf(seekBar.getProgress() + 8)));
                    }
                }, 200);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        Dialog dialog = new Dialog(PageDetailActivity.this);
        dialog.setContentView(view);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();

    }

    private void sendChangeSettings() {
        Intent intent = new Intent();
        intent.setAction(Constant.ACTION_CHANGE_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    //Set brightness and background
    private void initSetting() {
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        if (GlobalFuncition.getBrightnessDefault(this)) {
            layout.screenBrightness = -1f;
        } else {
            layout.screenBrightness = (float) GlobalFuncition.getBrightness(this) / 100F;
        }
        getWindow().setAttributes(layout);
    }

    private void shareApplication() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareBody = getString(R.string.share_body);
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)));
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mInterstitialAd.isLoaded()) mInterstitialAd.show();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.tgDone:
                checkToggle(isChecked, Constant.COMPLETE_COLUMN_PD);

                openRateApp();
                break;
            case R.id.tgFavorite:
                getListTableofContent();
                checkToggle(isChecked, Constant.FAVORITE_COLUMN_PD);
                break;

        }
    }

    private void openRateApp() {
        if (MySharedPreferences.getTypeRate(this) == 0) return;
        if (System.currentTimeMillis() - MySharedPreferences.getTimeRate(this) > 1000 * 60 * 60 * 24) {
            RateDialog rateDialog = RateDialog.newInstance();
            rateDialog.show(getSupportFragmentManager(), RateDialog.class.getSimpleName());
        }

    }

    private void checkToggle(boolean isChecked, String column) {
        database.openDatabase();
        if (isChecked) {
            database.updatePageDetail(
                    column,
                    100,
                    listPageDetail.get(checkPosition).getId());
            if (column.equals(Constant.COMPLETE_COLUMN_PD)) {
                listPageDetail.get(checkPosition).setComplete(100);
                updateProgressIntoCate();
            } else if (column.equals(Constant.FAVORITE_COLUMN_PD)) {

                for (int i = 0; i < listTableOfContent.size(); i++) {
                    if (listPageDetail.get(checkPosition).getId() == listTableOfContent.get(i).getPageDetailId()) {
                        contentBookmark = listTableOfContent.get(i).getName();
                    }
                }
                if (listPageDetail.get(checkPosition).getFavorite() == 0) {
                    database.insertBookmark(
                            listPageDetail.get(checkPosition).getStoryId(),
                            listPageDetail.get(checkPosition).getId(),
                            contentBookmark);
                }
                listPageDetail.get(checkPosition).setFavorite(100);
            }

        } else {
            if (column.equals(Constant.FAVORITE_COLUMN_PD)) {

                database.deleteNoteBookmark(listPageDetail.get(checkPosition).getStoryId(), listPageDetail.get(checkPosition).getId());
                listPageDetail.get(checkPosition).setFavorite(0);
            }
            if (column.equals(Constant.COMPLETE_COLUMN_PD)) {
                listPageDetail.get(checkPosition).setComplete(0);
                updateProgressIntoCate();
            }
            database.updatePageDetail(
                    column,
                    0,
                    listPageDetail.get(checkPosition).getId());

        }
        database.closeDatabase();
    }

    private void checkDefaultToggle(float attr, ToggleButton view) {
        if (attr == 100) {
            view.setChecked(true);
        } else view.setChecked(false);
    }

    private void initView() {

        AdView adView = (AdView) findViewById(R.id.adView);
        initAds(adView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        vpPageDetail = (ViewPager) findViewById(R.id.vpPageDetail);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgNode = (ImageView) findViewById(R.id.imgNode);
        imgShare = (ImageView) findViewById(R.id.imgShare);
        imgBrightness = (ImageView) findViewById(R.id.imgBrightness);
        imgFontSize = (ImageView) findViewById(R.id.imgFontSize);
        tgDone = (ToggleButton) findViewById(R.id.tgDone);
        tgFavorite = (ToggleButton) findViewById(R.id.tgFavorite);
        tvPageNumber = (TextView) findViewById(R.id.tvPageNumber);
        tvTitleDetail = (TextView) findViewById(R.id.tvTitleDetail);
        tvTitleDetail.setSelected(true);
        imgMenuPageDetail = (ImageView) findViewById(R.id.imgMenuPageDetail);
        containerTreeView = (FrameLayout) findViewById(R.id.containerTreeView);

        tgDone.setOnCheckedChangeListener(this);
        tgFavorite.setOnCheckedChangeListener(this);
        imgBack.setOnClickListener(this);
        imgNode.setOnClickListener(this);
        imgMenuPageDetail.setOnClickListener(this);
        vpPageDetail.setOnPageChangeListener(this);
        imgShare.setOnClickListener(this);
        imgBrightness.setOnClickListener(this);
        imgFontSize.setOnClickListener(this);
    }


    public int excuteProgress() {
        int result = 0;
        for (int i = 0; i < listPageDetail.size(); i++) {
            result += listPageDetail.get(i).getComplete();
            resultFinal = result / (listPageDetail.size());
        }
        return resultFinal;
    }

    public void updateProgressIntoCate() {
        database.updateCategory(Constant.CATEGORY_TABLE, listPageDetail.get(checkPosition).getStoryId(), excuteProgress());
    }

    //Check state icon note
    private void stateBookmark() {
        database = new Database(this);
        database.openDatabase();
        listBookmarkNote = database.listBookmarkDB(Constant.BOOKMARK_TABLE);
        database.closeDatabase();
        for (int i = 0; i < listPageDetail.size(); i++) {
            listPageDetail.get(i).setmIconNote(0);
        }
        for (int i = 0; i < listBookmarkNote.size(); i++) {
            if (listBookmarkNote.get(i).getStoryId() == 0 && listBookmarkNote.get(i).getPageDetailId() == listPageDetail.get(checkPosition).getId()) {
                listPageDetail.get(checkPosition).setmIconNote(100);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        stateBookmark();

        if (listPageDetail.get(checkPosition).getmIconNote() == 100) {
            imgNode.setBackgroundResource(R.drawable.ic_note_select);
        } else {
            imgNode.setBackgroundResource(R.drawable.ic_note);
        }
    }

    private void getListTableofContent() {
        SharedPreferences sharedPrefs = getSharedPreferences("tableofcontent", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("ListTableOfContent", null);
        Type type = new TypeToken<List<TableOfContentObject>>() {
        }.getType();
        listTableOfContent = gson.fromJson(json, type);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
