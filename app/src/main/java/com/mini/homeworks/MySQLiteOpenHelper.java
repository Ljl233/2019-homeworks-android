package com.mini.homeworks;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    //数据库版本号
    private static Integer Version = 1;

    //在SQLiteOpenHelper的子类中，必须有构造函数
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //必须通过super调用父类中的构造函数
        super(context, name, factory, version);
    }

    public MySQLiteOpenHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }


    public MySQLiteOpenHelper(Context context, String name) {
        this(context, name, Version);
    }

    //当数据库创建的时候被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table notification(id integer primary key ,switch_allow varchar(64),switchButton_mail varchar(64))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
