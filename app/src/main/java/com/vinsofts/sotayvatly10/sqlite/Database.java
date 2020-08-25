package com.vinsofts.sotayvatly10.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vinsofts.sotayvatly10.BuildConfig;
import com.vinsofts.sotayvatly10.common.Constant;
import com.vinsofts.sotayvatly10.object.BookmarkObject;
import com.vinsofts.sotayvatly10.object.CategoryObject;
import com.vinsofts.sotayvatly10.object.PageDetailObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AT on 4/20/2016.
 */
public class Database {

    public static final int CATEGORY_12 = 0;
    public static final int CATEGORY_11 = 3;
    public static final int CATEGORY_10 = 2;


    private Context mContext;
    protected SQLiteDatabase sqLiteDatabase;
    private List<CategoryObject> list;
    private List<PageDetailObject> listPageDetail;
    private List<BookmarkObject> listBookmark;
    private List<BookmarkObject> listNote;


    private CategoryObject categoryObject;
    private PageDetailObject pageDetailObject;
    private BookmarkObject bookmarkObject;

    public Database(Context mContext) {
        this.mContext = mContext;
        writeData();
    }

    protected void writeData() {
        File fileDatabase = new File(Constant.PATH + "/" + BuildConfig.DATABASE_NAME);
        if (!fileDatabase.exists()) {
            File file = new File(Constant.PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                fileDatabase.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(fileDatabase);
                InputStream inputStream = mContext.getAssets().open(BuildConfig.DATABASE_NAME);
                byte[] bytes = new byte[1024];
                int count = inputStream.read(bytes);
                while (count != -1) {
                    fileOutputStream.write(bytes, 0, count);
                    count = inputStream.read(bytes);
                }
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openDatabase() {
        sqLiteDatabase = mContext.openOrCreateDatabase(Constant.PATH + "/" + BuildConfig.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    public void closeDatabase() {
        sqLiteDatabase.close();
    }

    public List<CategoryObject> listCateDb(String tableName) {
        list = new ArrayList<>();
        openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + tableName + " where " + Constant.PARENT_ID_COLUMN_CATE + " = " + CATEGORY_12, null);
        cursor.moveToFirst();

        int indexId = cursor.getColumnIndex(Constant.ID_COLUMN_CATE);
        int indexParentId = cursor.getColumnIndex(Constant.PARENT_ID_COLUMN_CATE);
        int indexPercent = cursor.getColumnIndex(Constant.PERCENT_COMPLETE_COLUMN_CATE);
        int indexName = cursor.getColumnIndex(Constant.NAME_COLUMN_CATE);

        while (!cursor.isAfterLast()) {
            categoryObject = new CategoryObject();
            categoryObject.setId(cursor.getInt(indexId));
            categoryObject.setParentId(cursor.getInt(indexParentId));
            categoryObject.setPercentComplete(cursor.getInt(indexPercent));
            categoryObject.setName(cursor.getString(indexName));
            list.add(categoryObject);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return list;
    }


    public List<CategoryObject> listLesson(int catId) {
        list = new ArrayList<>();
        openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Constant.TABLE_OF_CONTENT_TABLE
                + " where " + Constant.STORY_ID_COLUMN_TOC + " = " + catId
                + " AND " + Constant.PAGE_DETAIL_ID_COLUMN_TOC + " > 0 ;" , null);
        cursor.moveToFirst();

        int indexStoryId = cursor.getColumnIndex(Constant.STORY_ID_COLUMN_TOC);
        int indexName = cursor.getColumnIndex(Constant.NAME_COLUMN_TOC);

        while (!cursor.isAfterLast()) {
            categoryObject = new CategoryObject();
            categoryObject.setId(cursor.getInt(indexStoryId));
            categoryObject.setName(cursor.getString(indexName));
            list.add(categoryObject);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return list;
    }

    public List<PageDetailObject> listPageDb(String tableName, int storyId) {
        listPageDetail = new ArrayList<>();
        openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + tableName + " where " + Constant.STORY_ID_COLUMN_PD + " = " + storyId, null);
        cursor.moveToFirst();
        int indexIdPage = cursor.getColumnIndex(Constant.ID_COLUMN_PD);
        int indexStoryIdPage = cursor.getColumnIndex(Constant.STORY_ID_COLUMN_PD);
        int indexContentPage = cursor.getColumnIndex(Constant.CONTENT_COLUMN_PD);
        int indexPageIndexPage = cursor.getColumnIndex(Constant.PAGE_INDEX_COLUMN_PD);
        int indexFavoritePage = cursor.getColumnIndex(Constant.FAVORITE_COLUMN_PD);
        int indexCompletePage = cursor.getColumnIndex(Constant.COMPLETE_COLUMN_PD);

        while (!cursor.isAfterLast()) {
            pageDetailObject = new PageDetailObject();
            pageDetailObject.setId(cursor.getInt(indexIdPage));
            pageDetailObject.setStoryId(cursor.getInt(indexStoryIdPage));
            pageDetailObject.setContent(cursor.getString(indexContentPage));
            pageDetailObject.setPageIndex(cursor.getInt(indexPageIndexPage));
            pageDetailObject.setFavorite(cursor.getInt(indexFavoritePage));
            pageDetailObject.setComplete(cursor.getInt(indexCompletePage));
            listPageDetail.add(pageDetailObject);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return listPageDetail;
    }


    public List<PageDetailObject> listPageDetail() {
        listPageDetail = new ArrayList<>();
        openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Constant.PAGE_DETAIL_TABLE, null);
        cursor.moveToFirst();
        int indexIdPage = cursor.getColumnIndex(Constant.ID_COLUMN_PD);
        int indexStoryIdPage = cursor.getColumnIndex(Constant.STORY_ID_COLUMN_PD);
        int indexContentPage = cursor.getColumnIndex(Constant.CONTENT_COLUMN_PD);
        int indexPageIndexPage = cursor.getColumnIndex(Constant.PAGE_INDEX_COLUMN_PD);
        int indexFavoritePage = cursor.getColumnIndex(Constant.FAVORITE_COLUMN_PD);
        int indexCompletePage = cursor.getColumnIndex(Constant.COMPLETE_COLUMN_PD);

        while (!cursor.isAfterLast()) {
            pageDetailObject = new PageDetailObject();
            pageDetailObject.setId(cursor.getInt(indexIdPage));
            pageDetailObject.setStoryId(cursor.getInt(indexStoryIdPage));
            pageDetailObject.setContent(cursor.getString(indexContentPage));
            pageDetailObject.setPageIndex(cursor.getInt(indexPageIndexPage));
            pageDetailObject.setFavorite(cursor.getInt(indexFavoritePage));
            pageDetailObject.setComplete(cursor.getInt(indexCompletePage));
            listPageDetail.add(pageDetailObject);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return listPageDetail;
    }

    public List<BookmarkObject> listBookmarkDB(String tableName) {
        listBookmark = new ArrayList<>();
        openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + tableName, null);
        cursor.moveToFirst();

        int indexIdBM = cursor.getColumnIndex(Constant.ID_COLUMN_BM);
        int indexStoryIdBM = cursor.getColumnIndex(Constant.STORY_COLUMN_BM);
        int indexPageDetailBM = cursor.getColumnIndex(Constant.PAGEDETAIL_ID_COLUMN_BM);
        int indexDesBM = cursor.getColumnIndex(Constant.DESCRIPTION_COLUMN_BM);

        while (!cursor.isAfterLast()) {
            bookmarkObject = new BookmarkObject();
            bookmarkObject.setId(cursor.getInt(indexIdBM));
            bookmarkObject.setStoryId(cursor.getInt(indexStoryIdBM));
            bookmarkObject.setPageDetailId(cursor.getInt(indexPageDetailBM));
            bookmarkObject.setDescription(cursor.getString(indexDesBM));
            listBookmark.add(bookmarkObject);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return listBookmark;
    }

    public List<BookmarkObject> listBookmarkNote() {
        listNote = new ArrayList<>();
        openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Constant.BOOKMARK_TABLE + " where " + Constant.STORY_COLUMN_BM + " =0", null);
        cursor.moveToFirst();

        int indexIdBM = cursor.getColumnIndex(Constant.ID_COLUMN_BM);
        int indexStoryIdBM = cursor.getColumnIndex(Constant.STORY_COLUMN_BM);
        int indexPageDetailBM = cursor.getColumnIndex(Constant.PAGEDETAIL_ID_COLUMN_BM);
        int indexDesBM = cursor.getColumnIndex(Constant.DESCRIPTION_COLUMN_BM);

        while (!cursor.isAfterLast()) {
            bookmarkObject = new BookmarkObject();
            bookmarkObject.setId(cursor.getInt(indexIdBM));
            bookmarkObject.setStoryId(cursor.getInt(indexStoryIdBM));
            bookmarkObject.setPageDetailId(cursor.getInt(indexPageDetailBM));
            bookmarkObject.setDescription(cursor.getString(indexDesBM));
            listNote.add(bookmarkObject);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return listNote;
    }


    public String getNameByCategoryId(int catId, int pageId) {
        openDatabase();
        String selection = Constant.STORY_ID_COLUMN_TOC + " = ? AND "+ Constant.PAGE_DETAIL_ID_COLUMN_TOC+ " = ?";
        String[] selectionArgs = { String.valueOf(catId), String.valueOf(pageId) };
        Cursor cursor = sqLiteDatabase.query(Constant.TABLE_OF_CONTENT_TABLE, null, selection, selectionArgs, null, null,
                null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String name = cursor.getString(2);

                if (!cursor.isClosed()) {
                    cursor.close();
                }
                closeDatabase();
                return name;
            }
        }
        closeDatabase();
        return "";
    }


    public int getCompleteLesson(int catId, int pageIndex) {
        openDatabase();
        String selection = Constant.STORY_ID_COLUMN_PD + " = ? AND "+ Constant.PAGE_INDEX_COLUMN_PD+ " = ?";
        String[] selectionArgs = { String.valueOf(catId), String.valueOf(pageIndex) };
        Cursor cursor = sqLiteDatabase.query(Constant.PAGE_DETAIL_TABLE, null, selection, selectionArgs, null, null,
                null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int percent = cursor.getInt(5);

                if (!cursor.isClosed()) {
                    cursor.close();
                }
                closeDatabase();
                return percent;
            }
        }
        closeDatabase();
        return 0;
    }

    public void updatePageDetail(String column, int value, int pageId) {
        openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column, value);
        sqLiteDatabase.update(Constant.PAGE_DETAIL_TABLE, contentValues, Constant.ID_COLUMN_PD + " = " + pageId, null);
        closeDatabase();
    }
//
//    public void updateCompletePageDetail(int valueComplete, int pageIndex) {
//        openDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Constant.COMPLETE_COLUMN_PD, valueComplete);
//        sqLiteDatabase.update(Constant.PAGE_DETAIL_TABLE, contentValues, Constant.PAGE_INDEX_COLUMN_PD + " = " + pageIndex, null);
//        closeDatabase();
//    }

    public void updateCategory(String tableName, int idCate, int value) {
        openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.PERCENT_COMPLETE_COLUMN_CATE, value);
        sqLiteDatabase.update(tableName, contentValues, Constant.ID_COLUMN_CATE + " = " + idCate, null);
        closeDatabase();
    }


    public void insertBookmark(int storyId, int pageId, String text) {
        openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.STORY_COLUMN_BM, storyId);
        contentValues.put(Constant.PAGEDETAIL_ID_COLUMN_BM, pageId);
        contentValues.put(Constant.DESCRIPTION_COLUMN_BM, text);
        sqLiteDatabase.insert(Constant.BOOKMARK_TABLE, null, contentValues);
        closeDatabase();
    }

    public void deleteBookmark(int id) {
        openDatabase();
        sqLiteDatabase.delete(Constant.BOOKMARK_TABLE, Constant.ID_COLUMN_BM + " = " + id, null);
        closeDatabase();
    }

    public void deleteNoteBookmark(int valueStoryId, int pageDetailId) {
        openDatabase();
        sqLiteDatabase.delete(Constant.BOOKMARK_TABLE, Constant.STORY_COLUMN_BM + " = " + valueStoryId + " and " + Constant.PAGEDETAIL_ID_COLUMN_BM + " = " + pageDetailId, null);
        closeDatabase();
    }


    public void updateBookmark(String columnValue, String value, String columnWhere, int id) {
        openDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(columnValue, value);
        sqLiteDatabase.update(Constant.BOOKMARK_TABLE, contentValues, columnWhere + " = " + id, null);
        closeDatabase();
    }

    public void updateFavoritePageDetail(int value, int idPage) {
        openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.FAVORITE_COLUMN_PD, value);
        sqLiteDatabase.update(Constant.PAGE_DETAIL_TABLE, contentValues, Constant.ID_COLUMN_PD + " = " + idPage, null);
        closeDatabase();
    }

}
