package com.mini.homeworks.MyAssign;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_OVERHEAD = "create table Overhead ("
            + "siteId integer primary key,"
            + "status integer ,"
            + "assignName text ,"
            + "beginTime integer , "
            + "endTime integer)";

    public static final String CREATE_DELETE =  "create table Delete ("
            + "siteId integer primary key,"
            + "status integer ,"
            + "assignName text ,"
            + "beginTime integer , "
            + "endTime integer)";


    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_OVERHEAD);
        db.execSQL(CREATE_DELETE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
