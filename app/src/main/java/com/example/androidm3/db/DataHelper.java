package com.example.androidm3.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataHelper  extends SQLiteOpenHelper {

    //创建表的SQL语句
    public static final String CREATE_Result = "create table " +
            Data.TABLE_NAME1 + "(" +
            Data.COLUMN_NUM + " integer primary key autoincrement, " +
            //这里日期用text格式存储
            Data.COLUMN_TIME + " text, " +
            Data.COLUMN_TYPE + " text, " +
            Data.CLOUMN_PLACE + " text, " +
            Data.CLOUMN_RESULT + " text, " +
            Data.CLOUMN_PHOTO + " text "
            + ")";


    public static final String CREATE_Management = "create table " +
            Data.TABLE_NAME2 + "(" +
            Data._ID + " integer primary key autoincrement, " +
            //这里日期用text格式存储
            Data.COLUMN_RELEASE_TIME + " text, " +
            Data.COLUMN_MODEL + " text, " +
            Data.CLOUMN_VERSION + " real, " +
            Data.CLOUMN_DESCRIBE + " text, " +
            Data.CLOUMN_CONDITION + " integer "
            + ")";

    private Context mContext;

    public DataHelper(Context context) {
        super(context, Data.SQLITE_NAME, null, Data.SQLITE_VERSON);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_Result);
        db.execSQL(CREATE_Management);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + Data.TABLE_NAME2);
        db.execSQL("drop table if exists " + Data.TABLE_NAME1);
        onCreate(db);
    }
}
