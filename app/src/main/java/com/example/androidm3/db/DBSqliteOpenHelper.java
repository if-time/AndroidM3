package com.example.androidm3.db;

/*
 * 向app导入数据库
 * */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBSqliteOpenHelper extends SQLiteOpenHelper {

    //这里的信息字段和外部db文件中信息保持一致。
    public static final String DongDB_NAME = "sqlitedong.db";
    //表名
    public static final String TABLE_Dong = "Dong";
    //字段名
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_release_time = "release_time";
    public static final String COLUMN_model = "model";
    public static final String COLUMN_version = "version";
    public static final String COLUMN_describe = "describe";
    public static final String COLUMN_condition = "condition";

    public DBSqliteOpenHelper(Context context) {
        super(context, DongDB_NAME, null, Data.SQLITE_VERSON);
        this.myContext = context;
    }

    private Context myContext;

    private String DB_PATH = "/data/data/com.example.dong/databases/";

    private static String ASSETS_NAME = "sqlitedong.db";

    private SQLiteDatabase myDataBase = null;


    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (!dbExist) {
            try {
                File dir = new File(DB_PATH);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                File dbf = new File(DB_PATH +  DongDB_NAME);
                if (dbf.exists()) {
                    dbf.delete();
                }
                SQLiteDatabase.openOrCreateDatabase(dbf, null);
                copyDataBase();
            } catch (IOException e) {
                throw new Error("数据库创建失败");
            }
        }
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = null;
        //        try {
        myInput = myContext.getAssets().open(ASSETS_NAME);
        String outFileName = DB_PATH + DongDB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
    }

    private boolean checkDataBase() {
        String myPath = DB_PATH + DongDB_NAME;
        File file=new File(myPath);
        return file.exists();
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}