package com.vinsofts.sotayvatly10.common;

import android.os.Environment;

import com.vinsofts.sotayvatly10.BuildConfig;

/**
 * Created by AT on 4/20/2016.
 */
public class Constant {

    public static final String VERSION_NAME = "1.0";
    public static final String PACKAGE_NAME = "com.vinsofts.sotayvatly10";
    /*
     * Common
     */
    public static final String PATH = Environment.getExternalStorageDirectory().toString() + BuildConfig.PATH;
    public static final String INTENT_ID_CATEGORY_TABLE = "INTENT_ID_CATEGORY_TABLE";
    public static final String INTENT_POSITION_LESSON = "INTENT_POSITION_LESSON";
    public static final String PASS_NAME_CATEGORY_TABLE = "pass_name_category_table";
    public static final String PATH_ASSEST = Environment.getExternalStorageDirectory().toString() + BuildConfig.PATH+"/";
    public static final String PASS_CONTENT_PG = "pass_content_pg";
    public static final int REQUEST_CODE_PAGE_DETAIL = 1;
    public static final int REQUEST_CODE_FAVORITE = 2;
    public static final String PASS_TEXT_NOTE = "pass_text_note";
    public static final String PASS_URL = "pass_url";
    public static final int SHARED_STILL_BACKGROUND = 0;
    public static final int SHARED_YOUROWN_BACKGROUND = 1;
    public static final String BASE_URL = "http://www.baitap123.com/";
    public static final String ACTION_CHANGE_SETTINGS = "ACTION_CHANGE_SETTINGS";
    public static final String BUNDLE_CAT_ID = "BUNDLE_CAT_ID";

    /*
     * Bookmark table
     */
    public static final String BOOKMARK_TABLE = "bookmark";
    public static final String ID_COLUMN_BM = "id";
    public static final String STORY_COLUMN_BM = "story_id";
    public static final String PAGEDETAIL_ID_COLUMN_BM = "pagedetail_id";
    public static final String DESCRIPTION_COLUMN_BM = "description";
    public static final String IMAGE_COLUMN_BM = "image";


    /*
     * Category table
     */
    public static final String CATEGORY_TABLE = "category";
    public static final String ID_COLUMN_CATE = "id";
    public static final String NAME_COLUMN_CATE = "name";
    public static final String PARENT_ID_COLUMN_CATE = "parent_id";
    public static final String PERCENT_COMPLETE_COLUMN_CATE = "percent_complete";


    /*
     * PageDetail table
     */
    public static final String PAGE_DETAIL_TABLE = "pagedetail";
    public static final String ID_COLUMN_PD = "id";
    public static final String STORY_ID_COLUMN_PD = "story_id";
    public static final String CONTENT_COLUMN_PD = "content";
    public static final String PAGE_INDEX_COLUMN_PD = "page_index";
    public static final String FAVORITE_COLUMN_PD = "favorite";
    public static final String COMPLETE_COLUMN_PD = "complete";


    /*
     * Story table
     */
    public static final String STORY_TABLE = "story";
    public static final String ID_COLUMN_STO = "id";
    public static final String CATEGORY_ID_COLUMN_STO = "category_id";
    public static final String NAME_COLUMN_STO = "name";
    public static final String DESCRIPTION_COLUMN_STO = "description";
    public static final String AUTHOR_COLUMN_STO = "author";
    public static final String NUMBER_OF_PAGE_COLUMN_STO = "numberofpage";
    public static final String PRODUCTION_COLUMN_STO = "production";
    public static final String IMAGE_COLUMN_STO = "image";
    public static final String DATE_COLUMN_STO = "date";

    /*
     * Table of content table
     */
    public static final String TABLE_OF_CONTENT_TABLE = "tableofcontent";
    public static final String ID_COLUMN_TOC = "id";
    public static final String STORY_ID_COLUMN_TOC = "story_id";
    public static final String NAME_COLUMN_TOC = "name";
    public static final String PAGE_DETAIL_ID_COLUMN_TOC = "pagedetail_id";
    public static final String PARENT_ID_COLUMN_TOC = "parent_id";


    public static final String PREF_RATE_APP = "PREF_RATE_APP";
    public static final String PREF_TIME_RATE = "PREF_TIME_RATE";
}
