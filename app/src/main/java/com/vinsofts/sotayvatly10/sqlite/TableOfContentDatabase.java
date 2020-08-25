package com.vinsofts.sotayvatly10.sqlite;

import android.content.Context;
import android.database.Cursor;

import com.vinsofts.sotayvatly10.common.Constant;
import com.vinsofts.sotayvatly10.object.TableOfContentObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongChien on 28/04/2016.
 */
public class TableOfContentDatabase extends Database {
    private List<TableOfContentObject> listRoot;
    private TableOfContentObject tableOfContentObject;
    public TableOfContentDatabase(Context mContext) {
        super(mContext);
    }

    //Lay list parent ID = 0 on TableOfContent table
    public List<TableOfContentObject> getListRoot() {
        listRoot = new ArrayList<>();
        openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Constant.TABLE_OF_CONTENT_TABLE, null);
        cursor.moveToFirst();

        int indexId = cursor.getColumnIndex(Constant.ID_COLUMN_TOC);
        int indexStoryId = cursor.getColumnIndex(Constant.STORY_ID_COLUMN_TOC);
        int indexName = cursor.getColumnIndex(Constant.NAME_COLUMN_TOC);
        int indexParentDetailId = cursor.getColumnIndex(Constant.PAGE_DETAIL_ID_COLUMN_TOC);
        int indexParentId = cursor.getColumnIndex(Constant.PARENT_ID_COLUMN_TOC);

        while (!cursor.isAfterLast()) {
            tableOfContentObject = new TableOfContentObject();
            tableOfContentObject.setId(cursor.getInt(indexId));
            tableOfContentObject.setStoryId(cursor.getInt(indexStoryId));
            tableOfContentObject.setName(cursor.getString(indexName));
            tableOfContentObject.setPageDetailId(cursor.getInt(indexParentDetailId));
            tableOfContentObject.setParentId(cursor.getInt(indexParentId));
            listRoot.add(tableOfContentObject);
            cursor.moveToNext();
        }
        cursor.close();
        return listRoot;
    }
}
