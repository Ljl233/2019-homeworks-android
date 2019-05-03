package com.mini.homeworks.Search;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecordSQLiteOpenHelper extends SQLiteOpenHelper {

    private static String name = "temp.db";
    private static Integer version = 1;

    public RecordSQLiteOpenHelper (Context context){
        super (context,name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //打开数据库and建立一个名为records的表，里面只有一列name来储存记录
        db.execSQL("create table records(id integer primary key autoincrement,name varchar(200))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
