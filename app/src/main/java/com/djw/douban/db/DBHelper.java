package com.djw.douban.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JasonDong on 2017/2/22.
 */

public class DBHelper extends SQLiteOpenHelper {

    private final static String name = "yaeryou.db";
    private final static int version = 1;

    public static final String TABLE = "history";
    public static final String MESSAGE = "message";
    public static final String TYPE = "type";


    public DBHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table history(_id integer primary key,message text,type integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
